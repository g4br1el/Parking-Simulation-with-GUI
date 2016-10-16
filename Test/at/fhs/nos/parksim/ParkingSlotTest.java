package at.fhs.nos.parksim;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Gabriel on 02.06.2016.
 */
public class ParkingSlotTest {
    @Test
    public void testReservedForHandicappedCars() throws Exception {
        ParkingSlot parkingSlot1 = new ParkingSlot(HandicappedStatuses.HANDICAP);
        ParkingSlot parkingSlot2 = new ParkingSlot(HandicappedStatuses.NORMAL);
        assertEquals(true, parkingSlot1.reservedForHandicappedCars());
        assertEquals(false, parkingSlot2.reservedForHandicappedCars());
    }

    @Test
    public void testGetHandicappedStatus() throws Exception {
        ParkingSlot parkingSlot1 = new ParkingSlot(HandicappedStatuses.HANDICAP);
        ParkingSlot parkingSlot2 = new ParkingSlot(HandicappedStatuses.NORMAL);
        assertEquals(HandicappedStatuses.HANDICAP, parkingSlot1.getHandicappedStatus());
        assertEquals(HandicappedStatuses.NORMAL, parkingSlot2.getHandicappedStatus());
    }
}