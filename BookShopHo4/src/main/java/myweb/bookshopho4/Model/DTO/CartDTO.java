//package myweb.bookshopho4.Model.DTO;
//
//import lombok.*;
//import myweb.bookshopho4.Model.Entity.Cart;
//import myweb.bookshopho4.Model.Entity.CartItem;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class CartDTO {
//    private Long userId;              // ID người dùng
//    private List<Long> items;  // Danh sách sản phẩm trong giỏ hàng
//    private Double totalPrice;        // Tổng giá trị giỏ hàng
//
//    // Static method for mapping from entity to DTO
//    public static CartDTO fromEntity(Cart cart) {
//        if (cart == null) {
//            return null;
//        }
//        List<CartDTO> itemDTOs = cart.getItems().stream()
//                .map(CartItemDTO::fromEntity)
//                .collect(Collectors.toList());
//
//        return CartDTO.builder()
//                .userId(cart.getUser().getId())   // Assuming Cart has a reference to a User entity
//                .items(itemDTOs)
//                .totalPrice(cart.getTotalPrice())  // Assuming Cart has total price calculated
//                .build();
//    }
//
//    // Optional: Static method for mapping from DTO to entity
//    public static Cart toEntity(CartDTO cartDTO) {
//        if (cartDTO == null) {
//            return null;
//        }
//        Cart cart = new Cart();
//        cart.setUser(new Users()); // Assuming Cart has a reference to a User entity
//        cart.getUser().setId(cartDTO.getUserId());
//
//        List<CartItem> cartItems = cartDTO.getItems().stream()
//                .map(CartItemDTO::toEntity)
//                .collect(Collectors.toList());
//        cart.setItems(cartItems);
//
//        cart.setTotalPrice(cartDTO.getTotalPrice());
//
//        return cart;
//    }
//}
