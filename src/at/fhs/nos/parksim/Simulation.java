package at.fhs.nos.parksim;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Gabriel on 31.05.2016.
 */
public class Simulation extends Application {

    public static void main(String[] args) throws InterruptedException, IOException {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Simulation.class.getResource("gui/Gui.fxml"));

        Scene scene = new Scene(root, 600, 600);

        primaryStage.setTitle("Parking Simulation");
        primaryStage.setScene(scene);

        Scanner scan = new Scanner(System.in);
        long patience = 1000 * 60 * 2; // two minutes
        boolean active = true;
        System.out.print("Number of Handicapped Parking places: ");
        int Handicapped = scan.nextInt();
        System.out.print("Number of Normal Parking places: ");
        int Normal = scan.nextInt();
        long startTime = System.currentTimeMillis();
        ParkingArea Parking = new ParkingLot(Handicapped, Normal);
        Gate gate = new GateBarriers(Parking);
        GateWorker gate_1 = new GateWorker(gate, Parking);
        GateWorker gate_2 = new GateWorker(gate, Parking);
        gate_1.start();
        gate_2.start();

        primaryStage.setOnCloseRequest(event -> {
            gate_1.interrupt();
            gate_2.interrupt();
        });


        primaryStage.show();


        System.out.println("lkdsföalsdjfölkaj");
    }
}
