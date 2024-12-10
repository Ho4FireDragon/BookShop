package myweb.bookshopho4.Repository;

import myweb.bookshopho4.Model.Entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Books, Long> {
    List<Books> findByAuthor(String author);
    List<Books> findByTitle(String title);
    Books findById(long id);
    Books save(Books books);
}
