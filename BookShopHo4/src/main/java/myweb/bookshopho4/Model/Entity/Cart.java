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

    @OneToOne
    @JoinColumn
    private Users customer; // Khách hàng sở hữu giỏ hàng

    @OneToMany(cascade = CascadeType.ALL ,orphanRemoval = true)
    private List<Books> cartItems; // Danh sách sản phẩm trong giỏ

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private Long totalPrice;

}
