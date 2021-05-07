package com.rafal.settlementapp.controller;

import com.rafal.settlementapp.dao.ExpenseRepository;
import com.rafal.settlementapp.dao.RoomRepository;
import com.rafal.settlementapp.dao.UserRepository;
import com.rafal.settlementapp.entity.Room;
import com.rafal.settlementapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rooms/{roomId}")
public class userController {

    @Autowired
    RoomRepository roomDAO;

    @Autowired
    UserRepository userDAO;

    @Autowired
    ExpenseRepository expenseDAO;

    @GetMapping("/users")
    public List<User> findAll(@PathVariable int roomId ){

        Optional<Room> result = roomDAO.findById(roomId);
        Room theRoom = null;

        if(result.isPresent()){
            theRoom = result.get();
        }else{
            throw new RuntimeException("Did not find room id - " + roomId);
        }

        return theRoom.getUsers();
    }

    @GetMapping("/users/{userId}")
    public User find(@PathVariable int roomId, @PathVariable int userId){

        Optional<Room> roomResult = roomDAO.findById(roomId);
        Room theRoom = null;

        if(roomResult.isPresent()){
            theRoom = roomResult.get();
        }else{
            throw new RuntimeException("Did not find room id - " + roomId);
        }

        Optional<User> userResult = userDAO.findById(userId);
        User theUser = null;
        if(userResult.isPresent()){
            theUser = userResult.get();
        }else{
            throw new RuntimeException("Did not find user id - " + userId);
        }

        if(theRoom.getUsers().contains(theUser)){
            return theUser;
        }else {
            throw new RuntimeException("Did not find user id - " + userId + "in current group");
        }
    }

    @PostMapping("/users")
    public User addUser(@PathVariable int roomId, @RequestBody User theUser){

        Optional<Room> result = roomDAO.findById(roomId);
        Room theRoom = null;

        if(result.isPresent()){
            theRoom = result.get();
        }else{
            throw new RuntimeException("Did not find room id - " + roomId);
        }

        theUser.setId(0);
        theRoom.add(theUser);
        userDAO.save(theUser);

        return theUser;
    }

    @PutMapping("/users")
    public User updateUser(@PathVariable int roomId, @RequestBody User theUser){

        Optional<Room> result = roomDAO.findById(roomId);
        Room theRoom = null;

        if(result.isPresent()){
            theRoom = result.get();
        }else{
            throw new RuntimeException("Did not find room id - " + roomId);
        }

        int tempId = theUser.getId();
        Optional<User> tempResult = userDAO.findById(tempId);

        if(tempResult.isPresent()){

            User tempUser = tempResult.get();

            if(theRoom.getUsers().contains(tempUser)){
                userDAO.save(theUser);
                return theUser;
            }else{
                throw new RuntimeException("Did not find user id - " + tempId + "in current group");
            }

        }else{
            throw new RuntimeException("Did not find user id - " + tempId);
        }
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable int roomId, @PathVariable int userId ) {

        Optional<Room> result = roomDAO.findById(roomId);
        Room theRoom = null;

        if(result.isPresent()){
            theRoom = result.get();
        }else{
            throw new RuntimeException("Did not find room id - " + roomId);
        }

        Optional<User> userResult = userDAO.findById(userId);
        User theUser = null;

        if(userResult.isPresent()){

            theUser = userResult.get();

            if(theRoom.getUsers().contains(theUser)){
                theRoom.getUsers().remove(theUser);

                userDAO.deleteById(userId);
            }else{
                throw new RuntimeException("Did not find user id - " + userId + "in current room");
            }
        }else{
            throw new RuntimeException("Did not find user id - " + userId);
        }
    }
}
