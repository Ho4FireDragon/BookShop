package myweb.bookshopho4.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import myweb.bookshopho4.Enum.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id") // Tên cột khóa ngoại trong bảng Cart
    private Users customer; // Một khách hàng có thể sở hữu nhiều giỏ hàng

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "cart_books", // Tên bảng trung gian
            joinColumns = @JoinColumn(name = "cart_id"), // Khóa ngoại từ bảng Cart
            inverseJoinColumns = @JoinColumn(name = "book_id") // Khóa ngoại từ bảng Books
    )
    private List<Books> cartItems; // Danh sách sản phẩm trong giỏ

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private Long totalPrice;

}
