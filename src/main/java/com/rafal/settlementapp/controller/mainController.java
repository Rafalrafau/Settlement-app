package com.rafal.settlementapp.controller;

import com.rafal.settlementapp.dao.ExpenseRepository;
import com.rafal.settlementapp.dao.RoomRepository;
import com.rafal.settlementapp.dao.UserRepository;
import com.rafal.settlementapp.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class mainController {

    @Autowired
    private ExpenseRepository expenseDAO;

    @Autowired
    private UserRepository userDAO;

    @Autowired
    private RoomRepository roomDAO;

    @GetMapping("/rooms")
    public List<Room> findAll(){
        return roomDAO.findAll();
    }

    @GetMapping("/rooms/{roomId}")
    public Room find(@PathVariable int roomId){

       Optional<Room> result = roomDAO.findById(roomId);
       Room theRoom = null;

       if(result.isPresent()){
           theRoom = result.get();
       }else{
           throw new RuntimeException("Did not find room id - " +roomId);
       }
        return theRoom;
    }

    @PostMapping("/rooms")
    public Room addRoom(@RequestBody Room theRoom){

        theRoom.setId(0);
        roomDAO.save(theRoom);

        return theRoom;
    }

    @PutMapping("/rooms")
    public Room updateRoom(@RequestBody Room theRoom){

        roomDAO.save(theRoom);
        return theRoom;
    }

    @DeleteMapping("/rooms/{roomId}")
    public void deleteRoom(@PathVariable int roomId){

        Optional<Room> result = roomDAO.findById(roomId);
        Room theRoom = null;

        if(result.isPresent()){
            theRoom = result.get();
        }else{
            throw new RuntimeException("Did not find room id - " + roomId);
        }

        roomDAO.deleteById(roomId);
    }



}
