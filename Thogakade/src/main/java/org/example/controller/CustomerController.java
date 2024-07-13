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
import org.example.bo.custom.impl.CustomerBOImpl;
import org.example.dto.CustomerDTO;
import org.example.dto.tm.CustomerTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {


    private final CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);
    public TextField txtCustomerId;
    public TextField txtCustomerName;
    public TextField txtCustomerAddress;
    public TextField txtCustomerContact;
    public TableView<CustomerTM> tblCustomer;
    public TableColumn<CustomerTM, String> colCustomerId;
    public TableColumn<CustomerTM, String> colCustomerName;
    public TableColumn<CustomerTM, String> colCustomerAddress;
    public TableColumn<CustomerTM, String> colCustomerContact;
    public Button btnSave;
    public Button btnDelete;
    public AnchorPane pane;

    public void btnCustomerSaveOnAction(ActionEvent actionEvent) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Save Customer");
        confirmationAlert.setHeaderText("Are you sure you want to save this customer?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                CustomerDTO customerDTO = new CustomerDTO(
                        txtCustomerId.getText(),
                        txtCustomerName.getText(),
                        txtCustomerAddress.getText(),
                        txtCustomerContact.getText()
                );
                customerBO.addCustomer(customerDTO);
                getAllCustomers();
                clearFields();
                showAlert(Alert.AlertType.INFORMATION, "Customer Saved", "Customer has been saved successfully.");
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to save customer.");
            }
        }
    }

    public void btnCustomerDeleteOnAction(ActionEvent actionEvent) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Delete Customer");
        confirmationAlert.setHeaderText("Are you sure you want to delete this customer?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                String customerId = txtCustomerId.getText();
                customerBO.deleteCustomer(customerId);
                getAllCustomers();
                clearFields();
                showAlert(Alert.AlertType.INFORMATION, "Customer Deleted", "Customer has been deleted successfully.");
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete customer.");
            }
        }
    }

    public void btnCustomerUpdateOnAction(ActionEvent actionEvent) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Update Customer");
        confirmationAlert.setHeaderText("Are you sure you want to update this customer?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                CustomerDTO customerDTO = new CustomerDTO(
                        txtCustomerId.getText(),
                        txtCustomerName.getText(),
                        txtCustomerAddress.getText(),
                        txtCustomerContact.getText()
                );
                customerBO.updateCustomer(customerDTO);
                getAllCustomers();
                clearFields();
                showAlert(Alert.AlertType.INFORMATION, "Customer Updated", "Customer has been updated successfully.");
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to update customer.");
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTable();
        getAllCustomers();
    }

    private void setupTable() {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<CustomerTM, String>("id"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<CustomerTM, String>("name"));
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<CustomerTM, String>("address"));
        colCustomerContact.setCellValueFactory(new PropertyValueFactory<CustomerTM, String>("contact"));
    }

    void getAllCustomers() {
        ObservableList<CustomerTM> observableList = FXCollections.observableArrayList();
        try {
            ArrayList<CustomerDTO> all = customerBO.getAllCustomers();
            for (CustomerDTO customerDTO : all) {
                observableList.add(new CustomerTM(
                        customerDTO.getId(),
                        customerDTO.getName(),
                        customerDTO.getAddress(),
                        customerDTO.getContact()
                ));
            }
            tblCustomer.setItems(observableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        txtCustomerId.clear();
        txtCustomerName.clear();
        txtCustomerAddress.clear();
        txtCustomerContact.clear();
    }

//    private void loadAllCustomers() {
//        try {
//            List<CustomerDTO> allCustomers = customerBO.getAllCustomers();
//            tblCustomer.setItems(FXCollections.observableArrayList(allCustomers));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/Dashboard.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        pane.getChildren().clear();
        pane.getChildren().add(load);
    }
}
