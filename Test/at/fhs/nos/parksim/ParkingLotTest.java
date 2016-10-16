package at.fhs.nos.parksim;

import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Gabriel on 02.06.2016.
 */
public class ParkingLotTest {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(null);
    }

    @Test
    public void testPlaceCarHandicap() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1,0);
        Car car = new Car(HandicappedStatuses.HANDICAP, "FH-1");
        parkingLot.placeCar(car);
        assertEquals(car, parkingLot.findOccupiedPlace().getPlacedCar());
        parkingLot.parkingInformation();
        assertEquals("AUTO FAEHRT REIN: FH-1 HANDICAP" + "\r\n" + "Place:" + "1" + " Car Type: " + car.handicappedStatus() + " Car License: " + car.getLicensePlateNumber() + "\r\n", outContent.toString());
        outContent.reset();
    }

    @Test
    public void testPlaceCarHandicap2() throws Exception {
        ParkingLot parkingLot = new ParkingLot(0,2);
        Car car = new Car(HandicappedStatuses.HANDICAP, "FH-1");
        parkingLot.placeCar(car);
        assertEquals(car, parkingLot.findOccupiedPlace().getPlacedCar());
        parkingLot.parkingInformation();
        assertEquals("AUTO FAEHRT REIN: FH-1 HANDICAP" + "\r\n" + "Place:" + "1" + " Car Type: " + car.handicappedStatus() + " Car License: " + car.getLicensePlateNumber()
                + "\r\n" + "Place:" + "2" + " Car Type: " + car.handicappedStatus() + " Car License: " + car.getLicensePlateNumber()
                + "\r\n", outContent.toString());
        outContent.reset();
    }

    @Test
    public void testPlaceCarNormalFails() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1,0);
        Car car = new Car(HandicappedStatuses.NORMAL, "FH-1");
        parkingLot.placeCar(car);

        assertEquals(null, parkingLot.findOccupiedPlace().getPlacedCar());

        parkingLot.parkingInformation();

        assertEquals("AUTO FAEHRT REIN: FH-1 NORMAL" + "\r\n"
                + "Place:1 This Place is Free" + "\r\n"
                + "DEM AUTO: FH-1 NORMALWURDE DER PARKPLATZ WEG GESCCHNAPT UND FÄHRT WIEDER!" + "\r\n"
                + "Place:1 This Place is Free" + "\r\n"
                , outContent.toString());
        outContent.reset();
    }

    @Test
    public void testRemoveCar() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1,3);
        Car car1 = new Car(HandicappedStatuses.HANDICAP, "FH-1");
        Car car2 = new Car(HandicappedStatuses.NORMAL, "FH-2");
        Car car3 = new Car(HandicappedStatuses.NORMAL, "FH-3");
        parkingLot.placeCar(car1);
        parkingLot.placeCar(car2);
        parkingLot.placeCar(car3);

        parkingLot.removeCar(car1);
        assertEquals(car2, parkingLot.findOccupiedPlace().getPlacedCar());
        parkingLot.removeCar(car2);
        assertEquals(car3, parkingLot.findOccupiedPlace().getPlacedCar());
        parkingLot.removeCar(car3);
        assertEquals(null, parkingLot.findOccupiedPlace().getPlacedCar());

    }

    @Test
    public void testHasFreeParkingPlace() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1,3);
        Car car1 = new Car(HandicappedStatuses.HANDICAP, "FH-1");
        Car car2 = new Car(HandicappedStatuses.NORMAL, "FH-2");

        assertTrue(parkingLot.hasFreeParkingPlace(car1));
        parkingLot.placeCar(car1);
        assertTrue(parkingLot.hasFreeParkingPlace(car2));
        parkingLot.placeCar(car2);
        assertTrue(parkingLot.hasFreeParkingPlace(car1));
        parkingLot.placeCar(car1);
        assertFalse(parkingLot.hasFreeParkingPlace(car2));
        assertFalse(parkingLot.hasFreeParkingPlace(car1));
    }

    @Test
    public void testFindOccupiedPlace() throws Exception {
        ParkingLot parkingLot = new ParkingLot(0,1);
        Car car = new Car(HandicappedStatuses.NORMAL, "FH-1");
        parkingLot.placeCar(car);
        assertEquals(car, parkingLot.findOccupiedPlace().getPlacedCar());
    }
}