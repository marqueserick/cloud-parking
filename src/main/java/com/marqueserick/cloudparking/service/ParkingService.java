package com.marqueserick.cloudparking.service;

import com.marqueserick.cloudparking.model.Parking;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ParkingService {

    private static Map<String, Parking> parkingMap = new HashMap<>();

    static {
        var id = getUUID();
        Parking parking = new Parking(id,"ABC-1234", "SP", "Onix", "Azul");
        parkingMap.put(id, parking);

    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public List<Parking> findAll() {
        return new ArrayList<>(parkingMap.values());
    }
}
