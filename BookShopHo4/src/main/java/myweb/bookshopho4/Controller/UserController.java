package myweb.bookshopho4.Controller;


import myweb.bookshopho4.Model.DTO.UserDTO;
import myweb.bookshopho4.Model.Entity.Users;
import myweb.bookshopho4.Model.Request.UserRequest;
import myweb.bookshopho4.Model.Response.ResponseData;
import myweb.bookshopho4.Model.Response.StatusAndMessage;
import myweb.bookshopho4.Repository.UserRepository;
import myweb.bookshopho4.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register") //Register
    public ResponseEntity<ResponseData<UserDTO>> AddUser(@RequestBody UserRequest userRequest) {
        return userService.saveUser(userRequest);
    }

//    @PostMapping("/cart/add")
//    public ResponseEntity<ResponseData<UserDTO>> AddCart(@RequestBody CartRequest cartRequest) {
//
//    }







}
