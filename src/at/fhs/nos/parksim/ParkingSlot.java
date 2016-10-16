package at.fhs.nos.parksim;

import at.fhs.nos.parksim.gui.GuiController;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Created by Gabriel on 31.05.2016.
 */
public class ParkingSlot implements ParkingPlace {

    final private HandicappedStatuses HandicappedStatus;
    private SimpleObjectProperty<Car> placedCar = new SimpleObjectProperty<>();

    public ParkingSlot(HandicappedStatuses Handicapped) {
        HandicappedStatus = Handicapped;
        placedCar.set(null);
    }

    @Override
    public Car getPlacedCar() {
        return placedCar.get();
    }

    public SimpleObjectProperty<Car> placedCarProperty() {
        return placedCar;
    }

    public void placeCar(Car placedCar) {
        this.placedCar.set(placedCar);
        Platform.runLater(() -> GuiController.getInstance().refreshSlot(this));
    }

    @Override
    public boolean reservedForHandicappedCars() {
        return HandicappedStatus.equals(HandicappedStatuses.HANDICAP);
    }

    @Override
    public HandicappedStatuses getHandicappedStatus() {
        return HandicappedStatus;
    }
}
