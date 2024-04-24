package com.example.library_management_system.controller;

import com.example.library_management_system.body.request.StoreBookRequest;
import com.example.library_management_system.entity.Book;
import com.example.library_management_system.repository.BookRepository;
import com.example.library_management_system.service.BookService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    final private BookService bookService;
    @GetMapping
    public ResponseEntity<List<Book>> getBooks(){
        List<Book> books = bookService.getBooks();
        return ResponseEntity.ok(books);
    }
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody StoreBookRequest request){
        Book book = bookService.saveBook(new Book(null,
                request.getTitle(),request.getAuthor(),
                request.getGenre(),request.getIsbn(),
                request.getPrice(),request.getQuantity(),
                request.getUnitsSold()));
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }
    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBook(@PathVariable Long bookId){
        Book book = bookService.getBook(bookId);
        return ResponseEntity.ok(book);
    }
    @DeleteMapping("/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable Long bookId){
        bookService.deleteBook(bookId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping
    public ResponseEntity<Book> updateBook(@RequestBody Book book){
        Book updatedBook = bookService.updateBook(book);
        return ResponseEntity.ok(updatedBook);
    }
}
