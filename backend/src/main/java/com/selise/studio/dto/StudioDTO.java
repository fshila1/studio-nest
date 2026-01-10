package com.selise.studio.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class StudioDTO {
    private Long id;
    private String name;
    private String type;
    private String area;
    private String address;
    private Double latitude;
    private Double longitude;
    private List<String> amenities;
    private BigDecimal pricePerHour;
    private Double rating;
    private List<String> images;
    private String description;
}
