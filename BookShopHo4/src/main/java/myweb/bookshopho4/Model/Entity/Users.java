package myweb.bookshopho4.Model.Entity;


import jakarta.persistence.*;
import lombok.*;
import myweb.bookshopho4.Enum.Role;
import myweb.bookshopho4.Enum.Status;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String userPassword;

    private String email;

    private String phoneNumber;

    private String address;

    @Enumerated(EnumType.STRING)  // Store Role as string in database
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status userStatus ;


}
