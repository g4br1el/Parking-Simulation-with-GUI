/**
 * 
 */
package at.fhs.nos.parksim;


/**
 * @author eduard
 *
 */
public class Car {
	protected String plateNumber;
	protected HandicappedStatuses hasHadicappedStatus = HandicappedStatuses.NORMAL;
	
	public Car(HandicappedStatuses hadicapedStatus, String licensePlateNumber) {
		plateNumber = licensePlateNumber;
		hasHadicappedStatus = hadicapedStatus;
	}
	
	public String getLicensePlateNumber() {
		return plateNumber;
	}
	public HandicappedStatuses handicappedStatus() {
		return hasHadicappedStatus;
	}
}