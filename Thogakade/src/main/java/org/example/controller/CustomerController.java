package org.example.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BOFactory;
import org.example.bo.SuperBO;
import org.example.bo.custom.CustomerBO;
import org.example.dto.CustomerDTO;
import org.example.dto.tm.CustomerTM;

import java.io.IOException;
import java.net.URL;


import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CustomerController implements Initializable {


    public TextField txtCustomerId;
    public TextField txtCustomerName;
    public TextField txtCustomerEmail;
    public TextField txtCustomerPhone;
    public TextField txtSearch;
    public TableView<CustomerTM> tblCustomer;
    public TableColumn<CustomerTM, String> colCustomerId;
    public TableColumn<CustomerTM, String> colCustomerName;
    public TableColumn<CustomerTM, String> colCustomerEmail;
    public TableColumn<CustomerTM, String> colCustomerPhone;
    public Button btnDelete;
    public Button btnSave;
    public Button btnUpdate;
    public Button btnLogOut;
    public AnchorPane pane;


    private CustomerBO customerBO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            customerBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);
            colCustomerId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colCustomerEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            colCustomerPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

            loadAllCustomers();

            txtSearch.textProperty().addListener((observableValue, previous, current) -> {
                if (!Objects.equals(previous, current)) {
                    tblCustomer.getItems().clear();
                    List<CustomerTM> collect = customerBO.searchCustomerByText(current).stream().map(this::toCustomerTm).collect(Collectors.toList());
                    tblCustomer.setItems(FXCollections.observableArrayList(collect));
                }
            });
        } catch (RuntimeException exception) {
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
        }
    }

    private CustomerTM toCustomerTm(CustomerDTO customerDTO) {
        CustomerTM customerTM = new CustomerTM();
        customerTM.setId(customerTM.getId());
        customerTM.setName(customerTM.getName());
        customerTM.setEmail(customerTM.getEmail());
        customerTM.setPhone(customerTM.getPhone());

        return customerTM;
    }


    public void btnCustomerSaveOnAction(ActionEvent actionEvent) {
        try {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setId(txtCustomerId.getText());
            customerDTO.setName(txtCustomerName.getText());
            customerDTO.setEmail(txtCustomerEmail.getText());
            customerDTO.setPhone(txtCustomerPhone.getText());

            customerBO.save(customerDTO);
            new Alert(Alert.AlertType.INFORMATION, "Customer Added").show();
            loadAllCustomers();
            clearFields();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    public void btnCustomerDeleteOnAction(ActionEvent actionEvent) {
        CustomerTM selectedItem = tblCustomer.getSelectionModel().getSelectedItem();
        try {
            if (selectedItem != null) {
                customerBO.delete(selectedItem.getId());
                new Alert(Alert.AlertType.INFORMATION, "Customer Deleted").show();
                loadAllCustomers();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Select Customer first!").show();
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            clearFields();
        }
        btnUpdate.setDisable(true);
        btnSave.setDisable(false);
        btnDelete.setDisable(true);
    }


    public void btnCustomerUpdateOnAction(ActionEvent actionEvent) {
        try {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setId(txtCustomerId.getText());
            customerDTO.setName(txtCustomerName.getText());
            customerDTO.setEmail(txtCustomerEmail.getText());
            customerDTO.setPhone(txtCustomerPhone.getText());

            customerBO.update(customerDTO);
            new Alert(Alert.AlertType.INFORMATION, "Customer Updated").show();
            loadAllCustomers();
            clearFields();
        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            clearFields();
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
            List<CustomerDTO> all = customerBO.getAll();
            ObservableList<CustomerTM> customerTMObservableList = FXCollections.observableArrayList();
            all.forEach(customerDTO -> customerTMObservableList.add(new CustomerTM(customerDTO.getId(), customerDTO.getName(), customerDTO.getEmail(), customerDTO.getPhone())));
            tblCustomer.setItems(customerTMObservableList);
        } catch (RuntimeException exception) {
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
            tblCustomer.getItems().clear();
        }
    }

    private void clearFields() {
        txtCustomerId.clear();
        txtCustomerName.clear();
        txtCustomerEmail.clear();
        txtCustomerPhone.clear();
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {

    }

    public void txtSearchOnKeyReleased(KeyEvent keyEvent) {
    }

    public void tblCustomerOnMouseClicked(MouseEvent mouseEvent) {
        CustomerTM selectedItem = tblCustomer.getSelectionModel().getSelectedItem();
        try {
            if (selectedItem != null) {
                btnUpdate.setDisable(false);
                btnDelete.setDisable(false);
                btnSave.setDisable(true);
                txtCustomerId.setText(selectedItem.getId());
                txtCustomerName.setText(selectedItem.getName());
                txtCustomerEmail.setText(selectedItem.getEmail());
                txtCustomerPhone.setText(selectedItem.getPhone());
            } else {
                btnUpdate.setDisable(true);
            }
        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
