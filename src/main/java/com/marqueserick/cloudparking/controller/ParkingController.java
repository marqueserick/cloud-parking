package com.marqueserick.cloudparking.controller;

import com.marqueserick.cloudparking.controller.dto.ParkingDTO;
import com.marqueserick.cloudparking.controller.dto.ParkingDTOCreate;
import com.marqueserick.cloudparking.controller.mapper.ParkingMapper;
import com.marqueserick.cloudparking.model.Parking;
import com.marqueserick.cloudparking.service.ParkingService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }

    @GetMapping()
    @ApiOperation("Get all parkings")
    public ResponseEntity<List<ParkingDTO>> findAll() {
        List<Parking> parkingList = parkingService.findAll();
        return ResponseEntity.ok(parkingMapper.toParkingDTOList(parkingList));
    }

    @GetMapping("/{id}")
    @ApiOperation("Get parking by id")
    public ResponseEntity<ParkingDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(parkingMapper.toParkingDTO(parkingService.findById(id)));
    }

    @PostMapping
    @ApiOperation("Create a new parking")
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingDTOCreate parkingDTO) {
        Parking parking = parkingService.create(parkingMapper.toParking(parkingDTO));
        URI uri = URI.create("/parking/" + parking.getId());
        return ResponseEntity.created(uri).body(parkingMapper.toParkingDTO(parking));
    }

    @DeleteMapping("/{id}")
    @SuppressWarnings("rawtypes")
    @ApiOperation("Delete a parking")
    public ResponseEntity delete(@PathVariable String id) {
        parkingService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ApiOperation("Update a parking")
    public ResponseEntity<ParkingDTO> update(@PathVariable String id, @RequestBody ParkingDTOCreate parkingDTO) {
        Parking parking = parkingService.update(id, parkingMapper.toParking(parkingDTO));
        return ResponseEntity.ok(parkingMapper.toParkingDTO(parking));
    }
}
