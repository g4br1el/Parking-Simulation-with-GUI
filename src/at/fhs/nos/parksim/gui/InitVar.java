package at.fhs.nos.parksim.gui;

import at.fhs.nos.parksim.Simulation;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class InitVar extends Application {

    @FXML
    private TextField NormalField;

    @FXML
    private TextField HandicappedField;

    @FXML
    private Button Closebutton;

    @FXML
    private Slider Minutes;

    @FXML
    protected void CloseWindow() {
        Stage stage = (Stage) Closebutton.getScene().getWindow();
        if(validate(HandicappedField.getText()) && validate(HandicappedField.getText()) && !HandicappedField.getText().isEmpty() && !NormalField.getText().isEmpty()) {
            //Simulation.Handicapped = Integer.parseInt(HandicappedField.getText());
            //Simulation.Normal = Integer.parseInt(NormalField.getText());
            //Simulation.time = (int) Minutes.getValue();
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Input! Set Default values\n50 Handicapped slots\n300 Normal slots\n10 Minutes");
            //Simulation.Handicapped = 50;
            //Simulation.Normal = 300;
            //Simulation.time = 10;
        }
        stage.close();
    }

    private boolean validate(String text)
    {
        return text.matches("[0-9]*");
    }

    public static void StartWIV(String[] args){
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(InitVar.class.getResource("InitVar.fxml"));
        Scene scene = new Scene(root, 320, 180);
        primaryStage.setTitle("Set Variables");
        primaryStage.setScene(scene);

        primaryStage.setOnCloseRequest(event -> {
            JOptionPane.showMessageDialog(null, "Invalid Input! Set Default values\n50 Handicapped slots\n300 Normal slots\n10 Minutes");
            //Simulation.Handicapped = 50;
            //Simulation.Normal = 300;
            //Simulation.time = 10;
        });

        primaryStage.show();
    }
}