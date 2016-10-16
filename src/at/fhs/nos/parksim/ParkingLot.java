package at.fhs.nos.parksim;

import at.fhs.nos.parksim.gui.GuiController;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Created by Gabriel on 31.05.2016.
 */
public class ParkingLot implements ParkingArea {
    private static ObservableList<ParkingSlot> parkingPlaces;

    public ParkingLot(int Handicapped, int Normal) {
        parkingPlaces = FXCollections.observableArrayList();
        parkingPlaces.addListener((ListChangeListener<? super ParkingSlot>) c -> {
            c.next();
            if (c.wasAdded()) {
                // System.out.println("change listener: elements add "+c.getAddedSubList().get(0));
            }
        });
        for (int i = 0; i < Handicapped; i++) {
            ParkingSlot ps = new ParkingSlot(HandicappedStatuses.HANDICAP);
            ps.placedCarProperty().addListener((observable,
                                                oldValue,
                                                newValue) -> {
                if (newValue != null) {
                    System.out.println("handicapped parking place: " + newValue.getLicensePlateNumber());
                } else {
                    System.out.println("handicapped parking place now empty");
                }
            });
            parkingPlaces.add(ps);
        }

        for (int i = 0; i < Normal; i++) {
            parkingPlaces.add(new ParkingSlot(HandicappedStatuses.NORMAL));
        }
        GuiController.getInstance().setParkingPlaces(parkingPlaces);
    }

    @Override
    public void placeCar(Car car) {
        System.out.println("AUTO FAEHRT REIN: " + car.getLicensePlateNumber() + " " + car.handicappedStatus());
        if (car.hasHadicappedStatus == HandicappedStatuses.NORMAL) {
            for (ParkingPlace place : parkingPlaces) {
                synchronized (place) {
                    if (!place.reservedForHandicappedCars() && (place.getPlacedCar() == null)) {
                        place.placeCar(car);
                        return;
                    }
                }
            }
        } else {
            for (int i = 0; i < (parkingPlaces.size()); i++) {
                synchronized (parkingPlaces) {
                    if (parkingPlaces.get(i).getHandicappedStatus() == HandicappedStatuses.HANDICAP && (parkingPlaces.get(i).getPlacedCar() == null)) {
                        parkingPlaces.get(i).placeCar(car);
                        return;
                    } else if ((parkingPlaces.get(i).getPlacedCar() == null) && (parkingPlaces.get(i + 1).getPlacedCar() == null)) {
                        parkingPlaces.get(i).placeCar(car);
                        parkingPlaces.get(i + 1).placeCar(car);
                        return;
                    }
                }
            }
        }
        this.parkingInformation();
        System.out.println("DEM AUTO: " + car.getLicensePlateNumber() + " " + car.handicappedStatus() + "WURDE DER PARKPLATZ WEG GESCCHNAPT UND FÄHRT WIEDER!");
    }

    @Override
    public void removeCar(Car car) {
        synchronized (parkingPlaces) {
            parkingPlaces.stream()
                    .filter(ParkingSlot -> ParkingSlot.getPlacedCar() != null)
                    .filter(ParkingSlot -> ParkingSlot
                            .getPlacedCar()
                            .equals(car))
                    .forEach(PSlot -> PSlot.placeCar(null));
        }
        System.out.println("AUTO FÄHRT RAUS: " + car.getLicensePlateNumber() + " " + car.handicappedStatus());
    }

    @Override
    public boolean hasFreeParkingPlace(Car car) {
        if (car.hasHadicappedStatus == HandicappedStatuses.NORMAL) {
            for (ParkingPlace place : parkingPlaces) {
                if (!place.reservedForHandicappedCars() && (place.getPlacedCar() == null))
                    return true;
            }
        } else {
            for (int i = 0; i < (parkingPlaces.size()); i++) {
                if (parkingPlaces.get(i).getHandicappedStatus() == HandicappedStatuses.HANDICAP && (parkingPlaces.get(i).getPlacedCar() == null)) {
                    return true;
                } else if ((parkingPlaces.get(i).getPlacedCar() == null) && (parkingPlaces.get(i + 1).getPlacedCar() == null)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public ParkingPlace findOccupiedPlace() {
        for (ParkingPlace place : parkingPlaces) {
            if (place.getPlacedCar() != null)
                return place;
        }
        return parkingPlaces.get(0);
    }

    @Override
    public void parkingInformation() { //TODO: ev. Ändern
        Car car;
        List<ParkingSlot> Place = parkingPlaces;
        for (int i = 0; i < Place.size(); i++) {
            car = Place.get(i).getPlacedCar();
            if (null != car) {
                System.out.println("Place:" + (i + 1) + " Car Type: " + car.handicappedStatus() + " Car License: " + car.getLicensePlateNumber());
            } else {
                System.out.println("Place:" + (i + 1) + " This Place is Free");
            }
        }
    }
}