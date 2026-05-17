package dev.aditya.productcatalogservice.Service;

import dev.aditya.productcatalogservice.Exception.ProductNotFoundException;
import dev.aditya.productcatalogservice.Model.Category;
import dev.aditya.productcatalogservice.Model.ModelStatus;
import dev.aditya.productcatalogservice.Model.Product;
import dev.aditya.productcatalogservice.Repository.CategoryRepo;
import dev.aditya.productcatalogservice.Repository.ProductRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service("StorageProductService")
public class StorageProductService implements ProductServices {

    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;

    StorageProductService(ProductRepo productRepo,CategoryRepo categoryRepo)
    {
        this.productRepo=productRepo;
        this.categoryRepo=categoryRepo;
    }


    @Override
    public Product getProductById(long prodId) throws ProductNotFoundException {
        Optional<Product> optionalProduct =productRepo.findById(prodId);
        if(optionalProduct.isEmpty())
        {
            throw new ProductNotFoundException();
        }
        else {
        return optionalProduct.get();}
    }


    @Override
    public List<Product> getAllProducts() throws ProductNotFoundException {
        List<Product> products = productRepo.findAll();
        if(products.size()==0)
        {
            throw new ProductNotFoundException("No Products exist yet.");
        }
        return List.copyOf(products); //for security purpose
    }


    @Override
    public Product createNewProduct(String productName, String desc, String imageUrl, double price, String categoryName) {

        Product product = createNewProductFromParams(productName, desc, imageUrl, price, categoryName);

        if(productRepo.findById(product.getId()).isPresent())
        {
            throw new RuntimeException("Product already exists");
        }
        return productRepo.save(product);
    }

    @Override
    public Boolean deleteProductById(long prodId) throws ProductNotFoundException {
       Optional<Product> optionalProduct = productRepo.findById(prodId);
        if(optionalProduct.isEmpty())
        {
            throw new ProductNotFoundException();
        }
        else if (optionalProduct.get().getStatus().equals(ModelStatus.DELETED)) {
            throw new ProductNotFoundException();
        }
        optionalProduct.get().setStatus(ModelStatus.DELETED);
        return true;
    }



    //Helper Method for creating a Product Model Object
    private Product createNewProductFromParams(String productName, String desc, String imageURL, double price, String categoryName)
    {
        Product product = new Product();
        product.setName(productName);
        product.setDescription(desc);
        product.setImageUrl(imageURL);
        product.setPrice(price);
        //It's a nested Property i.e. product has a separate 'category' class attribute that means a product cannot be added if the category doesn't exist first.
        // It'll always fail as it demands the category to be present. That's why we first update category table then add the product.
        Optional<Category> optionalCategory = categoryRepo.findbyName(categoryName);
        if(optionalCategory.isEmpty())
        {
           Category category = new Category();
           category.setName(categoryName);
           categoryRepo.save(category);
           product.setCategory(category);
        }
        else {
            product.setCategory(optionalCategory.get());
        }
        //I fwe don't perform this product addition to the  table i.e. post/put api will fail.
        return product;
    }
}
