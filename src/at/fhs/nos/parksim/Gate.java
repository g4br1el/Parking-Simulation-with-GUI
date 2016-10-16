package at.fhs.nos.parksim;

public interface Gate {
	/**
	 * Handles the attempt of a car to drive into the parking 
	 * information system. 
	 * @param car The car aiming to drive in
	 * 
	 * @throws NoFreePlaceException In case the parking place is full an 
	 * driving in is not permitted.
	 */
	void driveIn(Car car) throws NoFreePlaceException;
	/**
	 * Handles the quitting of a car from the parking place
	 * @param car The car currently leaving
	 */
	void driveOut(Car car);

	boolean isOpen();
	void setGateOpen(boolean isOpen);
}
