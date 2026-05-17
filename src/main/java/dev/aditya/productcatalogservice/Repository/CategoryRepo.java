package dev.aditya.productcatalogservice.Repository;

import dev.aditya.productcatalogservice.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {
    @Override
    Optional<Category> findById(Long categoryId);

    @Query(value = "SELECT c from Category as c where c.name like :name") //"Select c" returns Category object as whole. Select proper column, or it can result in type mismatch.
    Optional<Category> findbyName(String name); //Here we are expecting category product not a column/attribute

    @Override
    Category save(Category category);
}
