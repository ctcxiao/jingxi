package com.example.employee.controller;

import com.example.employee.entity.OrderCreateEntity;
import com.example.employee.entity.Orders;
import com.example.employee.entity.ResponseOrders;
import com.example.employee.repository.OrderRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;


    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public ResponseEntity<List<Orders>> createOrder(@RequestBody String body){
        List<OrderCreateEntity> orderCreateEntities = new Gson().fromJson(body, new TypeToken<ArrayList<OrderCreateEntity>>() {}.getType());
        final List<Orders> ordersList = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        orderCreateEntities.forEach((orderCreateEntity)->{
            String createTime =  simpleDateFormat.format(System.currentTimeMillis());
            Orders orders = new Orders(0,"", orderCreateEntity.getPurchaseCount(),
                    createTime, 30.30, 1, ""+orderCreateEntity.getProductId());
            orderRepository.save(orders);
            ordersList.add(orders);
        });
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("location", "jingxi");
        return new ResponseEntity<>(ordersList, responseHeader, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.PUT)
    public ResponseEntity payForOrder(@PathVariable("id")int id, @RequestParam("orderStatus")String status){
        orderRepository.updateOrderStatus(status, id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    public ResponseOrders findOrdersById(@PathVariable("id") int id){
        Orders orders = orderRepository.findById(id);
        if (orders == null){
            throw new RuntimeException("there is no orders");
        }
        String purchaseString = orders.getOrderDetail();
        List<Integer> purchaseItemList = new ArrayList<>();
        for (int i = 0; i < purchaseString.length(); i++) {
            purchaseItemList.add(purchaseString.charAt(i)-'0');
        }
        return new ResponseOrders(orders.getId(), orders.getTotalPrice(), orders.getUserId(), orders.getBuyTime(), purchaseItemList);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public List<Orders> findOrdersBuUserId(@RequestParam("id") int id){
        return orderRepository.findAllByUserId(id);
    }
}
