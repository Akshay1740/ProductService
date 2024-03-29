package com.example.productservice_proxy.Inhertanceexamples.singleclass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "single_instructor")
@DiscriminatorValue(value = "3")
public class Instructor extends User {
    private String company;
}
