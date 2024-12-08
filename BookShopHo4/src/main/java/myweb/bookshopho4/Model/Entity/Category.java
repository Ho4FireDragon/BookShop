package myweb.bookshopho4.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID duy nhất của thể loại (khóa chính)

    @Column(nullable = false, unique = true)
    private String name; // Tên thể loại (ví dụ: Khoa học viễn tưởng)

    private String description; // Mô tả ngắn về thể loại

    @OneToMany(mappedBy = "category")
    private List<Books> books; // Danh sách các sách thuộc thể loại này
}
