package myweb.bookshopho4.Controller;


import com.nimbusds.jose.JOSEException;
import myweb.bookshopho4.Model.Request.IntrospectRequest;
import myweb.bookshopho4.Model.Request.RefreshTokenRequest;
import myweb.bookshopho4.Model.Response.AuthenticationResponse;
import myweb.bookshopho4.Model.Response.IntrospectResponse;
import myweb.bookshopho4.Model.Response.ResponseData;
import myweb.bookshopho4.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/introspect")
    public ResponseEntity<ResponseData<IntrospectResponse>>  introspect(@RequestBody IntrospectRequest introspectRequest) throws ParseException, JOSEException {
        return authenticationService.introspectCustomer(introspectRequest);
    }

    @PostMapping("/refresh")
    public AuthenticationResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) throws ParseException, JOSEException {
        return authenticationService.refreshToken(refreshTokenRequest);
    }
}
