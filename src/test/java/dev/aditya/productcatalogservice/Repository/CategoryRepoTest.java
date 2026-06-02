package dev.aditya.productcatalogservice.Repository;

import dev.aditya.productcatalogservice.Model.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//This is an Integration test not a Unit Test. We never run Unit Tests on Repository
@DataJpaTest //automatically marks every single @Test method in your class as @Transactional. It doesn't strictly need H2 db, but is preferred
class CategoryRepoTest {

    @Autowired
    CategoryRepo categoryRepo;

    private Category createNewCategory(){
        Category category =  new Category();
        category.setName("Category name");
        return  category;
    }

    @Test
    void testFindByIdRunsSuccessfully(){
        //Arrange
        Category dummyCategory = createNewCategory();
        categoryRepo.save(dummyCategory);

        //Act
        Optional<Category> optionalExpectedCategory = categoryRepo.findById(dummyCategory.getId());

        //Assert
        assertTrue(optionalExpectedCategory.isPresent());
        assertEquals(dummyCategory.getId(),optionalExpectedCategory.get().getId());

    }

    @Test
    void testFindByNameRunsSuccessfully(){
        //Arrange
        Category dummyCategory = createNewCategory();
        categoryRepo.save(dummyCategory);

        //Act
        Optional<Category> optionalExpectedCategory = categoryRepo.findbyName(dummyCategory.getName());

        //Assert
        assertTrue(optionalExpectedCategory.isPresent());
        assertEquals(dummyCategory.getName(),optionalExpectedCategory.get().getName());
    }

    @Test
    void testSaveRunsSuccessfully(){
        //Arrange
        Category dummyCategory = createNewCategory();

        //Act
        Category savedCategory = categoryRepo.save(dummyCategory);

        //Assert
        Optional<Category> expectedSavedCategory = categoryRepo.findById(dummyCategory.getId());

        assertTrue(expectedSavedCategory.isPresent());
        assertEquals(dummyCategory.getId(),expectedSavedCategory.get().getId());
        assertEquals(savedCategory.getId(),expectedSavedCategory.get().getId());
    }

}