package org.example.bo.util;

import org.example.dto.CustomerDTO;
import org.example.dto.ItemDTO;
import org.example.dto.OrderDTO;
import org.example.dto.OrderDetailsDTO;
import org.example.entity.Customer;
import org.example.entity.Item;
import org.example.entity.OrderDetails;
import org.example.entity.Orders;

import java.util.List;
import java.util.stream.Collectors;

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

    // Orders Mapping
    public OrderDTO toOrdersDTO(Orders orders) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(orders.getId());
        orderDTO.setCustomerId(orders.getCustomer().getId());
        orderDTO.setOrderDetails(
                orders.getOrderDetails().stream()
                        .map(this::toOrderDetailsDTO)
                        .collect(Collectors.toList())
        );
        return orderDTO;
    }

    public Orders toOrdersEntity(OrderDTO ordersDTO, Customer customer, List<OrderDetails> orderDetailsList) {
        Orders ordersEntity = new Orders();
        ordersEntity.setId(ordersDTO.getId());
        ordersEntity.setCustomer(customer);
        ordersEntity.setOrderDetails(orderDetailsList);
        return ordersEntity;
    }


    // OrderDetails Mapping
    private OrderDetailsDTO toOrderDetailsDTO(OrderDetails orderDetails) {
        OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
        orderDetailsDTO.setId(orderDetails.getId());
        orderDetailsDTO.setItem(orderDetails.getItem());
        orderDetailsDTO.setQuantity(orderDetails.getQuantity());
        orderDetailsDTO.setDate(orderDetails.getDate());
        orderDetailsDTO.setOrderId(orderDetails.getOrder().getId());
        return orderDetailsDTO;
    }

    public OrderDetails toOrderDetailsEntity(OrderDetailsDTO orderDetailsDTO, Orders order) {
        OrderDetails orderDetailsEntity = new OrderDetails();
        orderDetailsEntity.setId(orderDetailsDTO.getId());
        orderDetailsEntity.setItem(orderDetailsDTO.getItem());
        orderDetailsEntity.setQuantity(orderDetailsDTO.getQuantity());
        orderDetailsEntity.setDate(orderDetailsDTO.getDate());
        orderDetailsEntity.setOrder(order);
        return orderDetailsEntity;
    }

    // Utility Methods for List Conversion
    public List<CustomerDTO> toCustomerDTOList(List<Customer> customers) {
        return customers.stream()
                .map(this::tocustomerDTO)
                .collect(Collectors.toList());
    }

    public List<Customer> toCustomerEntityList(List<CustomerDTO> customerDTOs) {
        return customerDTOs.stream()
                .map(this::toCustomerEntity)
                .collect(Collectors.toList());
    }

    public List<ItemDTO> toItemDTOList(List<Item> items) {
        return items.stream()
                .map(this::toItemDTO)
                .collect(Collectors.toList());
    }

    public List<Item> toItemEntityList(List<ItemDTO> itemDTOs) {
        return itemDTOs.stream()
                .map(this::toItemEntity)
                .collect(Collectors.toList());
    }

    public List<OrderDTO> toOrdersDTOList(List<Orders> ordersList) {
        return ordersList.stream()
                .map(this::toOrdersDTO)
                .collect(Collectors.toList());
    }

    public List<OrderDetailsDTO> toOrderDetailsDTOList(List<OrderDetails> orderDetailsList) {
        return orderDetailsList.stream()
                .map(this::toOrderDetailsDTO)
                .collect(Collectors.toList());
    }

}
