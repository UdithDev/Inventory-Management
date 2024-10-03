package org.example.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemFormViewController implements Initializable {


    public TextField txtItemCode;
    public TextField txtItemDescription;
    public TextField txtUnitPrice;
    public TextField txtQtyOnHand;
    public TableView tblItem;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQtyOnHand;
    public Button btnSave;
    public Button btnDelete;
    public Button btnUpdate;
    public Button btnLogOut;
    public AnchorPane pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void btnItemSaveOnAction(ActionEvent actionEvent) {
    }

    public void btnItemDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnItemUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        System.out.println("logout");
        URL resource = getClass().getResource("/view/Dashboard.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        pane.getChildren().clear();
        pane.getChildren().add(load);
    }
}
