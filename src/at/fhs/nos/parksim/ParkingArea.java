/**
 * 
 */
package at.fhs.nos.parksim;

/**
 * @author eduard
 *
 */
public interface ParkingArea {
	/** 
	 * Method handling the placement of a car 
	 * on any of the parking place(s). 
	 * @param car The car to place in the parkingArea
	 */
	void placeCar(Car car);

	/**
	 * Method handling the removement of a car
	 * from the occupied parking place(s)
	 * @param car The car to remove
	 */
	void removeCar(Car car);

	/**
	 * Method verifying whether there is free space
	 * for a car
	 */
	boolean hasFreeParkingPlace(Car car);

	/**
	 * Choses an occupied ParkingPlace by a car
     */
	ParkingPlace findOccupiedPlace();

	void parkingInformation();
}
