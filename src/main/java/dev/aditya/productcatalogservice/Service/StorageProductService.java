package dev.aditya.productcatalogservice.Service;

import dev.aditya.productcatalogservice.Model.Category;
import dev.aditya.productcatalogservice.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("StorageProductService")
@Primary
public class StorageProductService implements ProductServices {

    @Autowired
    private RestClient restClient;
    private RestTemplate restTemplate;

    @Override
    public Product getProductById(long prodId) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createNewProduct(String name, String desc, String imageUrl, double price, Category category) {
        return null;
    }

    @Override
    public void deleteProductById(long prodId) {

    }
}
