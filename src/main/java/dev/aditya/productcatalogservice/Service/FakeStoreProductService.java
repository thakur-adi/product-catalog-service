package dev.aditya.productcatalogservice.Service;

import dev.aditya.productcatalogservice.DTO.FakeStoreDTO;
import dev.aditya.productcatalogservice.Exception.ProductIdMissingException;
import dev.aditya.productcatalogservice.Exception.ProductNotFoundException;
import dev.aditya.productcatalogservice.Model.Category;
import dev.aditya.productcatalogservice.Model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

//From this service class we are communicating to another 3rd party service called FakeStore. This acts as a proxy for FakeStore.
@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductServices {

    RestTemplate restTemplate;
    RestClient restClient;
    private final String baseURL = "https://fakestoreapi.com/products";


    //Injecting the restTemplate reference using constructor -> This is known as Constructor Injection
    //@Autowired
    FakeStoreProductService(RestClient restClient)
    {
        this.restClient = restClient;
    }



//    @Autowired(required = false) (required condition is a Fail-Safe in case there is no bean for rest template it'll try injecting in the other constructor)
//    FakeStoreProductService(RestTemplate restTemplate)
//    {
//        this.restTemplate=restTemplate;
//    }



    @Override
    public Product getProductById(long prodId) throws ProductNotFoundException {

        FakeStoreDTO fakeStoreDTO = restClient.get()
                                              .uri(baseURL+"/"+prodId)// <--- This variable is for the URL template nothing to do http request body. Control WHERE the request goes (modifying the web link string).
                                              .retrieve()
                                              .body(FakeStoreDTO.class);

        //Using Rest Template
        //we can also call getForEntity() and get the status from there.
        //restTemplate.getForObject(baseURL+"/"+prodId, FakeStoreDTO.class, prodId);

        if (fakeStoreDTO==null)
        {
            throw  new ProductNotFoundException("Product doesn't Exist");
        }
        return fakeStoreDTO.convertToProduct();
    }



    @Override
    public List<Product> getAllProducts()
    {
       FakeStoreDTO[] fakeStoreDTOS = restClient.get()
                                               .uri(baseURL)
                                               .retrieve()
                                               .body(FakeStoreDTO[].class);

       //restTemplate.getForObject(baseURL,FakeStoreDTO[].class);

        if (fakeStoreDTOS == null || fakeStoreDTOS.length==0)
        {
            throw new NullPointerException("No Products exist yet");
        }
       return Arrays.stream(fakeStoreDTOS)
                    .map(fakeStoreDTO -> fakeStoreDTO.convertToProduct())
                    .toList();
    }




    @Override
    public Product createNewProduct(String name, String desc, String imageURL, double price, Category category)
    {
        ResponseEntity<FakeStoreDTO> fakeStoreDTOResponseEntity= restClient
                                                                .post()
                                                                .uri(baseURL) // <--- This variable is for the URL template
                                                                //This is the Http request Body. Always pass the object in body() and not it uri unlike rest template.
                                                                .body(createProductFromParams(name,desc,imageURL,price,category)// This is used in POST and PUT.
                                                                     .convertToFakeStoreDTO())
                                                                .retrieve()
                                                                .toEntity(FakeStoreDTO.class);


     /*restTemplate.postForEntity(baseURL,createProductFromParams(name, desc, imageURL, price, category).convertToFakeStoreDTO(),
                                  FakeStoreDTO.class);*/

        if(fakeStoreDTOResponseEntity.hasBody())
        {
            if(fakeStoreDTOResponseEntity.getStatusCode().isSameCodeAs(HttpStatus.CREATED))
            {
                assert fakeStoreDTOResponseEntity.getBody() != null;
                return fakeStoreDTOResponseEntity.getBody().convertToProduct();
            }
            else {
                throw new InternalError("Couldn't create the object. Internal server issue encountered. Please try again later!");
            }
        }
        else
        {
            throw new NullPointerException("Couldn't create the object");
        }
    }



    @Override
    public Product deleteProductById(long prodId) throws ProductNotFoundException{
        Product product = getProductById(prodId);
        ResponseEntity<FakeStoreDTO> fakeStoreDTOResponseEntity=restClient.delete()
                                                                          .uri(baseURL+"/"+prodId) //No need to pass id twice, Spring can get confused
                                                                          .retrieve()
                                                                          .toEntity(FakeStoreDTO.class);

        //restTemplate.delete(baseURL+"/"+prodId,prodId);

        if(fakeStoreDTOResponseEntity.hasBody())
        {
            if(fakeStoreDTOResponseEntity.getStatusCode().equals(HttpStatus.OK))
            {
                return fakeStoreDTOResponseEntity.getBody().convertToProduct();
            }
            else
            {
                if(fakeStoreDTOResponseEntity.getStatusCode().is4xxClientError()) {
                    throw new ProductNotFoundException("Product doesn't Exist");
                }
                else {
                    throw new InternalError("Some internal Error occured");
                }
            }
        }
        else {
            throw new NullPointerException("Couldn't Delete any product, Null Pointer encountered");
        }



    }
    

 //Helper Method for creating a model Object
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