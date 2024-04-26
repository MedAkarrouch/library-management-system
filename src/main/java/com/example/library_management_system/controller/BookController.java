package com.example.library_management_system.controller;

import com.example.library_management_system.body.request.StoreBookRequest;
import com.example.library_management_system.body.request.UpdateBookRequest;
import com.example.library_management_system.entity.Book;
import com.example.library_management_system.entity.User;
import com.example.library_management_system.repository.BookRepository;
import com.example.library_management_system.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    final private BookService bookService;
    @GetMapping
    public ResponseEntity<List<Book>> getBooks(HttpServletRequest request){
        User user = (User)request.getAttribute("user");
        List<Book> books = bookService.getBooks(user);
        return ResponseEntity.ok(books);
    }
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody StoreBookRequest requestBody, HttpServletRequest request){
        User user = (User)request.getAttribute("user");
        Book book = bookService.addBook(requestBody,user);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }
    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBook(@PathVariable Long bookId,HttpServletRequest request){
        User user = (User)request.getAttribute("user");
        Book book = bookService.getBook(bookId,user);
        return ResponseEntity.ok(book);
    }
    @DeleteMapping("/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable Long bookId,HttpServletRequest request){
        User user = (User)request.getAttribute("user");
        bookService.deleteBook(bookId,user);
        return ResponseEntity.noContent().build();
    }
    @PutMapping
    public ResponseEntity<Book> updateBook(@RequestBody Book book, HttpServletRequest request){
        User user = (User)request.getAttribute("user");
        Book updatedBook = bookService.updateBook(book,user);
        return ResponseEntity.ok(updatedBook);
    }
}
