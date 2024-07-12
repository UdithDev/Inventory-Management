package org.example.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemTM {
    private String code;
    private String description;
    private double unitPrice;
    private int qtyOnHand;
}
