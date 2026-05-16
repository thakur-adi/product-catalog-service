package dev.aditya.productcatalogservice.Controller;

import dev.aditya.productcatalogservice.DTO.ProductRequestDTO;
import dev.aditya.productcatalogservice.DTO.ProductResponseDTO;
import dev.aditya.productcatalogservice.Exception.ProductNotFoundException;
import dev.aditya.productcatalogservice.Model.Product;
import dev.aditya.productcatalogservice.Service.ProductServices;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductServices productServices;

    ProductController(@Qualifier("FakeStoreProductService") ProductServices productServices)
    {
        this.productServices = productServices;
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable("id") long prodId) throws ProductNotFoundException {
        Product product = productServices.getProductById(prodId);
        return new ResponseEntity<>(product.convertToResponseDTO(),HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts()
    {

        return new ResponseEntity<>( productServices.getAllProducts()
                                              .stream()
                                              .map(product -> product.convertToResponseDTO())
                                              .toList()
                            ,HttpStatus.OK);
    }




    //Response Entity -> A wrapper class which contains -> body Object + HTTP Status code + HTTP Header
    // our proxy successfully forwards the request and displays the result(be it null or successful), so it'll always show status code as 200 but that might not be the actual output.
    // so to get proper response from 3rd party API we need to wrap it in Response Entity. So that we can manually set headers and change the status code dynamically based on logic
    //So I have converted all the return types to a ResponseEntity
    @PostMapping
    public ResponseEntity<ProductResponseDTO> createNewProduct(@RequestBody ProductRequestDTO productRequestDTO)
    {
        return new ResponseEntity<>(productServices.createNewProduct(productRequestDTO.getName(),
                                                                    productRequestDTO.getDesc(),
                                                                    productRequestDTO.getImageURL(),
                                                                    productRequestDTO.getPrice(),
                                                                    productRequestDTO.getCategory()
                                                                    )
                                                    .convertToResponseDTO(),
                                    HttpStatus.OK);
    }




    //Again Delete operation has a void return type, to give out proper response with a message we wrap it into a response entity with status as 'ok'
    @DeleteMapping("/{Id}")
    public ResponseEntity<String> deleteProductById(@PathVariable("Id") long id) throws ProductNotFoundException {
        productServices.deleteProductById(id);
        return new ResponseEntity<>("Object Deleted Successfully",HttpStatus.OK);
    }




    /* This is GetMapping for Query Parameter
    @GetMapping("/productId")
    public ProductResponseDTO getProductId(@RequestParam("prodId") long prodId)
    {
        return null;
    }
    */

}
