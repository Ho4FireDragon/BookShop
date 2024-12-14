package myweb.bookshopho4.Service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.experimental.NonFinal;
import myweb.bookshopho4.Model.DTO.UserDTO;
import myweb.bookshopho4.Model.Entity.InvalidateToken;
import myweb.bookshopho4.Model.Entity.Users;
import myweb.bookshopho4.Model.Request.IntrospectRequest;
import myweb.bookshopho4.Model.Request.LoginRequest;
import myweb.bookshopho4.Model.Request.RefreshTokenRequest;
import myweb.bookshopho4.Model.Response.AuthenticationResponse;
import myweb.bookshopho4.Model.Response.IntrospectResponse;
import myweb.bookshopho4.Model.Response.ResponseData;
import myweb.bookshopho4.Model.Response.StatusAndMessage;
import myweb.bookshopho4.Repository.InvalidateRepository;
import myweb.bookshopho4.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationService {

        @Value("${jwt.signJWT}")
        protected String signJWT;

        @Value("${jwt.valid-duration}")
        @NonFinal
        protected Long validDuration;

        @Value("${jwt.refreshable-duration}")
        @NonFinal
        protected Long refreshDuration;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private InvalidateRepository invalidateRepository;
        //####################################################
        private String generateToken(Users user) throws JOSEException {
            JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

            JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                    .subject(user.getEmail())
                    .issuer("BookShopHo4.com")
                    .issueTime(new Date())
                    .expirationTime(new Date(
                            Instant.now().plus(validDuration, ChronoUnit.MINUTES).toEpochMilli()
                    ))
                    .jwtID(UUID.randomUUID().toString())
                    .claim("scope",user.getRole())
                    .build();

            Payload payload = new Payload(jwtClaimsSet.toJSONObject());
            JWSObject jwsObject= new JWSObject(jwsHeader, payload);

            try {
                jwsObject.sign(new MACSigner(signJWT.getBytes()));
                return jwsObject.serialize();
            } catch (JOSEException e) {
                throw new RuntimeException(e);
            }
        }
        //############################################################
        public ResponseEntity<ResponseData<AuthenticationResponse>> authenticateCustomer(LoginRequest loginRequest) throws JOSEException {
            Users userEmail = userRepository.findByEmail(loginRequest.getEmail());

            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            boolean authenticated = passwordEncoder.matches(loginRequest.getPassword(), userEmail.getUserPassword());

            if (!authenticated) {
                ResponseData<AuthenticationResponse> responseData = ResponseData.<AuthenticationResponse>builder()
                        .status(StatusAndMessage.UNAUTHENTICATED.getCode())
                        .message(StatusAndMessage.UNAUTHENTICATED.getMessage())
                        .data(null)
                        .build();
                return ResponseEntity.ok(responseData);
            }
            else{
                var token = generateToken(userEmail);
                AuthenticationResponse authenticationResponse = new AuthenticationResponse(userEmail.getEmail(), token);
                ResponseData<AuthenticationResponse> responseData = ResponseData.<AuthenticationResponse>builder()
                        .status(StatusAndMessage.SUCCESS.getCode())
                        .message(StatusAndMessage.SUCCESS.getMessage())
                        .data(authenticationResponse)
                        .build();
                return ResponseEntity.ok(responseData);
            }
        }
        //#######################################################################

        public ResponseEntity<ResponseData<IntrospectResponse>>  introspectCustomer(IntrospectRequest introspectRequest) {
            {
                var token = introspectRequest.getToken();
                boolean isValid = true;

                try {
                    verifyToken(token,false);
                } catch (Exception e) {
                    isValid = false;
                }

                IntrospectResponse introspectResponse = new IntrospectResponse(isValid);

                ResponseData<IntrospectResponse> responseData = ResponseData.<IntrospectResponse>builder()
                        .status(StatusAndMessage.SUCCESS.getCode())
                        .message(StatusAndMessage.SUCCESS.getMessage())
                        .data(introspectResponse)
                        .build();
                return ResponseEntity.ok(responseData);
            }
        }
        //###################################################################
            private SignedJWT verifyToken(String token, Boolean isRefresh) throws JOSEException, ParseException {
                JWSVerifier verifier = new MACVerifier(signJWT.getBytes());
                SignedJWT signedJWT = SignedJWT.parse(token);

                Date expityTime = (isRefresh)
                        ? new Date(signedJWT.getJWTClaimsSet().getIssueTime().toInstant().plus(refreshDuration, ChronoUnit.MINUTES).toEpochMilli())

                        : signedJWT.getJWTClaimsSet().getExpirationTime();

                var verified = signedJWT.verify(verifier);

                if(!verified && expityTime.after(new Date())) {
                    throw new JOSEException("Invalid token");
                }

                if (invalidateRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())){
                    throw new JOSEException("Invalid token");
                }
                return signedJWT;
            }
        //#################################################################
            public AuthenticationResponse refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException {
                var signedJWT = verifyToken(request.getToken(),true);

                var jit = signedJWT.getJWTClaimsSet().getJWTID();
                var expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

                InvalidateToken invalidatedToken = InvalidateToken.builder()
                        .id(jit)
                        .experiedTime(expiryTime)
                        .build();

                invalidateRepository.save(invalidatedToken);

                var customeremail = signedJWT.getJWTClaimsSet().getSubject();

                var customer = userRepository.findByEmail(customeremail);

                var token = generateToken(customer);

                return AuthenticationResponse.builder()
                        .token(token)
                        .build();
            }
        //#############################################
        public ResponseEntity<ResponseData<UserDTO>> CurrentUser(IntrospectRequest token) throws JOSEException, ParseException {


            SignedJWT signedJWT = SignedJWT.parse(token.getToken());
            String customerEmail = signedJWT.getJWTClaimsSet().getSubject();

            Users user = userRepository.findByEmail(customerEmail);

            UserDTO userDTO = UserDTO.fromEntity(user);

            ResponseData<UserDTO> responseData = ResponseData.<UserDTO>builder()
                    .status(StatusAndMessage.SUCCESS.getCode())
                    .message(StatusAndMessage.SUCCESS.getMessage())
                    .data(userDTO)
                    .build();
            return ResponseEntity.ok(responseData);
        }
        //###################################
        public ResponseEntity<ResponseData<Void>> logout(IntrospectRequest logoutRequest) throws ParseException, JOSEException {

            try {
                var signToken = verifyToken(logoutRequest.getToken(), true);

                String jid = signToken.getJWTClaimsSet().getJWTID();
                Date expityTime = signToken.getJWTClaimsSet().getExpirationTime();

                InvalidateToken invalidateToken = InvalidateToken.builder()
                        .id(jid)
                        .experiedTime(expityTime)
                        .build();

                invalidateRepository.save(invalidateToken);
                ResponseData<Void> responseData = ResponseData.<Void>builder()
                        .status(StatusAndMessage.SUCCESS.getCode())
                        .message(StatusAndMessage.SUCCESS.getMessage())
                        .data(null)
                        .build();
                return ResponseEntity.ok(responseData);
            }catch (Exception e) {
                throw new RuntimeException();
            }
        }
}
