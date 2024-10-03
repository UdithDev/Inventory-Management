package org.example.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class OrderItemsFormController {

    public AnchorPane pane;
    public TextField txtOrderId;
    public TextField txtItemId;
    public TextField txtQuantity;
    public TextField txtTotalPrice;
    public TableView tblOrderItems;
    public TableColumn colOrderId;
    public TableColumn colItemId;
    public TableColumn colQuantity;
    public TableColumn colTotalPrice;
    public Button btnSaveOrderItem;
    public Button btnLogOut;
    public Button btnDeleteOrderItem;
    public Button btnUpdateOrderItem;

    public void btnLogOutOnAction(ActionEvent actionEvent) {
    }

    public void updateOrderItemOnAction(ActionEvent actionEvent) {
    }

    public void deleteOrderItemOnAction(ActionEvent actionEvent) {
    }

    public void saveOrderItemOnAction(ActionEvent actionEvent) {
    }
}
