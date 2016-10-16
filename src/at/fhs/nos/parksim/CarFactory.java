package at.fhs.nos.parksim;

public class CarFactory {
	/**
	 * Static counter, simply increase the number
	 */
	private static int counter = 0;

	/**
	 * Factory method creating a new filled instance of the car
	 * @return The new instance
	 */
	public static Car createNew() {
		//TODO: FINISHED assign one out of 10 cars HANDICAP status
		double rnd = Math.random();
		Car c;
		if(rnd < 0.9f){
			c = new Car(HandicappedStatuses.NORMAL, "HA-"+ ++counter + " FH");
		} else {
			c = new Car(HandicappedStatuses.HANDICAP, "HA-"+ ++counter + " FH");
		}
		return c;
	}
}

