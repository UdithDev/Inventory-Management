package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BOFactory;
import org.example.bo.custom.CustomerBO;
import org.example.dto.CustomerDTO;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {


    public TextField txtCustomerId;
    public TextField txtCustomerName;
    public TextField txtCustomerEmail;
    public TextField txtCustomerPhone;
    public TableView tblCustomer;
    public TableColumn colCustomerId;
    public TableColumn colCustomerName;
    public TableColumn colCustomerEmail;
    public TableColumn colCustomerPhone;
    public Button btnDelete;
    public Button btnSave;
    public Button btnUpdate;
    public Button btnLogOut;
    public AnchorPane pane;


    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCustomerEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCustomerPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));


        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setData((CustomerDTO) newValue);
            }
        });
        loadAllCustomers();
    }


    private void setData(CustomerDTO newValue) {
        txtCustomerId.setText(newValue.getId());
        txtCustomerName.setText(newValue.getName());
        txtCustomerEmail.setText(newValue.getEmail());
        txtCustomerPhone.setText(newValue.getPhone());
    }


    public void btnCustomerSaveOnAction(ActionEvent actionEvent) {
        try {
            String id = txtCustomerId.getText();
            String name = txtCustomerName.getText();
            String email = txtCustomerEmail.getText();
            String phone = txtCustomerPhone.getText();
            CustomerDTO customerDTO = new CustomerDTO(id, name, email, phone);
            boolean isSaved = customerBO.saveCustomer(customerDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer saved successfully!").show();
                loadAllCustomers();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save customer!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error occurred while saving customer: " + e.getMessage()).show();
        }
    }


    public void btnCustomerDeleteOnAction(ActionEvent actionEvent) {
        try {
            String id = txtCustomerId.getText();
            boolean isDeleted = customerBO.deleteCustomer(new CustomerDTO(id, "", "", ""));
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer deleted successfully!").show();
                loadAllCustomers();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete customer!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error occurred while deleting customer: " + e.getMessage()).show();
        }
    }


    public void btnCustomerUpdateOnAction(ActionEvent actionEvent) {
        try {
            String id = txtCustomerId.getText();
            String name = txtCustomerName.getText();
            String email = txtCustomerEmail.getText();
            String phone = txtCustomerPhone.getText();

            CustomerDTO customerDTO = new CustomerDTO(id, name, email, phone);
            boolean isUpdated = customerBO.updateCustomer(customerDTO);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer updated successfully!").show();
                loadAllCustomers();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update customer!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error occurred while updating customer: " + e.getMessage()).show();
        }
    }


    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        System.out.println("logout");
        URL resource = getClass().getResource("/view/Dashboard.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        pane.getChildren().clear();
        pane.getChildren().add(load);
    }

    private void loadAllCustomers() {
        try {
            List<CustomerDTO> allCustomers = customerBO.getAllCustomers();
            ObservableList<CustomerDTO> customerList = FXCollections.observableArrayList(allCustomers);
            tblCustomer.setItems(customerList);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error occurred while loading customers: " + e.getMessage()).show();
        }
    }

    private void clearFields() {
        txtCustomerId.clear();
        txtCustomerName.clear();
        txtCustomerEmail.clear();
        txtCustomerPhone.clear();
    }
}
