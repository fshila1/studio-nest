package com.selise.studio.repository;

import com.selise.studio.model.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Long> {

    List<String> findDistinctAreaBy();

    List<Studio> findByAreaContainingIgnoreCase(String area);

    @Query(value = "SELECT * FROM studios s WHERE " +
            "(6371 * acos(cos(radians(:lat)) * cos(radians(s.latitude)) * " +
            "cos(radians(s.longitude) - radians(:lng)) + " +
            "sin(radians(:lat)) * sin(radians(s.latitude)))) < :radius", nativeQuery = true)
    List<Studio> findNearbyStudios(@Param("lat") double lat,
            @Param("lng") double lng,
            @Param("radius") double radius);
}
