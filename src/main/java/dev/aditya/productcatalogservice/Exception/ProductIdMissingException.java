package dev.aditya.productcatalogservice.Exception;

public class ProductIdMissingException extends RuntimeException{
    //This has to be static cause the parent hasn't been created before super(), so we wouldn't be able to access any instance variable before the parent is constructed.
    // To avoid that we make it Static (created during app start by JVM)
    static String message ="Please enter an Id";

    //for default message
    public ProductIdMissingException()
    {
        super(message);
    }

    //for some personalized message
    public ProductIdMissingException(String message){
        super(message);
    }
}
