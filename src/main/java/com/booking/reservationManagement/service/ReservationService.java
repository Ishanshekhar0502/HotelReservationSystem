package com.booking.reservationManagement.service;

import com.booking.reservationManagement.entity.Reservation;
import com.booking.reservationManagement.entity.RoomAvailability;
import com.booking.reservationManagement.repos.ReservationRepository;
import com.booking.reservationManagement.repos.RoomAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private RoomAvailabilityRepository availabilityRepository;

    @Autowired
    private ReservationRepository reservationRepository;
    @Transactional
    public Reservation createReservation(int roomId, String guestName, LocalDate checkIn, LocalDate checkOut) {
        // Check availability
        List<RoomAvailability> availableRooms = availabilityRepository.findAvailableRoomsForDates(roomId, checkIn, checkOut);

        int requiredDays = (int) (checkOut.toEpochDay() - checkIn.toEpochDay()) + 1;
        if (availableRooms.size() != requiredDays) {
            throw new RuntimeException("Room is not available for the selected dates");
        }

        // Update room availability
        availableRooms.forEach(ra -> {
            ra.setStatus(false);
            availabilityRepository.save(ra);
        });

        // Create reservation
        Reservation reservation = new Reservation();
        reservation.setRoom(availableRooms.get(0).getRoom());
        reservation.setGuestName(guestName);
        reservation.setCheckIn(checkIn);
        reservation.setCheckOut(checkOut);

        return reservationRepository.save(reservation);
    }

}
