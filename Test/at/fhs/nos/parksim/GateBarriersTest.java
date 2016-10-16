package at.fhs.nos.parksim;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Created by Gabriel on 02.06.2016.
 */
public class GateBarriersTest {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(null);
    }

    @Test(expected = Exception.class)
    public void testDriveIn() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1,1);
        GateBarriers gateBarriers = new GateBarriers(parkingLot);
        Car car1 = new Car(HandicappedStatuses.NORMAL, "FH-1");

        gateBarriers.driveIn(car1);
        assertEquals(car1, parkingLot.findOccupiedPlace().getPlacedCar());

        gateBarriers.driveIn(car1);
        assertEquals("There is no free place for the current car with plate number: "
                + car1.getLicensePlateNumber()
                + " Type: "
                + car1.handicappedStatus()
                + "\r\n", outContent.toString());
        outContent.reset();
    }

    @Test
    public void testDriveOut() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1,1);
        GateBarriers gateBarriers = new GateBarriers(parkingLot);
        Car car1 = new Car(HandicappedStatuses.NORMAL, "FH-1");
        gateBarriers.driveOut(car1);
        assertEquals(null, parkingLot.findOccupiedPlace().getPlacedCar());
    }
}