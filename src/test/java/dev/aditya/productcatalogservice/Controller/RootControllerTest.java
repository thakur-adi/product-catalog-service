package dev.aditya.productcatalogservice.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RootController.class)
class RootControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
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
}
