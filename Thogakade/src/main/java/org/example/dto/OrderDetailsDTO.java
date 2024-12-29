package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.entity.Item;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OrderDetailsDTO implements SuperDTO {
    private String id;
    private String item;
    private Integer quantity;
    private LocalDate date;
    private String orderId;
}
