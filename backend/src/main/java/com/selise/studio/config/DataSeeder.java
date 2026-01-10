package com.selise.studio.config;

import com.selise.studio.model.Studio;
import com.selise.studio.repository.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private StudioRepository studioRepository;

    @Override
    public void run(String... args) throws Exception {
        if (studioRepository.count() == 0) {
            seedStudios();
        }
    }

    private void seedStudios() {
        Studio s1 = new Studio();
        s1.setName("Melody Haven");
        s1.setType("Music");
        s1.setArea("Downtown");
        s1.setAddress("123 Music Lane, Downtown");
        s1.setLatitude(40.7128);
        s1.setLongitude(-74.0060);
        s1.setAmenities(Arrays.asList("Soundproof", "Piano", "Microphones", "Recording Gear"));
        s1.setPricePerHour(new BigDecimal("50.00"));
        s1.setRating(4.8);
        s1.setImages(Arrays.asList(
                "https://images.unsplash.com/photo-1598488035139-bdbb2231ce04?auto=format&fit=crop&w=800&q=80",
                "https://images.unsplash.com/photo-1514320291840-2e0a9bf2a9ae?auto=format&fit=crop&w=800&q=80"));
        s1.setDescription("Professional music studio with top-tier equipment.");

        Studio s2 = new Studio();
        s2.setName("Rhythm Space");
        s2.setType("Dance");
        s2.setArea("West End");
        s2.setAddress("456 Dance Blvd, West End");
        s2.setLatitude(40.7306);
        s2.setLongitude(-73.9352);
        s2.setAmenities(Arrays.asList("Mirrors", "Sprung Floor", "Sound System", "Changing Rooms"));
        s2.setPricePerHour(new BigDecimal("40.00"));
        s2.setRating(4.5);
        s2.setImages(Arrays.asList(
                "https://images.unsplash.com/photo-1508700115892-45ecd05ae2ad?auto=format&fit=crop&w=800&q=80"));
        s2.setDescription("Spacious dance studio suitable for ballet, hip-hop, and contemporary.");

        Studio s3 = new Studio();
        s3.setName("Pixel Perfect");
        s3.setType("Photography");
        s3.setArea("Uptown");
        s3.setAddress("789 Photo Ave, Uptown");
        s3.setLatitude(40.7589);
        s3.setLongitude(-73.9851);
        s3.setAmenities(Arrays.asList("Cyclorama", "Lighting Kit", "Green Screen", "Makeup Station"));
        s3.setPricePerHour(new BigDecimal("75.00"));
        s3.setRating(4.9);
        s3.setImages(Arrays.asList(
                "https://images.unsplash.com/photo-1527011046414-4781f1f94f8c?auto=format&fit=crop&w=800&q=80"));
        s3.setDescription("High-end photography studio with natural light and professional lighting.");

        Studio s4 = new Studio();
        s4.setName("Podcast Pro");
        s4.setType("Podcast");
        s4.setArea("Midtown");
        s4.setAddress("101 Cast St, Midtown");
        s4.setLatitude(40.7549);
        s4.setLongitude(-73.9840);
        s4.setAmenities(Arrays.asList("4 Mics", "Mixer", "Soundproof", "Video Recording"));
        s4.setPricePerHour(new BigDecimal("35.00"));
        s4.setRating(4.7);
        s4.setImages(Arrays.asList(
                "https://images.unsplash.com/photo-1590602847861-f357a9332bbc?auto=format&fit=crop&w=800&q=80"));
        s4.setDescription("Cozy podcast studio perfect for interviews and solo recording.");

        studioRepository.saveAll(Arrays.asList(s1, s2, s3, s4));
    }
}
