package com.rafal.settlementapp.dao;

import com.rafal.settlementapp.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Integer> {
}
