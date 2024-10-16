package org.example.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OrderItemDTO {
    private Long id;
    private ItemDTO item;
    private int quantity;
    private double totalPrice;
}
