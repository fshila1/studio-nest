package com.selise.studio.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "studios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Studio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type; // Music, Dance, etc.

    @Column(nullable = false)
    private String area;

    @Column(nullable = false)
    private String address;

    private Double latitude;
    private Double longitude;

    @ElementCollection
    @CollectionTable(name = "studio_amenities", joinColumns = @JoinColumn(name = "studio_id"))
    @Column(name = "amenity")
    private List<String> amenities;

    @Column(nullable = false)
    private BigDecimal pricePerHour;

    private Double rating;

    @ElementCollection
    @CollectionTable(name = "studio_images", joinColumns = @JoinColumn(name = "studio_id"))
    @Column(name = "image_url")
    private List<String> images;

    @Column(columnDefinition = "TEXT")
    private String description;
}
