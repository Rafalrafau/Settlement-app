package com.rafal.settlementapp.service;

import com.rafal.settlementapp.dao.ExpenseRepository;
import com.rafal.settlementapp.dao.RoomRepository;
import com.rafal.settlementapp.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    RoomRepository roomDAO;

    @Autowired
    UserRepository userDAO;

    @Autowired
    ExpenseRepository expenseDAO;


}
