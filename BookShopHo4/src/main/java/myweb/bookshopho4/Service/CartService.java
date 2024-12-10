package myweb.bookshopho4.Service;

import myweb.bookshopho4.Enum.PaymentMethod;
import myweb.bookshopho4.Model.DTO.UserDTO;
import myweb.bookshopho4.Model.Entity.Books;
import myweb.bookshopho4.Model.Entity.Cart;
import myweb.bookshopho4.Model.Entity.Users;
import myweb.bookshopho4.Model.Request.CartRequest;
import myweb.bookshopho4.Model.Response.ResponseData;
import myweb.bookshopho4.Model.Response.StatusAndMessage;
import myweb.bookshopho4.Repository.BookRepository;
import myweb.bookshopho4.Repository.CartRepository;
import myweb.bookshopho4.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    public ResponseEntity<ResponseData<Cart>> addCart(CartRequest cart) {
        Users customer = userRepository.findById(cart.getUserId()).orElse(null) ;
        List<Books> books = bookRepository.findAllById(cart.getItemId());
        PaymentMethod paymentMethod = cart.getPaymentMethod();
        Long totalprice = cart.getTotalPrice();

        Cart currentCart = new Cart();
        currentCart.setCustomer(customer);           // Gắn người dùng vào giỏ hàng
        currentCart.setCartItems(books);             // Gắn danh sách sách
        currentCart.setPaymentMethod(paymentMethod); // Phương thức thanh toán
        currentCart.setTotalPrice(totalprice);// Tổng giá tiền

        cartRepository.save(currentCart);
        ResponseData<Cart> responseData = ResponseData.<Cart>builder()
                .status(StatusAndMessage.SUCCESS.getCode())
                .message(StatusAndMessage.SUCCESS.getMessage())
                .data(currentCart)
                .build();

        return ResponseEntity.ok(responseData);
    }
}
