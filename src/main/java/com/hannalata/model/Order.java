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
    private Integer userId;
    private Integer cartId;
    private Integer amount;

    public Order(Integer userId, Integer cartId, Integer amount) {
        this.userId = userId;
        this.cartId = cartId;
        this.amount = amount;
    }
}
