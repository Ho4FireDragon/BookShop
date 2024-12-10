package myweb.bookshopho4.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import myweb.bookshopho4.Enum.BookStatus;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Books {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String title; // Tiêu đề sách
        private String author; // Tác giả
        private String publisher; // Nhà xuất bản
        private String isbn; // Mã ISBN
        private Double price; // Giá
        private Integer stockQuantity; // Số lượng trong kho
        private String category; // Thể loại
        private LocalDate publishDate; // Ngày xuất bản
        private String description; // Mô tả chi tiết sách
        private String coverImageUrl; // Link ảnh bìa

        @Enumerated(EnumType.STRING)
        private BookStatus status;
    }


