package myweb.bookshopho4.Controller;

import myweb.bookshopho4.Model.DTO.UserDTO;
import myweb.bookshopho4.Model.Entity.Users;
import myweb.bookshopho4.Model.Response.ResponseData;
import myweb.bookshopho4.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/manager")
public class ManageController {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return userService.findAllUser();
    }

    @PostMapping("/search-email")
    public ResponseEntity<ResponseData<UserDTO>> getUsersByEmail(@RequestBody String email) {
        return userService.findUserByEmail(email);
    }
}
