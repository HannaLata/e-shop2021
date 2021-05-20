package com.hannalata.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Item {

    private Integer id;
    private String code;
    private String name;
    private Integer price;
    private Integer availability;

    public Item(String code, String name, Integer price, Integer availability) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.availability = availability;
    }
}
