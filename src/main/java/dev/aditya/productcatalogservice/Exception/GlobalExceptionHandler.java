package dev.aditya.productcatalogservice.Exception;

import org.apache.coyote.BadRequestException;
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

    //Pre-Defined Exceptions

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException e){
        return new ResponseEntity<>("Null encountered. Please try again later!",HttpStatus.NOT_IMPLEMENTED);
    }

    @ExceptionHandler(InternalError.class)
    public ResponseEntity<String> handleInternalError(InternalError e){
        return new ResponseEntity<>("Internal server issue encountered. Please try again later!",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException e){
        return new ResponseEntity<>("Please enter a valid address and try again later!",HttpStatus.BAD_REQUEST);
    }


    //Custom Exceptions

    @ExceptionHandler(ProductIdMissingException.class)
    public ResponseEntity<String> handleProductIdMissingException(ProductIdMissingException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
