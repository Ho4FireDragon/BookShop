package myweb.bookshopho4.Model.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;


@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn
    private Users customer; // Khách hàng sở hữu giỏ hàng

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL ,orphanRemoval = true)
    private List<Books> cartItems; // Danh sách sản phẩm trong giỏ

    private Long totalPrice;

}
