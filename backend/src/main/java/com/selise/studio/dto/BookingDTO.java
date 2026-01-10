package com.selise.studio.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class BookingDTO {
    private Long id;
    private Long studioId;
    private String studioName;
    private String userName;
    private String email;
    private LocalDate bookingDate;
    private String timeSlot;
    private LocalDateTime createdAt;
}
