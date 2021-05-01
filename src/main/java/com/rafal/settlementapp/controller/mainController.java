package com.rafal.settlementapp.controller;

import com.rafal.settlementapp.dao.ExpenseRepository;
import com.rafal.settlementapp.dao.GroupRepository;
import com.rafal.settlementapp.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo")
public class mainController {

    @Autowired
    private ExpenseRepository expenseService;

    @Autowired
    private UserRepository userService;

    @Autowired
    private GroupRepository groupService;

}
