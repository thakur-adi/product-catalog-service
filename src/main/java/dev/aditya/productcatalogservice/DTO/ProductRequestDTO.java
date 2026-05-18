package dev.aditya.productcatalogservice.DTO;

import dev.aditya.productcatalogservice.Model.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDTO {
    //These var names should match exactly like the one being sent by the client, for spring to catch and match the incoming values.
    String productName;
    String description;
    String imageUrl;
    String categoryName;
    double price;
}
