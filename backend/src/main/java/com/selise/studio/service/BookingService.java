package com.selise.studio.service;

import com.selise.studio.dto.BookingDTO;
import com.selise.studio.dto.BookingRequestDTO;
import com.selise.studio.exception.ConflictException;
import com.selise.studio.exception.ResourceNotFoundException;
import com.selise.studio.model.Booking;
import com.selise.studio.model.Studio;
import com.selise.studio.repository.BookingRepository;
import com.selise.studio.repository.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private StudioRepository studioRepository;

    @Transactional
    public BookingDTO createBooking(BookingRequestDTO request) {
        // 1. Check if studio exists
        Studio studio = studioRepository.findById(request.getStudioId())
                .orElseThrow(() -> new ResourceNotFoundException("Studio not found with id: " + request.getStudioId()));

        // 2. Check for conflict
        if (bookingRepository.existsByStudioIdAndBookingDateAndTimeSlot(
                request.getStudioId(), request.getBookingDate(), request.getTimeSlot())) {
            throw new ConflictException("Time slot " + request.getTimeSlot() + " is already booked for this date.");
        }

        // 3. Save booking
        Booking booking = new Booking();
        booking.setStudio(studio);
        booking.setUserName(request.getUserName());
        booking.setEmail(request.getEmail());
        booking.setBookingDate(request.getBookingDate());
        booking.setTimeSlot(request.getTimeSlot());

        Booking savedBooking = bookingRepository.save(booking);
        return mapToDTO(savedBooking);
    }

    public List<BookingDTO> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<String> getBookedSlots(Long studioId, LocalDate date) {
        return bookingRepository.findByStudioIdAndBookingDate(studioId, date).stream()
                .map(Booking::getTimeSlot)
                .collect(Collectors.toList());
    }

    private BookingDTO mapToDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());
        dto.setStudioId(booking.getStudio().getId());
        dto.setStudioName(booking.getStudioName());
        dto.setUserName(booking.getUserName());
        dto.setEmail(booking.getEmail());
        dto.setBookingDate(booking.getBookingDate());
        dto.setTimeSlot(booking.getTimeSlot());
        dto.setCreatedAt(booking.getCreatedAt());
        return dto;
    }
}
