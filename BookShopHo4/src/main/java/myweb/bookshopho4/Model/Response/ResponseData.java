package myweb.bookshopho4.Model.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData<T> implements Serializable {
    private int status;
    private  String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;


//    public ResponseData(int status, String message, T data) {
//        this.status = status;
//        this.message = message;
//        this.data = data;
//    }


    public ResponseData(int status, String message) {
        this.status = status;
        this.message = message;
    }
}

