package com.rafal.settlementapp.dao;

import com.rafal.settlementapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
