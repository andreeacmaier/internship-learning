package com.arobs.service;

import com.arobs.entities.Item;
import com.arobs.entities.Order;
import com.arobs.entities.OrderDetails;
import com.arobs.repository.GeneralRepo;
import com.arobs.repository.OrderDetailsRepo;
import com.arobs.repository.OrderRepo;
import com.arobs.repository.ProductRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductManager {

    private ProductRepo productRepo;
    private OrderRepo orderRepo;
    private OrderDetailsRepo orderDetailsRepo;
    private GeneralRepo generalRepo;

    public ProductManager() {
        productRepo = new ProductRepo();
        orderRepo = new OrderRepo();
        orderDetailsRepo = new OrderDetailsRepo();
        generalRepo = new GeneralRepo();
    }

    public List<Item> getItems() throws ClassNotFoundException {
        return productRepo.findAll();
    }

    public Item getItemByName(String name) throws ClassNotFoundException {
        return productRepo.findByName(name);
    }

    public Item getItemById(int id) throws ClassNotFoundException {
        return productRepo.findById(id);
    }

    public boolean saveOrderDetail(OrderDetails orderDetails) throws ClassNotFoundException {
        return orderDetailsRepo.save(orderDetails);
    }

    public List<OrderDetails> getOrderDetailsListForAUser(int userId) throws ClassNotFoundException {

        List<OrderDetails> orderDetailsList = orderDetailsRepo.findAll();

        System.out.println("==== INTO getOrderDetailsListForAUser METHOD");
        for (OrderDetails o : orderDetailsList) {
            System.out.println(o.getId());
        }

        List<Order> orders = orderRepo.findAll();

        for (Order o : orders) {
            System.out.println(o.getOrderNumber());
        }

        List<OrderDetails> shoppingCart = new ArrayList<>();

        for (Order order : orders) {
            for (OrderDetails orderDetails: orderDetailsList) {
                if (order.getUserId() == userId && orderDetails.getOrderNumber() == order.getOrderNumber()) {
                    shoppingCart.add(orderDetails);
                }
            }
        }

        return shoppingCart;
    }

    public Map<String, Integer> getShoppingCart(int userId) throws ClassNotFoundException {
        System.out.println("==== INTO getShoppingCart METHOD");
        return generalRepo.getShoppingCart(userId);
    }

}
