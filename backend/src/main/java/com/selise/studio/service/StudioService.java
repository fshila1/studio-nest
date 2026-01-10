package com.selise.studio.service;

import com.selise.studio.dto.StudioDTO;
import com.selise.studio.exception.ResourceNotFoundException;
import com.selise.studio.model.Studio;
import com.selise.studio.repository.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudioService {

    @Autowired
    private StudioRepository studioRepository;

    public List<StudioDTO> getAllStudios() {
        return studioRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public StudioDTO getStudioById(Long id) {
        Studio studio = studioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Studio not found with id: " + id));
        return mapToDTO(studio);
    }

    public List<StudioDTO> searchStudios(String area) {
        if (area == null || area.trim().isEmpty()) {
            return getAllStudios();
        }
        return studioRepository.findByAreaContainingIgnoreCase(area).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<StudioDTO> getNearbyStudios(double lat, double lng, double radius) {
        return studioRepository.findNearbyStudios(lat, lng, radius).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private StudioDTO mapToDTO(Studio studio) {
        StudioDTO dto = new StudioDTO();
        dto.setId(studio.getId());
        dto.setName(studio.getName());
        dto.setType(studio.getType());
        dto.setArea(studio.getArea());
        dto.setAddress(studio.getAddress());
        dto.setLatitude(studio.getLatitude());
        dto.setLongitude(studio.getLongitude());
        dto.setAmenities(studio.getAmenities());
        dto.setPricePerHour(studio.getPricePerHour());
        dto.setRating(studio.getRating());
        dto.setImages(studio.getImages());
        dto.setDescription(studio.getDescription());
        return dto;
    }
}
