package myweb.bookshopho4.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import myweb.bookshopho4.Enum.PaymentMethod;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {
    private Long userId;           // ID của người dùng
    private List<Long> itemId;  // Danh sách các sản phẩm trong giỏ hàng
    private PaymentMethod paymentMethod;
    private Long totalPrice;     // Tổng giá trị giỏ hàng

}
