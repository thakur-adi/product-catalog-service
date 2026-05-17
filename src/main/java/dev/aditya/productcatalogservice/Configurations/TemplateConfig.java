package dev.aditya.productcatalogservice.Configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TemplateConfig {


    @Bean(name = "RestTemplate")
    public RestTemplate createRestTemplate(){

        return new RestTemplate();
    }

    @Bean(name = "RestClient")
    public RestClient createRestClient(){
        return RestClient.create();
//                .builder()
//                .baseUrl("http://localhost:".concat(String.valueOf(serverProperties.getPort())))
//                .build();
        }

}
