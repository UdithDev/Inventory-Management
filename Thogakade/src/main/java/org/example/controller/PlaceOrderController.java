package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BOFactory;
import org.example.bo.SuperBO;
import org.example.bo.custom.OrderBO;
import org.example.dao.custom.OrderDetailsDAO;
import org.example.dto.CustomerDTO;
import org.example.dto.ItemDTO;
import org.example.dto.OrderDTO;
import org.example.dto.OrderDetailsDTO;
import org.example.dto.tm.OrdersTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class PlaceOrderController implements Initializable {

    public TextField txtOrderId;
    public TextField txtOrderDate;
    public TextField txtCustomerName;
    public TextField txtDescription;


    public ComboBox cmbCustomerId;
    public ComboBox cmbItemCode;
    public TextField txtQtyOnHand;
    public TableView<OrdersTM> tblOrderCart;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colTotal;
    public TableColumn colAction;
    public TextField txtNetTotal;
    public TextField txtUnitPrice;
    public TextField txtQty;
    public AnchorPane pane;
    public Button btnBack;

    private OrderBO orderBO;
    private ObservableList<OrdersTM> observableList = FXCollections.observableArrayList();


    public void cmbCustomerIdOnAction(ActionEvent actionEvent) {
        String id = (String) cmbCustomerId.getValue();
//        cmbCustomerId.setDisable(true);

        try {
            CustomerDTO customerDTO = orderBO.searchByCustomerId(id);
            txtCustomerName.setText(customerDTO.getName());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "please try again...!").show();
            e.printStackTrace();
        }
    }

    public void cmbItemCodeOnAction(ActionEvent actionEvent) {
        String code = (String) cmbItemCode.getValue();
        try {
            ItemDTO itemDTO = orderBO.searchByItemCode(code);
            fillItemFields(itemDTO);
            txtQty.requestFocus();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "please try again...!").show();
        }
    }

    private void fillItemFields(ItemDTO itemDTO) {
        txtDescription.setText(itemDTO.getDescription());
        txtUnitPrice.setText(String.valueOf(itemDTO.getPrice()));
        txtQtyOnHand.setText(String.valueOf(itemDTO.getQtyOnHand()));
    }

    public void addToCartOnAction(ActionEvent actionEvent) {
        try {
            String code = (String) cmbItemCode.getValue();
            String description = txtDescription.getText();
            int qty = Integer.parseInt(txtQty.getText());
            double unitPrice = Double.parseDouble(txtUnitPrice.getText());

            double total = qty * unitPrice;
            Button btnRemove = new Button("Remove");
            btnRemove.setCursor(Cursor.HAND);
            setRemoveBtnOnAction(btnRemove);

            if (!observableList.isEmpty()) {
                for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
                    if (colCode.getCellData(i).equals(code)) {
                        qty += (int) colQty.getCellData(i);
                        total = qty * unitPrice;

                        observableList.get(i).setQty(qty);
                        observableList.get(i).setTotal(total);

                        tblOrderCart.refresh();
                        calculateNetTotal();
                        return;
                    }
                }
            }
            OrdersTM ordersTM = new OrdersTM(code, description, qty, unitPrice, total, btnRemove);
            observableList.add(ordersTM);
            tblOrderCart.setItems(observableList);
            calculateNetTotal();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "please tyr again...!").show();
        }
    }

    private void calculateNetTotal() {
        double netTotal = 0.0;
        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            double total = (double) colTotal.getCellData(i);
            netTotal += total;
        }
        txtNetTotal.setText(String.valueOf(netTotal));
    }

    private void setRemoveBtnOnAction(Button btn) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {

                int index = tblOrderCart.getSelectionModel().getSelectedIndex();
                observableList.remove(index + 1);

                tblOrderCart.refresh();
                calculateNetTotal();
            }

        });
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        System.out.println("Back to dashboard");
        URL resource = getClass().getResource("/view/Dashboard.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        pane.getChildren().clear();
        pane.getChildren().add(load);
    }

    public void placeOrderOnAction(ActionEvent actionEvent) throws SQLException {
        String orderId = txtOrderId.getText();
        String customerId = (String) cmbCustomerId.getValue();
        String itemCode = (String) cmbItemCode.getValue();
        int qty = Integer.parseInt(txtQty.getText());

        List<OrderDetailsDTO> order_detailsDTOList = new ArrayList<>();
        order_detailsDTOList.add(new OrderDetailsDTO(orderId, itemCode, qty, LocalDate.now(), customerId));

        boolean isPlaced = orderBO.placeOrder(new OrderDTO(orderId, customerId, order_detailsDTOList));
        if (isPlaced) {
            Alert orderPlacedAlert = new Alert(Alert.AlertType.INFORMATION, "Order Placed...!");
            orderPlacedAlert.show();
            clearFields();

//            orderPlacedAlert.setOnHidden(event -> {
//                try {
//                    printBills();
//                } catch (Exception e) {
//                    new Alert(Alert.AlertType.ERROR, "please try again...!").show();
//                }
//            });

        } else {
            new Alert(Alert.AlertType.ERROR, "Order is Not Placed...!").show();
        }
        generateNextOrderId();
    }

    private void clearFields() {
        txtQty.clear();
        txtDescription.clear();
        txtUnitPrice.clear();
        txtCustomerName.clear();
        txtNetTotal.clear();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            orderBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER);
            colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
            colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
            colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
            colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
            colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));

            tblOrderCart.setItems(FXCollections.observableArrayList());

            setOrderDate();
            generateNextOrderId();
            loadCustomerIds();
            loadItemCodes();
        } catch (RuntimeException exception) {
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
        }
    }

    private void generateNextOrderId() {
        try {
            String id = orderBO.generateNextOrderId();
            txtOrderId.setText(id);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "please try again...!").show();
        }
    }

    private void setOrderDate() {
        txtOrderDate.setText(String.valueOf(LocalDate.now()));
    }

    private void loadCustomerIds() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            List<String> customerId = orderBO.loadCustomerIds();

            for (String id : customerId) {
                observableList.add(id);
            }
            cmbCustomerId.setItems(observableList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "please try again...!").show();
        }
    }

    private void loadItemCodes() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            List<String> itemCodes = orderBO.loadItemCodes();

            for (String code : itemCodes) {
                observableList.add(code);
            }
            cmbItemCode.setItems(observableList);

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "please try again...!").show();
        }
    }
}
