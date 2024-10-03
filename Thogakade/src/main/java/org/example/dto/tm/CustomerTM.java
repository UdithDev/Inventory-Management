package org.example.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.OrderDTO;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerTM {
    private Long id;
    private String name;
    private String email;
    private String phone;

}
