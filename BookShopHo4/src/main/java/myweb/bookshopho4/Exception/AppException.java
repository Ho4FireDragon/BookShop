package myweb.bookshopho4.Exception;


import lombok.Getter;
import lombok.Setter;
import myweb.bookshopho4.Model.Response.StatusAndMessage;


@Getter
@Setter
public class AppException extends RuntimeException {

    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode ;
    }

    private ErrorCode errorCode;

}
