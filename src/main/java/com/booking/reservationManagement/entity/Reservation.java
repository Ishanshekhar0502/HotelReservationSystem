package com.booking.reservationManagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reservationId;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    private String guestName;
    private LocalDate checkIn;
    private LocalDate checkOut;
}
