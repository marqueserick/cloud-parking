package com.marqueserick.cloudparking.controller;

import com.marqueserick.cloudparking.controller.dto.ParkingDTO;
import com.marqueserick.cloudparking.controller.dto.ParkingDTOCreate;
import com.marqueserick.cloudparking.controller.mapper.ParkingMapper;
import com.marqueserick.cloudparking.model.Parking;
import com.marqueserick.cloudparking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    public ParkingController(@Autowired ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ParkingDTO>> findAll() {
         List<Parking> parkingList = parkingService.findAll();
         return ResponseEntity.ok(parkingMapper.toParkingDTOList(parkingList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(parkingMapper.toParkingDTO(parkingService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingDTOCreate parkingDTO) {
        Parking parking = parkingService.create(parkingMapper.toParkingCreate(parkingDTO));
        URI uri = URI.create("/parking/" + parking.getId());
        return ResponseEntity.created(uri).body(parkingMapper.toParkingDTO(parking));
    }
}