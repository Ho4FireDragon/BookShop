package myweb.bookshopho4.Model.DTO;

import lombok.*;
import myweb.bookshopho4.Model.Entity.Users;
import org.springframework.security.core.userdetails.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String name;
    private String email;
    private String phone;
    private String address;

    // Static method for mapping from entity to DTO
    public static UserDTO fromEntity(Users user) {
        if (user == null) {
            return null;
        }
        return UserDTO.builder()
                .name(user.getUserName())
                .email(user.getEmail())
                .phone(user.getPhoneNumber())
                .address(user.getAddress())
                .build();
    }

    // Optional: Static method for mapping from DTO to entity
    public static Users toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        Users user = new Users();
        user.setUserName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhone());
        user.setAddress(userDTO.getAddress());
        return user;
    }
}

