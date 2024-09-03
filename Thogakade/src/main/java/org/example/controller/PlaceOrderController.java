package org.example.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class PlaceOrderController {

    public Label lblDateAndTime;
    public TextField txtOrderId;
    public TextField txtOrderDate;
    public TextField txtDescription;
    public TextField txtCustomerName;
    public TableView tblOrderCart;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colTotal;
    public TableColumn colAction;
    public ComboBox cmbCustomerId;
    public ComboBox cmbItemCode;
    public TextField txtQtyOnHand;
    public TextField txtUnitPrice;
    public TextField txtQty;
    public TextField txtNetTotal;

    public void backOnAction(ActionEvent actionEvent) {
    }

    public void cmbCustomerIdOnAction(ActionEvent actionEvent) {
    }

    public void cmbItemCodeOnAction(ActionEvent actionEvent) {
    }

    public void addToCartOnAction(ActionEvent actionEvent) {
    }
}
