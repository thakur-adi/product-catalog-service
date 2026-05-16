package dev.aditya.productcatalogservice.Exception;

public class ProductIdMissingException extends RuntimeException{
   public ProductIdMissingException(String message){
        super(message);
    }
}
