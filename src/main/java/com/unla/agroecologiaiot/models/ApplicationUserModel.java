package com.unla.agroecologiaiot.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApplicationUserModel {

    private long userId;

    private String username;
    private String password;

    private String name;
    private String surname;
    private String email;

    private long roleId;
}
