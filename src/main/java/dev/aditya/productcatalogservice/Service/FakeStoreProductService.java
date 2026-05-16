package dev.aditya.productcatalogservice.Service;

import dev.aditya.productcatalogservice.DTO.FakeStoreDTO;
import dev.aditya.productcatalogservice.Exception.ProductNotFoundException;
import dev.aditya.productcatalogservice.Model.Category;
import dev.aditya.productcatalogservice.Model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

//From this service class we are communicating to another 3rd party service called FakeStore. This acts as a proxy for FakeStore.
@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductServices {

    RestTemplate restTemplate;
    String baseURL = "https://fakestoreapi.com/products";


    //Injecting the restTemplate reference using constructor -> This is known as Constructor Injection
    FakeStoreProductService(RestTemplate restTemplate)
    {
        this.restTemplate=restTemplate;
    }


    @Override
    public Product getProductById(long prodId) throws ProductNotFoundException {
        //we can also call getForEntity() and get the status from there.
        FakeStoreDTO fakeStoreDTO = restTemplate.getForObject(baseURL+"/"+prodId, FakeStoreDTO.class, prodId);
        if (fakeStoreDTO==null)
        {
            throw  new ProductNotFoundException("Product doesn't Exist");
        }
        return fakeStoreDTO.convertToProduct();
    }

    @Override
    public List<Product> getAllProducts()
    {
        FakeStoreDTO[] fakeStoreDTOS = restTemplate.getForObject(baseURL,FakeStoreDTO[].class);
       return Arrays.stream(fakeStoreDTOS)
                    .map(fakeStoreDTO -> fakeStoreDTO.convertToProduct())
                    .toList();
    }

    @Override
    public Product createNewProduct(String name, String desc, String imageURL, double price, Category category)
    {
     ResponseEntity<FakeStoreDTO> fakeStoreDTOResponseEntity = restTemplate.postForEntity(baseURL,
                                                            createProductFromParams(name, desc, imageURL, price, category)
                                                            .convertToFakeStoreDTO(),
                                                            FakeStoreDTO.class);
        if(fakeStoreDTOResponseEntity.getStatusCode().isSameCodeAs(HttpStatus.CREATED))
        {return fakeStoreDTOResponseEntity.getBody().convertToProduct();}
        else {
            return null;
        }

    }

    @Override
    public void deleteProductById(long prodId) throws ProductNotFoundException{
        Product product = getProductById(prodId);
        restTemplate.delete(baseURL+"/"+prodId,prodId);
    }
    
    
    private Product createProductFromParams(String name, String desc, String imageURL, double price, Category category)
    {
        Product product = new Product();
        product.setId(100l);
        product.setName(name);
        product.setDescription(desc);
        product.setImageUrl(imageURL);
        product.setPrice(price);
        product.setCategory(category);
        return product;  
    }
}
