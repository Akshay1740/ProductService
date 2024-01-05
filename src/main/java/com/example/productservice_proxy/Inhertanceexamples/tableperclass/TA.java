package com.example.productservice_proxy.Inhertanceexamples.tableperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tbc_ta")
public class TA extends  User{
    private double rating;
}
