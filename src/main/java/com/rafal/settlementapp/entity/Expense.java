package com.rafal.settlementapp.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id")
    private int id;

    @Column( name = "name")
    private String name;

    @Column( name = "price")
    private double price;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "user_expense", joinColumns = @JoinColumn(name = "expense_id"), inverseJoinColumns = @JoinColumn(name="user_id"))
    private List<User> users;

    public Expense(){

    }

    public Expense(String name, double price){
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
