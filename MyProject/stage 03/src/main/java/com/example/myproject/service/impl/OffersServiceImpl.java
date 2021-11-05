package com.example.myproject.service.impl;

import com.example.myproject.model.binding.OfferAddBindingModel;
import com.example.myproject.model.entities.OffersEntity;
import com.example.myproject.model.entities.RoomTypeEntity;
import com.example.myproject.model.entities.UserEntity;
import com.example.myproject.model.entities.enums.RoomEnum;
import com.example.myproject.model.view.OfferSummaryView;
import com.example.myproject.repository.OffersRepository;
import com.example.myproject.repository.RoomRepository;
import com.example.myproject.service.OffersService;
import com.example.myproject.service.RoomService;
import com.example.myproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OffersServiceImpl implements OffersService {
    private final OffersRepository offersRepository;
    private final RoomService roomService;
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public OffersServiceImpl(OffersRepository offersRepository, RoomService roomService, RoomRepository roomRepository, ModelMapper modelMapper, UserService userService) {
        this.offersRepository = offersRepository;
        this.roomService = roomService;
        this.roomRepository = roomRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


//    @Transactional
    @Override
    public boolean addOffer(OfferAddBindingModel offerAddBindingModel, Principal principal) {

        RoomEnum room = offerAddBindingModel.getRoom();
        OffersEntity byRoom = this.offersRepository.findByRoom(room);
        if (byRoom != null) {
            String username = principal.getName();
            UserEntity userByUsername = this.userService.findUserByUsername(username);
            byRoom.setUser(userByUsername);
            byRoom.setDiscount(offerAddBindingModel.getDiscount());
            byRoom.setPrice(offerAddBindingModel.getPrice());
            byRoom.setDescription(offerAddBindingModel.getDescription());
            byRoom.setStay(offerAddBindingModel.getStay());
            this.offersRepository.save(byRoom);
            RoomTypeEntity roomBy = this.roomService.findRoomBy(room);
            roomBy.setPrice(offerAddBindingModel.getPrice());
            this.roomRepository.save(roomBy);
            return true;
        }

        OffersEntity newOffer = this.modelMapper.map(offerAddBindingModel, OffersEntity.class);
        String username = principal.getName();
        UserEntity userByUsername = this.userService.findUserByUsername(username);
        if (userByUsername !=null) {
            newOffer.setUser(userByUsername);
            newOffer.setRoom(room);
            this.offersRepository.save(newOffer);
            RoomTypeEntity roomBy = this.roomService.findRoomBy(room);
            roomBy.setPrice(offerAddBindingModel.getPrice());
            this.roomRepository.save(roomBy);
            return true;
        }

        return false;
    }

    @Override
    public List<OfferSummaryView> getAllOffers() {
        List<OffersEntity> all = this.offersRepository.findAll();
        List<OfferSummaryView> collect = all.stream().map(offersEntity -> {
            OfferSummaryView map = this.modelMapper.map(offersEntity, OfferSummaryView.class);
            String name = offersEntity.getRoom().name();
            if (name.equals("DOUBLE_ROOM")) {
                name = "DOUBLE ROOM";
            }
            map.setRoom(name);
            return map;
        }).collect(Collectors.toList());
//        List<OfferSummaryView> collect = all
//                .stream()
//                .map(offersEntity -> this.modelMapper.map(offersEntity, OfferSummaryView.class))
//                .collect(Collectors.toList());
        return collect;
    }
}
