package org.example.dto.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.dto.CustomerDTO;
import org.example.dto.OrderItemDTO;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OrdersTM {
    private String Order_id;
    private Date orderDate;
    private CustomerDTO customer;
    private List<OrderItemDTO> orderItems;
}
