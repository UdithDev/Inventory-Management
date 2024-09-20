package org.example.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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

    public void cmbCustomerIdOnAction(ActionEvent actionEvent) {
    }

    public void cmbItemCodeOnAction(ActionEvent actionEvent) {
    }

    public void addToCartOnAction(ActionEvent actionEvent) {
    }

    public void backOnAction(ActionEvent actionEvent) {
    }

    public void placeOrderOnAction(ActionEvent actionEvent) {
    }
}
