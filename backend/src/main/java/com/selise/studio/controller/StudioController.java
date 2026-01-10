package com.selise.studio.controller;

import com.selise.studio.dto.StudioDTO;
import com.selise.studio.service.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/studios")
@CrossOrigin(origins = "*") // Allow all for demo
public class StudioController {

    @Autowired
    private StudioService studioService;

    @GetMapping
    public ResponseEntity<List<StudioDTO>> getAllStudios() {
        return ResponseEntity.ok(studioService.getAllStudios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudioDTO> getStudioById(@PathVariable Long id) {
        return ResponseEntity.ok(studioService.getStudioById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<StudioDTO>> searchStudios(@RequestParam(required = false) String area) {
        return ResponseEntity.ok(studioService.searchStudios(area));
    }

    @GetMapping("/nearby")
    public ResponseEntity<List<StudioDTO>> getNearbyStudios(
            @RequestParam double lat,
            @RequestParam double lng,
            @RequestParam(defaultValue = "10") double radius) {
        return ResponseEntity.ok(studioService.getNearbyStudios(lat, lng, radius));
    }
}
