package org.example.dto.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OrdersTM {
    private String code;
    private String description;
    private Integer qty;
    private Double unitPrice;
    private Double total;
    private Button btn;
}
