package com.hannalata.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Order {

    private Integer id;
    private Item item;
    private Cart cart;
    private Integer amount;

    public Order(Item item, Cart cart, Integer amount) {
        this.item = item;
        this.cart = cart;
        this.amount = amount;
    }
}
