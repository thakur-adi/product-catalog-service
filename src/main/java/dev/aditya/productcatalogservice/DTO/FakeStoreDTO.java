package dev.aditya.productcatalogservice.DTO;

import dev.aditya.productcatalogservice.Model.Category;
import dev.aditya.productcatalogservice.Model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreDTO {
    //These var names should match exactly like the one being sent by the other server.
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
        Category categoryModel = new Category();
        categoryModel.setName(category);
        productModel.setCategory(categoryModel);
        productModel.setImageUrl(image);
        return productModel;
    }
}
