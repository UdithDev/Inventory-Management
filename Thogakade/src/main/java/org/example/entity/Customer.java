//package com.inventorysystem.entity;
//
//
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Table(name = "customers")
//public class Customer {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private String id;
//
//    @Column(name = "name", nullable = false)
//    private String name;
//
//    @Column(name = "email", nullable = false, unique = true)
//    private String email;
//
//    @Column(name = "phone", nullable = false)
//    private String phone;
//
//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Order> orders;
//
//    // Constructors, getters, and setters
//}
