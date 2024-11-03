package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO implements SuperDTO {
    private String id;
    private String name;
    private String email;
    private String phone;
}
