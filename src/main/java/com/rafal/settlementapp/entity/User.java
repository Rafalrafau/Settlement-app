package com.rafal.settlementapp.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private int id;

    @Column(name= "first_name")
    private String firstName;

    @Column(name= "last_name")
    private String lastName;

    @Column(name= "money")
    private double money;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
//    @JoinColumn(name = "room_id")
//    private Room room;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JsonIgnore
    private List<Expense> paidExpenses;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name= "user_expense", joinColumns =@JoinColumn(name = "user_id"), inverseJoinColumns =@JoinColumn(name="expense_id"))
    @JsonIgnore
    private List<Expense> sharedExpenses;

    public User(){
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

//    public Room getRoom() {
//        return room;
//    }
//
//    public void setRoom(Room room) {
//        this.room = room;
//    }

    public List<Expense> getPaidExpenses() {
        return paidExpenses;
    }

    public void setPaidExpenses(List<Expense> paidExpenses) {
        this.paidExpenses = paidExpenses;
    }

    public List<Expense> getSharedExpenses() {
        return sharedExpenses;
    }

    public void setSharedExpenses(List<Expense> sharedExpenses) {
        this.sharedExpenses = sharedExpenses;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", money=" + money +
                '}';
    }

}
