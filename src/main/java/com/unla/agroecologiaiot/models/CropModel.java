package com.unla.agroecologiaiot.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CropModel {
    
    private long cropId;

    private String name;
    private long ownerId;
}