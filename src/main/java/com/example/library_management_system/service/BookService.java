package com.example.library_management_system.service;

import com.example.library_management_system.body.request.StoreBookRequest;
import com.example.library_management_system.body.request.UpdateBookRequest;
import com.example.library_management_system.entity.Book;
import com.example.library_management_system.entity.User;
import com.example.library_management_system.exception.NotFountException;
import com.example.library_management_system.repository.BookRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    final private BookRepository bookRepository;
    public List<Book> getBooks(User user){
        return bookRepository.findAllByUserId(user.getId());
    }
    public Book getBook(Long bookId,User user){
        return bookRepository.findByIdAndUserId(bookId,user.getId()).orElseThrow(()->new NotFountException("Book not found"));
    }
    public Book addBook(StoreBookRequest req,User user){
        Book book = Book.builder()
                .genre(req.getGenre()).title(req.getTitle())
                .isbn(req.getIsbn()).price(req.getPrice())
                .author(req.getAuthor()).quantity(req.getQuantity())
                .user(user)
                .build();
        return bookRepository.save(book);
    }
    public void deleteBook(Long bookId,User user){
        Book book = bookRepository.findByIdAndUserId(bookId,user.getId()).
                orElseThrow(()->new NotFountException("Book not found"));
        bookRepository.delete(book);
    }
    public Book updateBook(Book book, User user){
        if(!bookRepository.existsByIdAndUserId(book.getId(), user.getId()))
            throw new NotFountException("Book not found");
        book.setUser(user);
        return bookRepository.save(book);
    }
}
