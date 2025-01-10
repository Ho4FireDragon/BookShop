package myweb.bookshopho4.Service;

import myweb.bookshopho4.Enum.Role;
import myweb.bookshopho4.Enum.Status;
import myweb.bookshopho4.Exception.AppException;
import myweb.bookshopho4.Exception.ErrorCode;
import myweb.bookshopho4.Model.DTO.UserDTO;
import myweb.bookshopho4.Model.Entity.Users;
import myweb.bookshopho4.Model.Request.UserRequest;
import myweb.bookshopho4.Model.Response.ResponseData;
import myweb.bookshopho4.Model.Response.StatusAndMessage;
import myweb.bookshopho4.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<List<UserDTO>> findAllUser() {
        List<Users> users = userRepository.findAll();
        List<UserDTO> userDTOList = users.stream()
                .map(UserDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDTOList);
    }

    public Boolean exsistingUserByEmail(String email) {
        return userRepository.existsUsersByEmail(email);
    }

    public ResponseEntity<ResponseData<UserDTO>> findUserByEmail(String email) {
        Users user = userRepository.findByEmail(email);
        if (user == null) {
            throw new AppException(ErrorCode.NOT_FOUND);
        }
        else {
            UserDTO userDTO = UserDTO.fromEntity(user);
            ResponseData<UserDTO> responseData = ResponseData.<UserDTO>builder()
                    .status(StatusAndMessage.SUCCESS.getCode())
                    .message(StatusAndMessage.SUCCESS.getMessage())
                    .data(userDTO)
                    .build();
            return ResponseEntity.ok(responseData);
        }
    }

    public ResponseEntity<ResponseData<UserDTO>> saveUser(UserRequest userRequest) {

        if (exsistingUserByEmail(userRequest.getEmail())==true) {
            throw new AppException(ErrorCode.EMAIL_EXISTS);
        }

        else {
            Users newUser = new Users();
            newUser.setUserName(userRequest.getName());
            newUser.setUserPassword(passwordEncoder.encode(userRequest.getPassword()));

            newUser.setEmail(userRequest.getEmail());
            newUser.setAddress(userRequest.getAddress());
            newUser.setPhoneNumber(userRequest.getPhone());

            newUser.setUserStatus(Status.ACTIVE);
            newUser.setRole(Role.CUSTOMER);

            Users savedUser = userRepository.save(newUser);
            UserDTO userDTO = UserDTO.fromEntity(savedUser);

            ResponseData<UserDTO> responseData = ResponseData.<UserDTO>builder()
                    .status(StatusAndMessage.SUCCESS.getCode())
                    .message(StatusAndMessage.SUCCESS.getMessage())
                    .data(userDTO)
                    .build();
            return ResponseEntity.ok(responseData);
        }
    }
}
