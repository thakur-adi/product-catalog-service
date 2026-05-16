package dev.aditya.productcatalogservice.Model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Base {
    private long id;
    private String name;
}
