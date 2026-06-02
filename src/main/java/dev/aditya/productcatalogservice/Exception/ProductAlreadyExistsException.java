package dev.aditya.productcatalogservice.Exception;

public class ProductAlreadyExistsException extends RuntimeException {

    private static final String message ="Product already Exists";

    public ProductAlreadyExistsException(){super(message);}

    public ProductAlreadyExistsException(String message){
        super(message);
    }
}
