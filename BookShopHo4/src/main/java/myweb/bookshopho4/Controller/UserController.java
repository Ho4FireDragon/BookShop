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

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/create")
    public ResponseEntity<ResponseData<UserDTO>> AddUser(@RequestBody UserRequest userRequest) {
        // Tạo mới người dùng
        Users newUser = new Users();
        newUser.setUserName(userRequest.getName());
        newUser.setUserPassword(userRequest.getPassword());
        newUser.setEmail(userRequest.getEmail());
        newUser.setAddress(userRequest.getAddress());
        newUser.setPhoneNumber(userRequest.getPhone());

        // Lưu entity
        Users savedUser = userService.saveUser(newUser);

        // Chuyển từ entity sang DTO
        UserDTO userDTO = UserDTO.fromEntity(savedUser);

        // Tạo response
        ResponseData<UserDTO> responseData = ResponseData.<UserDTO>builder()
                .status(StatusAndMessage.SUCCESS.getCode())
                .message(StatusAndMessage.SUCCESS.getMessage())
                .data(userDTO)
                .build();

        // Trả về response
        return ResponseEntity.ok(responseData);
    }




}
