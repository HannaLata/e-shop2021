package com.hannalata.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class User {

    private Integer id;
    private String login;
    private String password;
    private String firstName;
    private String secondName;
    private String email;
    private String phone;

    public User(String login, String password, String firstName, String secondName, String email, String phone) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.phone = phone;
    }
}
