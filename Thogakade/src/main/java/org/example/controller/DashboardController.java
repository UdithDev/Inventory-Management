package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class DashboardController {
    public Button btnCustomerManager;
    public Button btnItemManager;
    public Button btnOrderManager;
    public Button btnPlaceOrder;
    public AnchorPane pane;

    public void btnCustomerManagerOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/CustomerForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        pane.getChildren().clear();
        pane.getChildren().add(load);

    }

    public void btnItemManagerOnAction(ActionEvent actionEvent) throws IOException {
        System.out.println("item");
        URL resource = getClass().getResource("/view/ItemFormView.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        pane.getChildren().clear();
        pane.getChildren().add(load);

    }

    public void btnOrderManagerOnAction(ActionEvent actionEvent) throws IOException {
        System.out.println("Order");
        URL resource = getClass().getResource("/view/OrderForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        pane.getChildren().clear();
        pane.getChildren().add(load);
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) throws IOException {
    }
}
