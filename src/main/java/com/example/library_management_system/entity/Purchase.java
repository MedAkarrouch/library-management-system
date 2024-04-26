package com.example.library_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;
import java.util.Date;

@Entity
@Table(name="purchases")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name="book_id",referencedColumnName = "id")
    private Book book;
    @ManyToOne
    @JoinColumn(name="customer_id",referencedColumnName = "id")
    private Customer customer;
    @Column(name="quantity")
    private Integer quantity;
    @Column(name="amount_paid")
    private Float amountPaid;
    @Column(name="date")
    private Date date;

    public Purchase(User user, Book book, Customer customer, Integer quantity, Float amountPaid, Date date) {
        this.user = user;
        this.book = book;
        this.customer = customer;
        this.quantity = quantity;
        this.amountPaid = amountPaid;
        this.date = date;
    }
}
