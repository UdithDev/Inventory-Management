package org.example;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        URL fxmlLocation = getClass().getResource("/view/CustomerForm.fxml");
        System.out.println("FXML Location: " + fxmlLocation);
        Parent load = FXMLLoader.load(fxmlLocation);
        stage.setScene(new Scene(load));
        stage.setTitle("Inventory Management System - Loading Page");
        stage.centerOnScreen();
        stage.show();
    }
}