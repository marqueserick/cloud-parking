package com.marqueserick.cloudparking.service;

import com.marqueserick.cloudparking.exception.ParkingNotFoundException;
import com.marqueserick.cloudparking.model.Parking;
import com.marqueserick.cloudparking.repository.ParkingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

import static com.marqueserick.cloudparking.service.ParkingCheckout.getBill;

@Service
public class ParkingService {

    private final ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }


    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Parking> findAll() {
        return parkingRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Parking findById(String id) {
        return parkingRepository.findById(id).orElseThrow(() -> new ParkingNotFoundException(id));
    }

    @Transactional
    public Parking create(Parking parking) {
        parking.setId(getUUID());
        parking.setEntry(LocalDateTime.now());
        parkingRepository.save(parking);
        return parking;
    }

    @Transactional
    public void delete(String id) {
        parkingRepository.delete(findById(id));
    }

    @Transactional
    public Parking update(String id, Parking parkingUpdate) {
        Parking parking = findById(id);
        parking.setColor(parkingUpdate.getColor());
        parking.setModel(parkingUpdate.getModel());
        parking.setState(parkingUpdate.getState());
        parking.setLicense(parkingUpdate.getLicense());
        parkingRepository.save(parking);
        return parking;
    }

    @Transactional
    public Parking checkout(String id) {
        Parking parking = findById(id);
        parking.setExit(LocalDateTime.now());
        parking.setTotal(getBill(parking));
        parkingRepository.save(parking);
        return parking;
    }
}
