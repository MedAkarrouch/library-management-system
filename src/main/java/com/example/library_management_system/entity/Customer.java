package com.example.library_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="email")
    private String email;
    @Column(name="phone_number")
    private String phoneNumber;
    @ManyToOne(cascade={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private User user;
    @ManyToMany(cascade = {
            CascadeType.DETACH,CascadeType.MERGE,
            CascadeType.REFRESH,CascadeType.PERSIST,
    })
    @JoinTable(
            name = "purchases",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name="book_id")
    )
    private List<Book> books = new ArrayList<>();
    @OneToMany
    @JoinColumn(name = "customer_id")
    private List<Purchase> purchases = new ArrayList<>();

    public Customer(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
