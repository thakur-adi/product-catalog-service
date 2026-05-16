package dev.aditya.productcatalogservice.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/*
This is an Advisor Class. As the name suggests this handles all the exceptions defined in the project at  one place.
Helps us by eliminating unnecessary try{}catch{} code everywhere, Spring handles all that on its own
* */

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException e)
    {
        return new ResponseEntity<>("Null Pointer Encountered",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InternalError.class)
    public ResponseEntity<String> handleInternalError(InternalError e)
    {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductIdMissingException.class)
    public ResponseEntity<String> handleProductIdMissingException(ProductIdMissingException e)
    {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

}
