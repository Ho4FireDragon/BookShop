package myweb.bookshopho4.Repository;

import myweb.bookshopho4.Model.Entity.InvalidateToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;

@Repository
public interface InvalidateRepository extends JpaRepository<InvalidateToken, String>{

}
