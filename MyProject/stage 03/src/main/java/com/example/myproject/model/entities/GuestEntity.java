package com.example.myproject.model.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "guests")
public class GuestEntity extends BaseEntity {

    private String username;
    private String email;

    private Set<MessageEntity> messages;
    private Set<BookingEntity> bookings;

    public GuestEntity() {
        this.messages = new HashSet<>();
        this.bookings = new HashSet<>();
    }

    @Column(nullable = false)
    public String getUsername() {
        return username;
    }

    public GuestEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    @Column(nullable = false)
    public String getEmail() {
        return email;
    }

    public GuestEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    @OneToMany(mappedBy = "guest", fetch = FetchType.EAGER)
    public Set<MessageEntity> getMessages() {
        return messages;
    }

    public GuestEntity setMessages(Set<MessageEntity> messages) {
        this.messages = messages;
        return this;
    }

    @OneToMany(mappedBy = "guest", fetch = FetchType.EAGER)
    public Set<BookingEntity> getBookings() {
        return bookings;
    }

    public GuestEntity setBookings(Set<BookingEntity> bookings) {
        this.bookings = bookings;
        return this;
    }
}
