package com.marqueserick.cloudparking.service;

import com.marqueserick.cloudparking.exception.ParkingNotFoundException;
import com.marqueserick.cloudparking.model.Parking;
import com.marqueserick.cloudparking.repository.ParkingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ParkingService {

    private final ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }


    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public List<Parking> findAll() {
        return parkingRepository.findAll();
    }

    public Parking findById(String id) {
        return parkingRepository.findById(id).orElseThrow(() -> new ParkingNotFoundException(id));
    }

    public Parking create(Parking parking) {
        parking.setId(getUUID());
        parking.setEntry(LocalDateTime.now());
        parkingRepository.save(parking);
        return parking;
    }

    public void delete(String id) {
        parkingRepository.delete(findById(id));
    }

    public Parking update(String id, Parking parkingUpdate) {
        Parking parking = findById(id);
        parking.setColor(parkingUpdate.getColor());
        parking.setModel(parkingUpdate.getModel());
        parking.setState(parkingUpdate.getState());
        parking.setLicense(parkingUpdate.getLicense());
        parkingRepository.save(parking);
        return parking;
    }
}
