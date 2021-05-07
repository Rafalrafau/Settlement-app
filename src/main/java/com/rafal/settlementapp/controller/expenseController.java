package com.rafal.settlementapp.controller;

import com.rafal.settlementapp.dao.ExpenseRepository;
import com.rafal.settlementapp.dao.RoomRepository;
import com.rafal.settlementapp.dao.UserRepository;
import com.rafal.settlementapp.entity.Expense;
import com.rafal.settlementapp.entity.Room;
import com.rafal.settlementapp.entity.User;
import com.rafal.settlementapp.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rooms/{roomId}")
public class expenseController {

    @Autowired
    ExpenseService expenseService;

    @Autowired
    RoomRepository roomDAO;

    @GetMapping("/expenses")
    public List<Expense> getExpenses(@PathVariable int roomId){

        Optional<Room> result = roomDAO.findById(roomId);
        Room theRoom = null;

        if(result.isPresent()){
            theRoom = result.get();
            return expenseService.findAllExpenses(theRoom);
        }else{
            throw new RuntimeException("Did not find room id - " + roomId);
        }
    }

    @GetMapping("/expenses/{expenseId}")
    public Expense find(@PathVariable int expenseId){

        return expenseService.findExpense(expenseId);
    }

    @PostMapping("/expenses")
    public Expense addExpense(@RequestBody Expense theExpense){

       return expenseService.addExpense(theExpense);
    }

    @DeleteMapping("/expenses/{expenseId}")
    public void deleteExpense(@PathVariable int expenseId){

        expenseService.deleteExpense(expenseId);
    }





}
