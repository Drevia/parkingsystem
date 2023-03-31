package com.parkit.parkingsystem;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.ParkingSpot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParkingSpotTest {

    @Test
    void testEquals() {
        ParkingSpot spot1 = new ParkingSpot(1, ParkingType.CAR, true);
        ParkingSpot spot2 = new ParkingSpot(1, ParkingType.CAR, false);
        ParkingSpot spot3 = new ParkingSpot(2, ParkingType.CAR, true);

        assertTrue(spot1.equals(spot2)); // same number, different availability
        assertFalse(spot1.equals(spot3)); // different number
    }
}
