package dev.aditya.productcatalogservice.Controller;

import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//This class handles the case of a wrong Address i/p. e.g."http://localhost:8080/" (w/o defining any endpoint)
// Throws a bad request error with a personal message defined in Global handler.
@RestController
public class RootController{
    @RequestMapping(value = "/", method = {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.PUT, RequestMethod.POST,RequestMethod.PATCH})
    public ResponseEntity<String> handleBadRequest() throws BadRequestException {
        throw new BadRequestException();
    }
}