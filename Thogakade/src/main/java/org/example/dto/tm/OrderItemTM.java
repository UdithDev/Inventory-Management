package org.example.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemTM {
    private String orderId;
    private String itemCode;
    private int qty;
    private double unitPrice;
}
