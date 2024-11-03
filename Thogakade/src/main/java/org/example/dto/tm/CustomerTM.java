package org.example.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.dto.OrderDTO;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CustomerTM {
    private String id;
    private String name;
    private String email;
    private String phone;
}
