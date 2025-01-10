package myweb.bookshopho4.Exception;

import myweb.bookshopho4.Model.Response.ResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ResponseData> handlingRuntimeException(RuntimeException exception) {
        ResponseData responseData = new ResponseData();

        responseData.setStatus(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        responseData.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());

        return  ResponseEntity.badRequest().body(responseData);

        
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ResponseData> handlingAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ResponseData responseData = new ResponseData();

        responseData.setStatus(errorCode.getCode());
        responseData.setMessage(errorCode.getMessage());

        return  ResponseEntity.badRequest().body(responseData);


    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ResponseData> handlingMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        String enumkey = exception.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.valueOf(enumkey);

        ResponseData responseData = new ResponseData();

        responseData.setStatus(errorCode.getCode());
        responseData.setMessage(errorCode.getMessage());

        return  ResponseEntity.badRequest().body(responseData);
    }

}
