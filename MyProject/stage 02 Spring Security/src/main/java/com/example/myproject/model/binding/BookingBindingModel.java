package com.example.myproject.model.binding;

import com.example.myproject.model.entities.GuestEntity;
import com.example.myproject.model.entities.RoomTypeEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.util.Date;

public class BookingBindingModel {

    private String fullName;
    private String email;
    private String phoneNumber;
    private Date checkIn;
    private Date checkOut;
    private RoomTypeEntity room;
    private String notes;



    public String getFullName() {
        return fullName;
    }

    public BookingBindingModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public BookingBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public BookingBindingModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getCheckIn() {
        return checkIn;
    }

    public BookingBindingModel setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
        return this;
    }

    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getCheckOut() {
        return checkOut;
    }

    public BookingBindingModel setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
        return this;
    }

    public RoomTypeEntity getRoom() {
        return room;
    }

    public BookingBindingModel setRoom(RoomTypeEntity room) {
        this.room = room;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public BookingBindingModel setNotes(String notes) {
        this.notes = notes;
        return this;
    }
}
