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
    @Column(name="quantity")
    private Integer quantity;
    @Transient
    private Integer unitsSold;
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "purchases",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name="customer_id")
    )
    private List<Customer> customers = new ArrayList<>();
    @OneToMany
    @JoinColumn(name = "book_id")
    private List<Purchase> purchases = new ArrayList<>();
    public Book(String title, String author, String genre, String isbn, Float price, Integer quantity) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getUnitsSold() {
        if(purchases == null)
            return 0;
        return purchases.stream().mapToInt(Purchase::getQuantity).sum();
    }

    public void add(Customer customer){
        customers.add(customer);
    }
}
