package dev.aditya.productcatalogservice.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FallbackController.class)
class FallbackControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testHandleMissingIdRunsSuccessfully() throws Exception {
        //Arrange
        //Since there's nothing to mock here we just act and assert.
        HttpMethod[] httpMethods = {HttpMethod.GET, HttpMethod.DELETE, HttpMethod.PUT};
        //Act and Assert
        for(HttpMethod method: httpMethods)
        {
            mockMvc.perform(request(method,"/products/"))
                    .andExpect(status().is4xxClientError())
                    .andExpect(content().string("Please enter a valid Product ID and try again!!"));
        }

    }


    //@Test
    //Not Required anymore
    /*
    void testHandleBadRequestRunsSuccessfully() throws Exception {
        //Arrange
        HttpMethod[] httpMethods = {HttpMethod.GET, HttpMethod.DELETE, HttpMethod.PUT};
        //Act and Assert
        for(HttpMethod method:httpMethods) {
            mockMvc.perform(request(method,"/"))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string("Please enter a valid address and try again later!"));
        }
    }
     */



}
