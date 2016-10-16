package at.fhs.nos.parksim.gui;

import at.fhs.nos.parksim.Car;
import at.fhs.nos.parksim.ParkingSlot;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * Created by Nev on 04.06.2016.
 */
public class GuiController {

    private static GuiController instance = null;
    @FXML
    private ListView<ParkingSlot> listNormal;
    @FXML
    private Label labelHandicapped;
    @FXML
    private ListView<ParkingSlot> listHandicapped;
    @FXML
    private Label labelNormal;
    @FXML
    private TextField textDummy;

    private ObservableList<ParkingSlot> parkingPlacesNormal;
    private ObservableList<ParkingSlot> parkingPlacesHandicapped;
    private ObservableList<ParkingSlot> parkingPlaces;

    public GuiController() {
        instance = this;
    }

    public static GuiController getInstance() {
        return instance;
    }

    @FXML
    private void initialize() {
        System.out.println("gui initialized");
        labelNormal.textProperty().bind(Bindings.concat("Normal Parkinglot - ").concat(textDummy.textProperty())); //TODO: add size binding
        labelHandicapped.textProperty().bind(Bindings.concat("Handicapped Parkinglot")); //TODO: add size binding
        listNormal.setCellFactory(new ParkingSlotCellFactory());
        listHandicapped.setCellFactory(new ParkingSlotCellFactory());
        textDummy.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("exit")) {
                System.exit(0);
            }
            System.out.println("text change in gui:" + oldValue + " -->" + newValue);
        });
    }

    public void refreshSlot(ParkingSlot slot) {
        int index = parkingPlaces.indexOf(slot);
        if (index > -1) {
            parkingPlaces.remove(slot);
            parkingPlaces.add(index, slot);
        }
    }

    public void setParkingPlaces(ObservableList<ParkingSlot> parkingPlaces) {
        this.parkingPlaces = parkingPlaces;
        FilteredList<ParkingSlot> normal = new FilteredList<>(parkingPlaces, parkingSlot -> !parkingSlot.reservedForHandicappedCars());
        FilteredList<ParkingSlot> handicapped = new FilteredList<>(parkingPlaces, parkingSlot -> parkingSlot.reservedForHandicappedCars());
        listNormal.setItems(normal);
        listHandicapped.setItems(handicapped);
    }

    private class ParkingSlotCellFactory implements javafx.util.Callback<ListView<ParkingSlot>, javafx.scene.control.ListCell<ParkingSlot>> {
        @Override
        public ListCell<ParkingSlot> call(ListView<ParkingSlot> param) {
            return new ListCell<ParkingSlot>() {
                @Override
                protected void updateItem(ParkingSlot item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null && !empty) {
                        Car car = item.getPlacedCar();
                        if (car == null) {
                            this.setGraphic(new Label("<empty>"));
                        } else {
                            this.setGraphic(new Label(car.getLicensePlateNumber()));
                        }
                    }
                }
            };
        }
    }
}
