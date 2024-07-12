package org.example.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.OrderItem;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrdersTM {
    private String id;
    private Date date;
    private String customerId;
    private List<OrderItem> orderItems;
}
