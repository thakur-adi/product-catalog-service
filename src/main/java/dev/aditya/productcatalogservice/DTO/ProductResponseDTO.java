package dev.aditya.productcatalogservice.DTO;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import dev.aditya.productcatalogservice.Model.Category;
import dev.aditya.productcatalogservice.Model.Product;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
//This defines the order in which the data should be displayed/sent to client. So name will be sent first -> description -> ....
@JsonPropertyOrder({ "name", "description", "price" ,"category", "imageUrl", "createdAt" })
public class ProductResponseDTO {
    //These var names should match exactly like the one being sent to the client,for spring to catch and match the incoming values.
    //private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private Double price;
    private String category;
    private Date createdAt;
}
