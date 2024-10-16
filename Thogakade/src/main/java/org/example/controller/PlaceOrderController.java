package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class PlaceOrderController {

    public TextField txtOrderId;
    public TextField txtOrderDate;
    public TextField txtCustomerName;
    public TextField txtDescription;


    public ComboBox cmbCustomerId;
    public ComboBox cmbItemCode;
    public TextField txtQtyOnHand;
    public TableView tblOrderCart;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colTotal;
    public TableColumn colAction;
    public TextField txtNetTotal;
    public TextField txtUnitPrice;
    public TextField txtQty;
    public AnchorPane pane;
    public Button btnBack;

    public void cmbCustomerIdOnAction(ActionEvent actionEvent) {
    }

    public void cmbItemCodeOnAction(ActionEvent actionEvent) {
    }

    public void addToCartOnAction(ActionEvent actionEvent) {
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        System.out.println("Back to dashboard");
        URL resource = getClass().getResource("/view/Dashboard.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        pane.getChildren().clear();
        pane.getChildren().add(load);
    }

    public void placeOrderOnAction(ActionEvent actionEvent) {
    }
}
