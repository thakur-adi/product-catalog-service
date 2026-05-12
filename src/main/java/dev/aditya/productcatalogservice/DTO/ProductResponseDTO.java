package dev.aditya.productcatalogservice.DTO;

import dev.aditya.productcatalogservice.Model.Category;
import dev.aditya.productcatalogservice.Model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {

    private Long id;
    private String name;
    private String desc;
    private String imageUrl;
    private Double price;
    private Category category;


    //Product to ProductDTO Mapper
    public void buildDTO(Product product) {
        this.setId(product.getId());
        this.setName(product.getName());
        this.setDesc(product.getDescription());
        this.setImageUrl(product.getImageUrl());
        this.setPrice(product.getPrice());
        this.setCategory(product.getCategory());
    }
}
