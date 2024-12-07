package myweb.bookshopho4.Controller;

import myweb.bookshopho4.Model.DTO.UserDTO;
import myweb.bookshopho4.Model.Entity.Users;
import myweb.bookshopho4.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/manager")
public class ManageController {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<Users> users = userService.findAllUser();
        List<UserDTO> userDTOList = users.stream()
                .map(UserDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDTOList);
    }

}
