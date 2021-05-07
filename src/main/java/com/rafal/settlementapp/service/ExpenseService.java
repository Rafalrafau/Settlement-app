package com.rafal.settlementapp.service;

import com.rafal.settlementapp.dao.ExpenseRepository;
import com.rafal.settlementapp.dao.RoomRepository;
import com.rafal.settlementapp.dao.UserRepository;
import com.rafal.settlementapp.entity.Expense;
import com.rafal.settlementapp.entity.Room;
import com.rafal.settlementapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    RoomRepository roomDAO;

    @Autowired
    UserRepository userDAO;

    @Autowired
    ExpenseRepository expenseDAO;


    public ExpenseService(){
    }

    public Expense addExpense(Expense theExpense){

        theExpense.setId(0);

        double expensePrice = theExpense.getPrice();

        double paidByCurrentMoney = theExpense.getUser().getMoney();

        theExpense.getUser().setMoney(expensePrice + paidByCurrentMoney);

        userDAO.save(theExpense.getUser());


        for(User paidForUser: theExpense.getUsers()){

            paidForUser.setMoney(paidForUser.getMoney() - expensePrice/theExpense.getUsers().size());
            userDAO.save(paidForUser);

        }

        return expenseDAO.save(theExpense);

    }

    public List<Expense> findAllExpenses(Room theRoom){

        List<Expense> expensesInGroup = new ArrayList<>();

       List<User> listOfUsers = theRoom.getUsers();

       for(User tempUser: listOfUsers){

           List<Expense> tempList = tempUser.getPaidExpenses();
           expensesInGroup.addAll(tempList);
       }

       return expensesInGroup;
    }

    public Expense findExpense(int expenseId){

        Optional<Expense> expenseResult = expenseDAO.findById(expenseId);
        Expense theExpense = null;

        if(expenseResult.isPresent()){
            theExpense = expenseResult.get();
        }else{
            throw new RuntimeException("Did not find expense id - " + expenseId);
        }

        return theExpense;
    }


    public void deleteExpense(int expenseId){

        Optional<Expense> expenseResult = expenseDAO.findById(expenseId);
        Expense theExpense = null;

        if(expenseResult.isPresent()){
            theExpense = expenseResult.get();

            double userMoney = theExpense.getUser().getMoney();

            theExpense.getUser().setMoney(userMoney - theExpense.getPrice());

            List<User> paidFor = theExpense.getUsers();

            for(User tempUser: paidFor){
                double tempUserCurrentMoney = tempUser.getMoney();
                tempUser.setMoney(tempUserCurrentMoney + theExpense.getPrice()/paidFor.size());
            }

            expenseDAO.deleteById(expenseId);

        }else{
            throw new RuntimeException("Did not find expense id - " + expenseId);
        }

    }

}
