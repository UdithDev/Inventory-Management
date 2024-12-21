package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.entity.Customer;
import org.example.entity.OrderDetails;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO implements SuperDTO {
    private String id;
    private Customer customerId;
    private List<OrderDetailsDTO> orderDetailsDTOS;
}
