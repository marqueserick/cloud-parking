package com.marqueserick.cloudparking.controller;

import com.marqueserick.cloudparking.controller.dto.ParkingDTO;
import com.marqueserick.cloudparking.controller.mapper.ParkingMapper;
import com.marqueserick.cloudparking.model.Parking;
import com.marqueserick.cloudparking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<ParkingDTO> findAll() {
         List<Parking> parkingList = parkingService.findAll();
         return parkingMapper.toParkingDTOList(parkingList);

    }
}
