package dev.aditya.productcatalogservice.Controller;

import dev.aditya.productcatalogservice.Exception.ProductIdMissingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


//This whole class is not required at all. All this can be handled directly from ControllerAdvisor(middleman).
// This is just another way of doing it. Not used IRL

@RestController
public class FallbackController {

    //To handle if Client tries to bypass without inputting any id. Spring catches it on its own and throws an error
    // Spring never lets it reach service layer. But this way we can give out a particular response instead of an Error
    //@DeleteMapping("/") could have been used as wel but would just take care of Delete Request
    @RequestMapping(value = "/products/",method = {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.PUT})
    public ResponseEntity<String> handleMissingId() {
        throw new ProductIdMissingException("Please enter a valid Product ID and try again!!");
    }


    /*
    This is not required as it gets handled directly in controller Advisor i.e. global Exception Class

    //This handles the case of a wrong Address i/p. e.g."http://localhost:8080/" (w/o defining any endpoint)
    // Throws an error with a personal message(defined in Global handler).
    @RequestMapping(value = "/", method = {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.PUT, RequestMethod.POST,RequestMethod.PATCH})
    public ResponseEntity<String> handleBadRequest() throws BadRequestException {
        throw new BadRequestException();
    }
    */


}