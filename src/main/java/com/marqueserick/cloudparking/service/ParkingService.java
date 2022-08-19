package com.marqueserick.cloudparking.service;

import com.marqueserick.cloudparking.exception.ParkingNotFoundException;
import com.marqueserick.cloudparking.model.Parking;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ParkingService {

    private static final Map<String, Parking> parkingMap = new HashMap<>();

    static {
        Parking parking = new Parking( getUUID(),"ABC-1234", "SP", "Onix", "Azul");
        parkingMap.put(parking.getId(), parking);

        Parking parking1 = new Parking( getUUID(),"EBC-1995", "SP", "Gol", "Preto");
        parkingMap.put(parking1.getId(), parking1);


    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public List<Parking> findAll() {
        return new ArrayList<>(parkingMap.values());
    }

    public Parking findById(String id) {
        Parking parking = parkingMap.get(id);
        if(parking == null) throw new ParkingNotFoundException(id);

        return parking;
    }

    public Parking create(Parking parking) {
        parking.setId(getUUID());
        parking.setEntry(LocalDateTime.now());
        parkingMap.put(parking.getId(), parking);
        return parking;
    }

    public void delete(String id) {
        findById(id);
        parkingMap.remove(id);
    }

    public Parking update(String id, Parking parkingUpdate) {
        Parking parking = findById(id);
        parking.setColor(parkingUpdate.getColor());
        parkingMap.replace(id, parking);
        return parking;
    }
}
