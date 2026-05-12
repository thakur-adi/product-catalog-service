package dev.aditya.productcatalogservice.Service;

import dev.aditya.productcatalogservice.DTO.FakeStoreDTO;
import dev.aditya.productcatalogservice.Model.Category;
import dev.aditya.productcatalogservice.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductServices implements ProductServices{

    RestTemplate restTemplate =new RestTemplate();
    @Override
    public Product getProductById(long prodId) {
        FakeStoreDTO fakeStoreDTO = restTemplate.getForObject("https://fakestoreapi.com/products/{Id}", FakeStoreDTO.class, prodId);
        return fakeStoreDTO.convertToProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of(null);
    }

    @Override
    public Product createNewProduct(String name, String desc, String imageUrl, double price, Category category) {
        return null;
    }

    @Override
    public void deleteProductById(long prodId) {

    }
}
