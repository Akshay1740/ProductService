package com.example.productservice_proxy.Inhertanceexamples.mappedsuperclass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "mps_instructor")
public class Instructor extends User {
    private String company;
}
