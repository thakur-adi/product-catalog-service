package dev.aditya.productcatalogservice.Controller;

import dev.aditya.productcatalogservice.Exception.ProductIdMissingException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//This class handles the case of a wrong Address i/p. e.g."http://localhost:8080/" (w/o defining any endpoint)
// Throws a bad request error with a personal message defined in Global handler.
@RestController
public class FallbackController {
    @RequestMapping(value = "/", method = {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.PUT, RequestMethod.POST,RequestMethod.PATCH})
    public ResponseEntity<String> handleBadRequest() throws BadRequestException {
        throw new BadRequestException();
    }


    //To handle if Client tries to bypass without inputting any id. Spring catches it on its own and throws an error
    // Spring never lets it reach service layer. But this way we can give out a particular response instead of an Error
    //@DeleteMapping("/") could have been used as wel but would just take care of Delete Request
    @RequestMapping(value = "/products/",method = {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.PUT})
    public ResponseEntity<String> handleMissingId() {
        throw new ProductIdMissingException("Please enter a valid Product Id");
    }
}