package com.thogakade.dto;

import com.thogakade.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OrdersDTO {
    private String id;
    private Date date;
    private String customerId;
    private List<OrderItem> orderItems;
}
