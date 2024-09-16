package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.ItemDTO;

import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    void addItem(ItemDTO itemDTO) throws Exception;
    void UpdateItem(ItemDTO itemDTO) throws Exception;
    void deleteItem(String code) throws Exception;
    ItemDTO getItem(String code) throws Exception;
    ArrayList<ItemDTO> getAllItems() throws Exception;
}
