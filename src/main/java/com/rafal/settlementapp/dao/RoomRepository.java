package com.rafal.settlementapp.dao;

import com.rafal.settlementapp.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
}
