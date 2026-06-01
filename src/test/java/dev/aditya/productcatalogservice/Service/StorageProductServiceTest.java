package dev.aditya.productcatalogservice.Service;

import dev.aditya.productcatalogservice.Repository.ProductRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
class StorageProductServiceTest {

    @MockitoBean
    private ProductRepo productRepo;

    @Qualifier("StorageProductService")
    @Autowired
    private ProductService productService;
    @Test
    void testGetProductByIdReturnsProduct()
    {

    }
}