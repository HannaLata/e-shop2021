package com.hannalata.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Cart {

    private Integer id;
    private Status status;
    private Integer userId;
    private Long creationTime;

    enum Status {
        OPEN,
        TO_BE_CLOSED,
        CLOSED
    }

    public Cart(Status status, Integer userId, Long creationTime) {
        this.status = status;
        this.userId = userId;
        this.creationTime = creationTime;
    }
}
