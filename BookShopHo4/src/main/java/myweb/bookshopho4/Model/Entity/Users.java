package myweb.bookshopho4.Model.Entity;


import jakarta.persistence.*;
import lombok.*;

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

    public enum Role {
        CUSTOMER,
        ADMIN,
        STAFF
    }

    @Enumerated(EnumType.STRING)
    private status userStatus ;

    public enum status {
        ACTIVE,
        INACTIVE,
    }
}
