package org.example.bo.util;

import org.example.dto.CustomerDTO;
import org.example.dto.ItemDTO;
import org.example.entity.Customer;
import org.example.entity.Item;

public class Converter {
    private static Converter converter;

    private Converter() {

    }

    public static Converter getInstance() {
        return converter == null ? converter = new Converter() : converter;
    }

    public CustomerDTO tocustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhone(customer.getPhone());
        return customerDTO;
    }

    public Customer toCustomerEntity(CustomerDTO customerDTO) {
        System.out.println(customerDTO);
        Customer customerEntity = new Customer();
        customerEntity.setId(customerDTO.getId());
        customerEntity.setName(customerDTO.getName());
        customerEntity.setEmail(customerDTO.getEmail());
        customerEntity.setPhone(customerDTO.getPhone());
        return customerEntity;
    }

    public ItemDTO toItemDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setCode(item.getCode());
        itemDTO.setDescription(item.getDescription());
        itemDTO.setPrice(item.getPrice());
        itemDTO.setQtyOnHand(item.getQtyOnHand());
        return itemDTO;
    }

    public Item toItemEntity(ItemDTO itemDTO) {
        System.out.println(itemDTO);
        Item itemEntity = new Item();
        itemEntity.setCode(itemDTO.getCode());
        itemEntity.setDescription(itemDTO.getDescription());
        itemEntity.setPrice(itemDTO.getPrice());
        itemEntity.setQtyOnHand(itemDTO.getQtyOnHand());
        return itemEntity;
    }
}
