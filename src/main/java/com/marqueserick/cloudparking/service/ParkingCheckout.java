package com.marqueserick.cloudparking.service;

import com.marqueserick.cloudparking.model.Parking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ParkingCheckout {
    public static final int ONE_HOUR = 60;
    public static final int ONE_DAY = 24 * ONE_HOUR;
    public static final double ONE_HOUR_PRICE = 5.0;
    public static final double ADDITIONAL_PER_HOUR_PRICE = 2.0;
    public static final double ONE_DAY_PRICE = 20.0;

    public static Double getBill(Parking parking) {
        return getBill(parking.getEntry(), parking.getExit());
    }

    public static Double getBill(LocalDateTime entry, LocalDateTime exit) {
        long minutes = entry.until(exit, ChronoUnit.MINUTES);
        if (minutes <= ONE_HOUR) {
            return ONE_HOUR_PRICE;
        }

        if (minutes <= ONE_DAY) {
            int hours = (int) ((minutes - ONE_HOUR) / ONE_HOUR);
            return ONE_HOUR_PRICE + (hours * ADDITIONAL_PER_HOUR_PRICE);
        }
        int days = (int) (minutes / ONE_DAY);
        int hours = (int) (minutes - (days * ONE_DAY))/ONE_HOUR;
        return ONE_DAY_PRICE * days + (hours * ADDITIONAL_PER_HOUR_PRICE);
    }
}
