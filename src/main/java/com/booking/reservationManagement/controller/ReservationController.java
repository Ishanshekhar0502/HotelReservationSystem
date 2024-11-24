package com.booking.reservationManagement.controller;
import com.booking.reservationManagement.entity.Reservation;
import com.booking.reservationManagement.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public Reservation createReservation(@RequestBody Map<String, Object> request) {
        int roomId = Integer.valueOf(request.get("roomId").toString());
        String guestName = request.get("guestName").toString();
        LocalDate checkIn = LocalDate.parse(request.get("checkIn").toString());
        LocalDate checkOut = LocalDate.parse(request.get("checkOut").toString());

        return reservationService.createReservation(roomId, guestName, checkIn, checkOut);
    }
}
