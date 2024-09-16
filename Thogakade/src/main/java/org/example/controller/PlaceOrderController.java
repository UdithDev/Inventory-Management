//package com.inventorysystem.controller;
//
//import com.inventorysystem.bo.custom.OrderBO;
//import com.inventorysystem.dto.tm.OrdersTM;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.Initializable;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import com.inventorysystem.bo.BOFactory;
//import com.inventorysystem.dto.ItemDTO;
//
//import java.net.URL;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//import java.util.ResourceBundle;
//
//public class PlaceOrderController implements Initializable {
//
//    public Label lblDateAndTime;
//    public TextField txtOrderId;
//    public TextField txtOrderDate;
//    public TextField txtDescription;
//    public TextField txtCustomerName;
//    public TableView tblOrderCart;
//    public TableColumn colCode;
//    public TableColumn colDescription;
//    public TableColumn colQty;
//    public TableColumn colUnitPrice;
//    public TableColumn colTotal;
//    public TableColumn colAction;
//    public ComboBox cmbCustomerId;
//    public ComboBox cmbItemCode;
//    public TextField txtQtyOnHand;
//    public TextField txtUnitPrice;
//    public TextField txtQty;
//    public TextField txtNetTotal;
//
//    private OrderBO ordersBO = (OrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER);
//    private ObservableList<OrdersTM> observableList = FXCollections.observableArrayList();
//
//    public void backOnAction(ActionEvent actionEvent) {
//    }
//
//    public void cmbCustomerIdOnAction(ActionEvent actionEvent) {
//    }
//
//    public void cmbItemCodeOnAction(ActionEvent actionEvent) {
//
//    }
//
//    private void fillItemFields(ItemDTO itemDTO) {
//        txtDescription.setText(itemDTO.getDescription());
//        txtUnitPrice.setText(String.valueOf(itemDTO.getUnitPrice()));
//        txtQtyOnHand.setText(String.valueOf(itemDTO.getQtyOnHand()));
//    }
//
//    public void addToCartOnAction(ActionEvent actionEvent) {
//
//    }
//
//
//    private void setRemoveBtnOnAction(Button btn) {
//        btn.setOnAction((e) -> {
//            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
//            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
//
//            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();
//
//            if (result.orElse(no) == yes) {
//
//                int index = tblOrderCart.getSelectionModel().getSelectedIndex();
//                observableList.remove(index+1);
//
//                tblOrderCart.refresh();
//                calculateNetTotal();
//            }
//
//        });
//    }
//
//    private void calculateNetTotal() {
//        double netTotal = 0.0;
//        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
//            double total = (double) colTotal.getCellData(i);
//            netTotal += total;
//        }
//        txtNetTotal.setText(String.valueOf(netTotal));
//    }
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
////        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd      hh:mm");
////        Date date = new Date();
////        lblDateAndTime.setText(simpleDateFormat.format(date));
//
//        setOrderDate();
//        generateNextOrderId();
//        setCellValueFactory();
//        loadCustomerIds();
//        loadItemCodes();
//    }
//
//    private void setOrderDate() {
//        txtOrderDate.setText(String.valueOf(LocalDate.now()));
//    }
//
//    private void generateNextOrderId() {
//
//    }
//
//
//    private void setCellValueFactory() {
//        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
//        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
//        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
//        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
//        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
//        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
//    }
//
//    private void loadCustomerIds() {
//        try {
//            ObservableList<String> observableList = FXCollections.observableArrayList();
//            List<String> customerId = ordersBO.loadCustomerIds();
//
//            for (String id : customerId) {
//                observableList.add(id);
//            }
//            cmbCustomerId.setItems(observableList);
//        } catch (Exception e) {
//            new Alert(Alert.AlertType.ERROR, "please try again...!").show();
//        }
//    }
//
//    private void loadItemCodes() {
////        try {
////            ObservableList<String> observableList = FXCollections.observableArrayList();
////            List<String> itemCodes = ordersBO.loadItemCodes();
////
////            for (String code : itemCodes) {
////                observableList.add(code);
////            }
////            cmbItemCode.setItems(observableList);
////
////        } catch (SQLException e) {
////            new Alert(Alert.AlertType.ERROR, "please try again...!").show();
////        }
//    }
//
//
//    public void placeOrderOnAction(ActionEvent actionEvent) {
//    }
//}
