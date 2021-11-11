package com.example.myproject.service.impl;

import com.example.myproject.model.entities.RoomTypeEntity;
import com.example.myproject.model.entities.enums.RoomEnum;
import com.example.myproject.model.view.RoomPricesView;
import com.example.myproject.repository.RoomRepository;
import com.example.myproject.service.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;


    public RoomServiceImpl(RoomRepository roomRepository, ModelMapper modelMapper) {
        this.roomRepository = roomRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void initRooms() {

        if (this.roomRepository.count() == 0) {
            RoomEnum[] values = RoomEnum.values();
            for (RoomEnum value : values) {
                RoomTypeEntity roomType = new RoomTypeEntity();
                switch (value.name().toUpperCase()) {
                    case "DOUBLE_ROOM":
                        roomType.setPrice(BigDecimal.valueOf(100));
                        break;
                    case "APARTMENT":
                        roomType.setPrice(BigDecimal.valueOf(150));
                        break;
                    case "STUDIO":
                        roomType.setPrice(BigDecimal.valueOf(75));
                        break;
                }
                roomType.setType(value);
                this.roomRepository.save(roomType);
            }
        }

    }

    @Override
    public RoomTypeEntity findRoomBy(RoomEnum roomEnum) {
        RoomTypeEntity roomTypeEntity = this.roomRepository.findByType(roomEnum).orElse(null);
        return roomTypeEntity;
    }

    @Override
    public List<RoomPricesView> getAllPrices() {
        List<RoomTypeEntity> all = this.roomRepository.findAll();
        List<RoomPricesView> collect = all
                .stream()
                .map(roomTypeEntity -> this.modelMapper.map(roomTypeEntity, RoomPricesView.class))
                .collect(Collectors.toList());
        return collect;
    }
}
