package myweb.bookshopho4.Service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
public class AuthenticationService {

    @Value("${jwt.signJWT}")
    protected String signJWT;

    @Value("${jwt.valid-duration}")
    @NonFinal
    protected Long validDuration;

    private String generateToken(String email) throws JOSEException {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(email)
                .issuer("BookShopHo4.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(validDuration, ChronoUnit.MINUTES).toEpochMilli()
                ))
                .jwtID(UUID.randomUUID().toString())
                .claim("BookShop")
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
}
