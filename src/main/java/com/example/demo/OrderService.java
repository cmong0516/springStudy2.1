package com.example.demo;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
