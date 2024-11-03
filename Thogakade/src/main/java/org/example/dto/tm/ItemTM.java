package org.example.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ItemTM {
    private String code;
    private String description;
    private double price;
    private int qtyOnHand;
}
