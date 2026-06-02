package dev.aditya.productcatalogservice.Repository;

import dev.aditya.productcatalogservice.Model.Category;
import dev.aditya.productcatalogservice.Model.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//This is an Integration test not a Unit Test. We never run Unit Tests on Repository
@DataJpaTest //automatically marks every single @Test method in your class as @Transactional. It doesn't strictly need H2 db, but is preferred
class ProductRepoTest {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    CategoryRepo categoryRepo;


    //@BeforeEach ---> Not Required as we don't want it to run before each method i.e. before save
    //Create a dummy Product for testing -> This is a stub
    public Product createNewDummyProduct() {
        //Creating a Category first
        Category category = new Category();
        //category.setId(1);  ----> We can't set Id as its getting set in our entity class(using Strategy = Identity). This actually throws an error
        category.setName("categoryName");
        categoryRepo.save(category); // We need this first, or it'll again lead to an error when pulling all objects from DB

        Product dummyProduct = new Product();
        //dummyProduct.setId(1);  ----> Same reason as Category
        dummyProduct.setName("Dummy Product");
        dummyProduct.setDescription("Description");
        dummyProduct.setImageUrl("imageURL");
        dummyProduct.setPrice(100.0);
        dummyProduct.setCategory(category);
        return dummyProduct;
    }


    @Test
    //@Transactional ---> Not Required because @DataJpaTest automatically marks every single @Test method in your class as @Transactional
    void testFindByIdRunsSuccessFully() {
        //Arrange
        Product dummyProduct = createNewDummyProduct();
        productRepo.save(dummyProduct);

        //Act
        Optional<Product> optionalExpectedProduct = productRepo.findById(dummyProduct.getId());

        //Assert
        assertThat(optionalExpectedProduct).isPresent(); //This is same as assertEquals just newer version just like restTemplate and restClient
        assertEquals(dummyProduct.getId(),optionalExpectedProduct.get().getId());

    }

    @Test
    void testFindByAllRunsSuccessFully() {
        //Arrange
        Product dummyProduct1 = createNewDummyProduct();
        productRepo.save(dummyProduct1);
        Product dummyProduct2 = createNewDummyProduct();
        productRepo.save(dummyProduct2);

        //Act
        List<Product> dummyExpectedProductsList = productRepo.findAll();

        //Assert
        assertEquals(dummyProduct1.getId(),dummyExpectedProductsList.get(0).getId());
        assertEquals(dummyProduct2.getId(),dummyExpectedProductsList.get(1).getId());
    }

    @Test
    void testSaveRunsSuccessFully() {
        //Arrange
        Product dummyProduct = createNewDummyProduct();

        //Act
        Product savedProduct = productRepo.save(dummyProduct);

        //Assert
        Optional<Product> expectedProduct = productRepo.findById(savedProduct.getId());

        assertEquals(true,expectedProduct.isPresent());
        assertEquals(dummyProduct.getId(),expectedProduct.get().getId());
    }

    @Test
    void testFindProductByNameAndCategoryNameRunsSuccessfully(){
        //Arrange
        Product dummyproduct = createNewDummyProduct();
        productRepo.save(dummyproduct);

        //Act
        Optional<Product> optionalExpectedProduct = productRepo.findProductByNameAndCategoryName(dummyproduct.getName(), dummyproduct.getCategory().getName());

        //Assert
        assertTrue(optionalExpectedProduct.isPresent());  //It's the same as assertEquals(true,optionalExpectedProduct.isPresent());
        assertEquals(dummyproduct.getId(),optionalExpectedProduct.get().getId());
    }
}