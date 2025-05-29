package com.cebem.medidor.models;

import lombok.Data;

@Data
public class Hero {
    private int id;
    private String name;
    private String localized_name;
    private String primary_attr;
    private String attack_type;
    private String[] roles;
    private int legs;
}