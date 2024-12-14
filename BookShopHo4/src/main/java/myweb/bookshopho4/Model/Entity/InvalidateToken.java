package myweb.bookshopho4.Model.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvalidateToken {
    @Id
    String id;
    Date experiedTime;
}
