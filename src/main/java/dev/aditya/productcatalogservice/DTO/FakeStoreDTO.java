package dev.aditya.productcatalogservice.DTO;

import dev.aditya.productcatalogservice.Model.Category;
import dev.aditya.productcatalogservice.Model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreDTO {
    private Long id;
    private String title;
    private String description;
    private String category;
    private String image;
    private Double price;



    //FakeStoreDTO to Product Model Mapper
    public Product convertToProduct() {
        Product productModel = new Product();
        productModel.setId(id);
        productModel.setPrice(price);
        productModel.setName(title);
        productModel.setDescription(description);
        productModel.setCategory(new Category(category));
        productModel.setImageUrl(image);
        return productModel;
    }
}
