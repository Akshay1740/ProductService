package com.example.productservice_proxy.Inhertanceexamples.tableperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Entity(name = "tbc_instructor")
public class Instructor extends User{
    private String company;
}
