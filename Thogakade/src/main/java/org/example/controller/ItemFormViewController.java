//package com.inventorysystem.controller;
//
//import com.inventorysystem.bo.custom.ItemBO;
//import com.inventorysystem.dto.tm.ItemTM;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.AnchorPane;
//import com.inventorysystem.bo.BOFactory;
//import com.inventorysystem.dto.ItemDTO;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.Optional;
//import java.util.ResourceBundle;
//
//public class ItemFormViewController implements Initializable {
//    private final ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);
//    public TextField txtItemCode;
//    public TextField txtItemDescription;
//    public TextField txtUnitPrice;
//    public TextField txtQtyOnHand;
//    public TableView<ItemTM> tblItem;
//    public TableColumn<ItemTM, String> colItemCode;
//    public TableColumn<ItemTM, String> colDescription;
//    public TableColumn<ItemTM, Double> colUnitPrice;
//    public TableColumn<ItemTM, Integer> colQtyOnHand;
//    public Button btnSave;
//    public Button btnDelete;
//    public AnchorPane pane;
//    public Button btnUpdate;
//    public Button btnLogOut;
//
//    public void btnItemSaveOnAction(ActionEvent actionEvent) {
//        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
//        confirmationAlert.setTitle("Save Item");
//        confirmationAlert.setHeaderText("Are you sure you want to save this item?");
//        Optional<ButtonType> result = confirmationAlert.showAndWait();
//        if (result.isPresent() && result.get() == ButtonType.OK) {
//            try {
//                ItemDTO itemDTO = new ItemDTO(
//                        txtItemCode.getText(),
//                        txtItemDescription.getText(),
//                        Double.parseDouble(txtUnitPrice.getText()),
//                        Integer.parseInt(txtQtyOnHand.getText())
//                );
//                itemBO.addItem(itemDTO);
//                getAllItems();
//                clearFields();
//                showAlert(Alert.AlertType.INFORMATION, "Item Saved", "Item has been saved successfully.");
//            } catch (Exception e) {
//                e.printStackTrace();
//                showAlert(Alert.AlertType.ERROR, "Error", "Failed to save item.");
//            }
//        }
//
//    }
//
//    public void btnItemDeleteOnAction(ActionEvent actionEvent) {
//        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
//        confirmationAlert.setTitle("Delete Item");
//        confirmationAlert.setHeaderText("Are you sure you want to delete this item?");
//        Optional<ButtonType> result = confirmationAlert.showAndWait();
//        if (result.isPresent() && result.get() == ButtonType.OK) {
//            try {
//                String itemCode = txtItemCode.getText();
//                itemBO.deleteItem(itemCode);
//                getAllItems();
//                clearFields();
//                showAlert(Alert.AlertType.INFORMATION, "Item Deleted", "Item has been deleted successfully.");
//            } catch (Exception e) {
//                e.printStackTrace();
//                showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete item.");
//            }
//        }
//    }
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        setupTable();
//        getAllItems();
//    }
//
//    private void showAlert(Alert.AlertType alertType, String title, String message) {
//        Alert alert = new Alert(alertType);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//
//    private void setupTable() {
//        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
//        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
//        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
//        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
//    }
//
//    private void getAllItems() {
//        ObservableList<ItemTM> observableList = FXCollections.observableArrayList();
//        try {
//            ArrayList<ItemDTO> allItems = itemBO.getAllItems();
//            for (ItemDTO itemDTO : allItems) {
//                observableList.add(new ItemTM(
//                        itemDTO.getCode(),
//                        itemDTO.getDescription(),
//                        itemDTO.getUnitPrice(),
//                        itemDTO.getQtyOnHand()
//                ));
//            }
//            tblItem.setItems(observableList);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private void clearFields() {
//        txtItemCode.clear();
//        txtItemDescription.clear();
//        txtUnitPrice.clear();
//        txtQtyOnHand.clear();
//    }
//
//    public void btnItemUpdateOnAction(ActionEvent actionEvent) {
//        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
//        confirmationAlert.setTitle("Update Item");
//        confirmationAlert.setHeaderText("Are you sure you want to update this item?");
//        Optional<ButtonType> result = confirmationAlert.showAndWait();
//        if (result.isPresent() && result.get() == ButtonType.OK) {
//            try {
//                ItemDTO itemDTO = new ItemDTO(
//                        txtItemCode.getText(),
//                        txtItemDescription.getText(),
//                        Double.parseDouble(txtUnitPrice.getText()),
//                        Integer.parseInt(txtQtyOnHand.getText())
//                );
//                itemBO.UpdateItem(itemDTO);
//                getAllItems();
//                clearFields();
//                showAlert(Alert.AlertType.INFORMATION, "Item Updated", "Item has been updated successfully.");
//            } catch (Exception e) {
//                e.printStackTrace();
//                showAlert(Alert.AlertType.ERROR, "Error", "Failed to update item.");
//            }
//        }
//    }
//
//    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
//        URL resource = getClass().getResource("/view/Dashboard.fxml");
//        assert resource != null;
//        Parent load = FXMLLoader.load(resource);
//        pane.getChildren().clear();
//        pane.getChildren().add(load);
//    }
//}
