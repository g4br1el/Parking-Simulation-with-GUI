package at.fhs.nos.parksim;

public interface ParkingPlace {
	boolean reservedForHandicappedCars();
	HandicappedStatuses getHandicappedStatus();
	Car getPlacedCar();
	void placeCar(Car car);
}