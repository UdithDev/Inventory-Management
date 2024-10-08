package org.example.dto.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.CustomerDTO;
import org.example.dto.OrderItemDTO;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrdersTM {
    private String id;
    private Date orderDate;
    private CustomerDTO customer;
    private List<OrderItemDTO> orderItems;
}
