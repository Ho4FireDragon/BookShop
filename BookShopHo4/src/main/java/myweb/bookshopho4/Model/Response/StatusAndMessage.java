package myweb.bookshopho4.Model.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@NoArgsConstructor(force = true)
public enum StatusAndMessage {
    // Success messages
    SUCCESS(200, "Success", HttpStatus.OK),
    VALID_TOKEN(202, "Valid token", HttpStatus.ACCEPTED);



    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    StatusAndMessage(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
