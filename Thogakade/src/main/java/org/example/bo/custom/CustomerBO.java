package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.CustomerDTO;

import java.util.List;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO<CustomerDTO> {
    List<CustomerDTO> searchCustomerByText(String text);

}
