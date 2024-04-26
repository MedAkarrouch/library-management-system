package com.example.library_management_system.repository;

import com.example.library_management_system.entity.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findAllByUserId(Long userId);
    Optional<Book> findByIdAndUserId(Long bookId , Long userId);
    void deleteByIdAndUserId(Long bookId , Long userId);
    boolean existsByIdAndUserId(Long id,Long userId);
}
