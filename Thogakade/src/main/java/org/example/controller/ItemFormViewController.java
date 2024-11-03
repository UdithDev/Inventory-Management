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
import org.example.bo.custom.ItemBO;
import org.example.dto.CustomerDTO;
import org.example.dto.ItemDTO;
import org.example.dto.tm.CustomerTM;
import org.example.dto.tm.ItemTM;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ItemFormViewController implements Initializable {


    public TextField txtItemCode;
    public TextField txtItemDescription;
    public TextField txtUnitPrice;
    public TextField txtQtyOnHand;
    public TextField txtSearch;
    public TableView<ItemTM> tblItem;
    public TableColumn<ItemTM, String> colItemCode;
    public TableColumn<ItemTM, String> colDescription;
    public TableColumn<ItemTM, Double> colUnitPrice;
    public TableColumn<ItemTM, Integer> colQtyOnHand;
    public Button btnSave;
    public Button btnDelete;
    public Button btnUpdate;
    public Button btnLogOut;
    public AnchorPane pane;


    private ItemBO itemBO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            itemBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);
            colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
            colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
            colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

            loadAllItems();

            txtSearch.textProperty().addListener((observableValue, previous, current) -> {
                if (!Objects.equals(previous, current)) {
                    tblItem.getItems().clear();
                    List<ItemTM> collect = itemBO.searchItemByText(current).stream().map(this::toItemTm).collect(Collectors.toList());
                    tblItem.setItems(FXCollections.observableArrayList(collect));
                }
            });
        } catch (RuntimeException exception) {
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
        }

    }

    public void btnItemSaveOnAction(ActionEvent actionEvent) {
        try {
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setCode(txtItemCode.getText());
            itemDTO.setDescription(txtItemDescription.getText());
            itemDTO.setPrice(Double.parseDouble(txtUnitPrice.getText()));
            itemDTO.setQtyOnHand(Integer.parseInt(txtQtyOnHand.getText()));

            itemBO.save(itemDTO);
            new Alert(Alert.AlertType.INFORMATION, "Item Added").show();
            loadAllItems();
            clearFields();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void clearFields() {
        txtItemCode.clear();
        txtItemDescription.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
    }

    private void loadAllItems() {
        try {
            List<ItemDTO> all = itemBO.getAll();
            ObservableList<ItemTM> itemTMObservableList = FXCollections.observableArrayList();
            all.forEach(itemDTO -> itemTMObservableList.add(new ItemTM(itemDTO.getCode(), itemDTO.getDescription(), itemDTO.getPrice(), itemDTO.getQtyOnHand())));
            tblItem.setItems(itemTMObservableList);
        } catch (RuntimeException exception) {
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
            tblItem.getItems().clear();
        }
    }

    public void btnItemUpdateOnAction(ActionEvent actionEvent) {
        try {
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setCode(txtItemCode.getText());
            itemDTO.setDescription(txtItemDescription.getText());
            itemDTO.setPrice(Double.parseDouble(txtUnitPrice.getText()));
            itemDTO.setQtyOnHand(Integer.parseInt(txtQtyOnHand.getText()));

            itemBO.update(itemDTO);
            new Alert(Alert.AlertType.INFORMATION, "Item Updated").show();
            loadAllItems();
            clearFields();
        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            clearFields();
        }
    }


    public void btnItemDeleteOnAction(ActionEvent actionEvent) {
        ItemTM selectedItem = tblItem.getSelectionModel().getSelectedItem();
        try {
            if (selectedItem != null) {
                itemBO.delete(selectedItem.getCode());
                new Alert(Alert.AlertType.INFORMATION, "Item Deleted").show();
                loadAllItems();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Select Item first!").show();
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


    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        System.out.println("logout");
        URL resource = getClass().getResource("/view/Dashboard.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        pane.getChildren().clear();
        pane.getChildren().add(load);
    }

    private ItemTM toItemTm(ItemDTO itemDTO) {
        ItemTM itemTM = new ItemTM();
        itemTM.setCode(itemTM.getCode());
        itemTM.setDescription(itemTM.getDescription());
        itemTM.setPrice(itemTM.getPrice());
        itemTM.setQtyOnHand(itemTM.getQtyOnHand());
        return itemTM;
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
    }

    public void txtSearchOnKeyReleased(KeyEvent keyEvent) {
    }

    public void tblItemsOnMouseClicked(MouseEvent mouseEvent) {
        ItemTM selectedItem = tblItem.getSelectionModel().getSelectedItem();
        try {
            if (selectedItem != null) {
                btnUpdate.setDisable(false);
                btnDelete.setDisable(false);
                btnSave.setDisable(true);
                txtItemCode.setText(selectedItem.getCode());
                txtItemDescription.setText(selectedItem.getDescription());
                txtUnitPrice.setText(String.valueOf(selectedItem.getPrice()));
                txtQtyOnHand.setText(String.valueOf(selectedItem.getQtyOnHand()));
            } else {
                btnUpdate.setDisable(true);
            }
        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
