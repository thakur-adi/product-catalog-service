package dev.aditya.productcatalogservice.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends Base{
    private double price;
    private Category category;
}
