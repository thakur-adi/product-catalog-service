package dev.aditya.productcatalogservice.Model;

import dev.aditya.productcatalogservice.DTO.FakeStoreDTO;
import dev.aditya.productcatalogservice.DTO.ProductResponseDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends Base{
    private String description;
    private String imageUrl;
    private Double price;

    @ManyToOne
    private Category category;


    //Product to ProductResponseDTO Mapper
    public ProductResponseDTO convertToResponseDTO()
    {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setName(this.getName());
        productResponseDTO.setDescription(this.getDescription());
        productResponseDTO.setImageUrl(this.getImageUrl());
        productResponseDTO.setPrice(this.getPrice());
        productResponseDTO.setCategory(this.getCategory().getName());
        productResponseDTO.setCreatedAt(this.getCreatedAt());
        return productResponseDTO;
    }

    //Product to FakeStoreDTO Mapper
    public FakeStoreDTO convertToFakeStoreDTO(){
        FakeStoreDTO fakeStoreDTO = new FakeStoreDTO();
        fakeStoreDTO.setId(this.getId());
        fakeStoreDTO.setTitle(this.getName());
        fakeStoreDTO.setDescription(this.getDescription());
        fakeStoreDTO.setImage(this.getImageUrl());
        fakeStoreDTO.setPrice(this.getPrice());
        fakeStoreDTO.setCategory(this.getCategory().getName());
        return fakeStoreDTO;
    }
}
