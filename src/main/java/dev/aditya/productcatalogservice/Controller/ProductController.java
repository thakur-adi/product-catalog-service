package dev.aditya.productcatalogservice.Controller;

import dev.aditya.productcatalogservice.DTO.ProductRequestDTO;
import dev.aditya.productcatalogservice.DTO.ProductResponseDTO;
import dev.aditya.productcatalogservice.Exception.ProductIdMissingException;
import dev.aditya.productcatalogservice.Exception.ProductNotFoundException;
import dev.aditya.productcatalogservice.Model.Product;
import dev.aditya.productcatalogservice.Service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

//    @Autowired
//    @Qualifier("StorageProductService")
    private ProductService productService;

    ProductController(@Qualifier("StorageProductService") ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable("id") long prodId) throws ProductNotFoundException {
        Product product = productService.getProductById(prodId);
        return new ResponseEntity<>(product.convertToResponseDTO(),HttpStatus.OK);
    }



    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() throws ProductNotFoundException {
        return new ResponseEntity<>( productService.getAllProducts()
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
    public ResponseEntity<ProductResponseDTO> createNewProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        return new ResponseEntity<>(productService.createNewProduct(productRequestDTO.getProductName(),
                                                                    productRequestDTO.getDescription(),
                                                                    productRequestDTO.getImageUrl(),
                                                                    productRequestDTO.getPrice(),
                                                                    productRequestDTO.getCategoryName()
                                                                    )
                                                    .convertToResponseDTO(),
                                    HttpStatus.CREATED);
    }




    //Again Delete operation has a void return type, to give out proper response with a message we wrap it into a response entity with status as 'ok'
    @DeleteMapping("/{Id}")
    public ResponseEntity<String> deleteProductById(@PathVariable("Id") long id) throws ProductNotFoundException {
        Product product = productService.deleteProductById(id);
        return new ResponseEntity<>( "Product: " + product.getName() +", Category: "+ product.getCategory().getName() +" has been deleted Successfully",
                                    HttpStatus.OK);
    }



    //To handle if Client tries to bypass without inputting any id. Spring catches it on its own and throws an error
    // Spring never lets it reach service layer. But this way we can give out a particular response instead of an Error
    //@DeleteMapping("/") could have been used as wel but would just take care of Delete Request
    @RequestMapping(value = "/",method = {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.PUT})
    public ResponseEntity<String> handleMissingId() {
        throw new ProductIdMissingException("Please enter a valid Product Id");
    }



    /* This is GetMapping for Query Parameter
    "https://fakestoreApi.com/products?prodId={id}"
    @GetMapping("/productId")
    public ProductResponseDTO getProductId(@RequestParam("prodId") long prodId)
    {
        return null;
    }
    */

}