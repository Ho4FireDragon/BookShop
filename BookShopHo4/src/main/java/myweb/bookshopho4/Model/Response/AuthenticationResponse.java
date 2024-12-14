package myweb.bookshopho4.Model.Response;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {
    public String email;
    public String token;
}
