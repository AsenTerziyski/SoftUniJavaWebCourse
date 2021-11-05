package com.example.myproject.service;

import com.example.myproject.model.entities.RoomTypeEntity;
import com.example.myproject.model.entities.enums.RoomEnum;

public interface RoomService {
    void initRooms();
    RoomTypeEntity findRoomBy(RoomEnum roomEnum);
}
