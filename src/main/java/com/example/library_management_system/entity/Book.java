package com.example.library_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="title")
    private String title;
    @Column(name="author")
    private String author;
    @Column(name="genre")
    private String genre;
    @Column(name="isbn")
    private String isbn;
    @Column(name="price")
    private Float price;
    @Column(name="quanity")
    private Integer quantity;
    @Column(name="units_sold")
    private Integer unitsSold;
    @ManyToMany(cascade = {
            CascadeType.DETACH,CascadeType.MERGE,
            CascadeType.REFRESH,CascadeType.PERSIST,
    })
    @JoinTable(
            name = "purchases",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name="customer_id")
    )
    List<Customer> customers = new ArrayList<>();
    public Book(Long id, String title, String author, String genre, String isbn, Float price, Integer quantity, Integer unitsSold) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
        this.price = price;
        this.quantity = quantity;
        this.unitsSold = unitsSold;
    }
    public void add(Customer customer){
        customers.add(customer);
    }
}
