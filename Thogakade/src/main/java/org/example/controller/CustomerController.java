package org.example.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CustomerController {


    public TextField txtCustomerId;
    public TextField txtCustomerName;
    public TextField txtCustomerAddress;
    public TextField txtCustomerContact;
    public TableView tblCustomer;
    public TableColumn colCustomerId;
    public TableColumn colCustomerName;
    public TableColumn colCustomerAddress;
    public TableColumn colCustomerContact;
    public Button btnSave;
    public Button btnDelete;

    public void btnCustomerSaveOnAction(ActionEvent actionEvent) {
    }

    public void btnCustomerDeleteOnAction(ActionEvent actionEvent) {
    }
}
