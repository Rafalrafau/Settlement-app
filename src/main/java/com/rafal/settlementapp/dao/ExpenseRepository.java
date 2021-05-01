package com.rafal.settlementapp.dao;


import com.rafal.settlementapp.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository <Expense, Integer> {
}
