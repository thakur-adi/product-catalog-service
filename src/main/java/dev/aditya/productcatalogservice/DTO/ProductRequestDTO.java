package dev.aditya.productcatalogservice.DTO;

import dev.aditya.productcatalogservice.Model.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDTO {
    String productName;
    String description;
    String imageUrl;
    String categoryName;
    double price;
}
