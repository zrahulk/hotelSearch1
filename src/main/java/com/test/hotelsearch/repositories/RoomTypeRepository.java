package com.test.hotelsearch.repositories;

import com.test.hotelsearch.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType,Integer> {
    RoomType findByRoomTypeId(int id);
    RoomType findByName(String name);
}
