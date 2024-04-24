package com.example.library_management_system.service;

import com.example.library_management_system.body.request.StoreBookRequest;
import com.example.library_management_system.entity.Book;
import com.example.library_management_system.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    final private BookRepository bookRepository;
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }
    public Book getBook(Long id){
        Optional<Book> book =  bookRepository.findById(id);
        if(book.isPresent())
            return book.get();
        throw new RuntimeException("Book Not Found with id = "+id);
    }
    public Book saveBook(Book book){
        return bookRepository.save(book);
    }
    public void deleteBook(Long id){
        Book book = getBook(id);
        bookRepository.deleteById(id);
    }
    public Book updateBook(Book book    ){
        if(!bookRepository.existsById(book.getId()))
            throw new RuntimeException("Book not found with it = "+book.getId());
        return saveBook(book);
    }
}
