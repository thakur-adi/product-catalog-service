package dev.aditya.productcatalogservice.Controller;

import dev.aditya.productcatalogservice.DTO.ProductResponseDTO;
import dev.aditya.productcatalogservice.Model.Product;
import dev.aditya.productcatalogservice.Service.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    ProductServices productService;

    @GetMapping("/products/{id}")
    public ProductResponseDTO getProductById(@PathVariable("id") long prodId)
    {
        Product productModel = productService.getProductById(prodId);
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.buildDTO(productModel);
        return productResponseDTO;
    }

}
