package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.CustomerDTO;
import org.example.dto.ItemDTO;
import org.example.entity.Customer;
import org.example.entity.Item;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public interface ItemBO extends SuperBO<ItemDTO> {
    List<ItemDTO> searchItemByText(String text);
}
