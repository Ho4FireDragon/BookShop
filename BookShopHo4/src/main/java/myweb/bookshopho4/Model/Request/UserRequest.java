package myweb.bookshopho4.Model.Request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequest {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;

}
