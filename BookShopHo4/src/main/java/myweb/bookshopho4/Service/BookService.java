package myweb.bookshopho4.Service;

import myweb.bookshopho4.Model.Entity.Books;
import myweb.bookshopho4.Model.Response.ResponseData;
import myweb.bookshopho4.Model.Response.StatusAndMessage;
import myweb.bookshopho4.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public ResponseEntity<ResponseData<List<Books>>> getAllBooks() {
        List<Books> books = bookRepository.findAll();
        ResponseData<List<Books>> response = ResponseData.<List<Books>>builder()
                .status(StatusAndMessage.SUCCESS.getCode())
                .message(StatusAndMessage.SUCCESS.getMessage())
                .data(books)
                .build();
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<ResponseData<Books>> AddNewBook(Books book) {
        Books newBook = bookRepository.save(book);

        ResponseData<Books> response = ResponseData.<Books>builder()
                .status(StatusAndMessage.SUCCESS.getCode())
                .message(StatusAndMessage.SUCCESS.getMessage())
                .data(newBook)
                .build();

        return ResponseEntity.ok(response);
    }

}
