package myweb.bookshopho4.Service;

import myweb.bookshopho4.Enum.BookStatus;
import myweb.bookshopho4.Exception.AppException;
import myweb.bookshopho4.Exception.ErrorCode;
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

    public ResponseEntity<ResponseData<Books>> UpdateBook(Long id, Books bookDetails) {
        Books existingBook = bookRepository.findById(id).orElse(null);

        existingBook.setTitle(bookDetails.getTitle());
        existingBook.setAuthor(bookDetails.getAuthor());
        existingBook.setPublisher(bookDetails.getPublisher());
        existingBook.setIsbn(bookDetails.getIsbn());
        existingBook.setPrice(bookDetails.getPrice());
        existingBook.setStockQuantity(bookDetails.getStockQuantity());
        existingBook.setCategory(bookDetails.getCategory());
        existingBook.setPublishDate(bookDetails.getPublishDate());
        existingBook.setDescription(bookDetails.getDescription());
        existingBook.setCoverImageUrl(bookDetails.getCoverImageUrl());
        existingBook.setStatus(bookDetails.getStatus());

        // Lưu vào cơ sở dữ liệu
        bookRepository.save(existingBook);

        // Tạo ResponseData
        ResponseData<Books> response = ResponseData.<Books>builder()
                .status(StatusAndMessage.SUCCESS.getCode())
                .message(StatusAndMessage.SUCCESS.getMessage())
                .data(existingBook)
                .build();

        return ResponseEntity.ok(response);
        }

        public ResponseEntity<ResponseData<Void>> DeleteBook(Long id) {
            Books existingBook = bookRepository.findById(id).orElse(null);


            if (existingBook == null) {
                throw new AppException(ErrorCode.NOT_FOUND);
            }
            else {
                existingBook.setStatus(BookStatus.NOT_AVAILABLE);
                ResponseData<Void> response = ResponseData.<Void>builder()
                        .status(StatusAndMessage.SUCCESS.getCode())
                        .message(StatusAndMessage.SUCCESS.getMessage())
                        .data(null)
                        .build();
                return ResponseEntity.ok(response);
            }
        }
}
