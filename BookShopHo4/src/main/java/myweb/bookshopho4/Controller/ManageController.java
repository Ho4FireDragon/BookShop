package myweb.bookshopho4.Controller;

import myweb.bookshopho4.Model.DTO.UserDTO;
import myweb.bookshopho4.Model.Entity.Books;
import myweb.bookshopho4.Model.Entity.Users;
import myweb.bookshopho4.Model.Response.ResponseData;
import myweb.bookshopho4.Service.BookService;
import myweb.bookshopho4.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/manager")
public class ManageController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;


    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return userService.findAllUser();
    }

    @PostMapping("/search-email")
    public ResponseEntity<ResponseData<UserDTO>> getUsersByEmail(@RequestBody String email) {
        return userService.findUserByEmail(email);
    }

    @PostMapping("/newbooks")
    public ResponseEntity<ResponseData<Books>> AddBook(@RequestBody Books book) {
        return bookService.AddNewBook(book);
    }

    @GetMapping("/books")
    public ResponseEntity<ResponseData<List<Books>>> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PutMapping("/update-book/{id}")
    public ResponseEntity<ResponseData<Books>> UpdateBook(@RequestBody Books book, @PathVariable Long id) {
        return bookService.UpdateBook(id, book);
    }

    @PutMapping("/delete-book/{id}")
    public ResponseEntity<ResponseData<Void>> DeleteBook(@PathVariable Long id) {
        return bookService.DeleteBook(id);
    }
}
