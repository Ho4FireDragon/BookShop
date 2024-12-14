package myweb.bookshopho4.Controller;


import com.nimbusds.jose.JOSEException;
import myweb.bookshopho4.Model.DTO.UserDTO;
import myweb.bookshopho4.Model.Entity.Cart;
import myweb.bookshopho4.Model.Entity.Users;
import myweb.bookshopho4.Model.Request.CartRequest;
import myweb.bookshopho4.Model.Request.IntrospectRequest;
import myweb.bookshopho4.Model.Request.LoginRequest;
import myweb.bookshopho4.Model.Request.UserRequest;
import myweb.bookshopho4.Model.Response.AuthenticationResponse;
import myweb.bookshopho4.Model.Response.ResponseData;
import myweb.bookshopho4.Model.Response.StatusAndMessage;
import myweb.bookshopho4.Repository.UserRepository;
import myweb.bookshopho4.Service.AuthenticationService;
import myweb.bookshopho4.Service.CartService;
import myweb.bookshopho4.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register") //Register
    public ResponseEntity<ResponseData<UserDTO>> AddUser(@RequestBody UserRequest userRequest) {
        return userService.saveUser(userRequest);
    }

    @PostMapping("/cart/add")
    public ResponseEntity<ResponseData<Cart>> AddCart(@RequestBody CartRequest cartRequest) {
        return cartService.addCart(cartRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseData<AuthenticationResponse>> Login(@RequestBody LoginRequest loginRequest) throws JOSEException {
        return authenticationService.authenticateCustomer(loginRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<ResponseData<Void>> Logout(@RequestBody IntrospectRequest token) throws ParseException, JOSEException {
        return authenticationService.logout(token);
    }

    @PostMapping("/me")
    public ResponseEntity<ResponseData<UserDTO>> CurrentUser(@RequestBody IntrospectRequest token) throws ParseException, JOSEException {
        return authenticationService.CurrentUser(token);
    }


}
