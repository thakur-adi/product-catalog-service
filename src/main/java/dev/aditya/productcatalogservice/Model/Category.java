package dev.aditya.productcatalogservice.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@Entity
public class Category extends Base{

    @OneToMany(mappedBy = "category")
    @JsonIgnore //Tells Spring to exclude this from Json result and display/show only the remaining attributes.
    //Another way would be to change the fetch type to lazy.
    //If we don't then it can go into an infinite loop -> display product -> for that product display category -> display list<Product> for that category -> for each of those product display category -> so on so forth.
    private List<Product> productList;




/*
   // Once we make it an entity we have to define a default Constructor if a parameterized Constructor exists
    public Category() {}


   // This Constructor is added so that we can directly call Product.setCategory(new Category(name)) -> Check FakeStoreDTO

    public Category(String name) {
        super();
        this.setName(name);
    }

*/
}
