//package com.inventorysystem.bo.custom.impl;
//
//import com.inventorysystem.bo.custom.ItemBO;
//import com.inventorysystem.dao.DAOFactory;
//import com.inventorysystem.dao.custom.ItemDAO;
//import com.inventorysystem.dto.ItemDTO;
//import com.inventorysystem.entity.Item;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ItemBOImpl implements ItemBO {
//
//    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
//
//    @Override
//    public void addItem(ItemDTO itemDTO) throws Exception {
//        itemDAO.save(new Item(itemDTO.getCode(), itemDTO.getDescription(), itemDTO.getUnitPrice(), itemDTO.getQtyOnHand()));
//    }
//
//    @Override
//    public void UpdateItem(ItemDTO itemDTO) throws Exception {
//        itemDAO.save(new Item(itemDTO.getCode(), itemDTO.getDescription(), itemDTO.getUnitPrice(), itemDTO.getQtyOnHand()));
//    }
//
//    @Override
//    public void deleteItem(String code) throws Exception {
//        itemDAO.delete(code);
//    }
//
//    @Override
//    public ItemDTO getItem(String code) throws Exception {
//        Item item = itemDAO.search(code);
//        return new ItemDTO(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand());
//    }
//
//    @Override
//    public ArrayList<ItemDTO> getAllItems() throws Exception {
//        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();
//        List<Item> items = itemDAO.getAll();
//        for (Item item : items) {
//            itemDTOS.add(new ItemDTO(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));
//        }
//        return itemDTOS;
//    }
//}
