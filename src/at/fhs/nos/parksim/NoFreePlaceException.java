package at.fhs.nos.parksim;

public class NoFreePlaceException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NoFreePlaceException() {
		super("There is no free place for the current car");
	}
	public NoFreePlaceException(Car car) {
		super ("There is no free place for the current car with plate number: " + car.getLicensePlateNumber() + " Type: " + car.handicappedStatus());
	}
}
