package dev.aditya.productcatalogservice.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends Base{
    private String description;
    private String imageUrl;
    private Double price;
    private Category category;
}
