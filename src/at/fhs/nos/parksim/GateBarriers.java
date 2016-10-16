package at.fhs.nos.parksim;

/**
 * Created by Gabriel on 31.05.2016.
 */
public class GateBarriers implements Gate {
    private ParkingArea parkingArea;
    private boolean gateOpen;

    public GateBarriers(ParkingArea parkingArea) {
        this.parkingArea = parkingArea;
        gateOpen = true;
    }

    @Override
    synchronized public void driveIn(Car car) throws NoFreePlaceException {
        System.out.println("Drive in");
        boolean free = parkingArea.hasFreeParkingPlace(car);

        if (!free)
            throw new NoFreePlaceException(car);

        parkingArea.placeCar(car);
    }

    @Override
    public void driveOut(Car car) {
        System.out.println("Drive out");
        parkingArea.removeCar(car);
    }

    @Override
    public boolean isOpen() {
        return gateOpen;
    }

    @Override
    public void setGateOpen(boolean isOpen) {
        gateOpen = isOpen;
    }
}
