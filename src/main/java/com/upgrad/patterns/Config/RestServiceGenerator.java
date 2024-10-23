package com.upgrad.patterns.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

public class RestServiceGenerator {

    private static Logger logger = LoggerFactory.getLogger(RestServiceGenerator.class);

    // Private static RestTemplate instance (Singleton)
    private static RestTemplate restTemplate;

    // Private constructor to prevent instantiation
    private RestServiceGenerator() {
    }

    // Static method to get the single instance of RestTemplate
    public static RestTemplate GetInstance() {
        // Check if restTemplate is already initialized
        if (restTemplate == null) {
            synchronized (RestServiceGenerator.class) {
                if (restTemplate == null) {
                    // Initialize restTemplate. This is executed only once.
                    restTemplate = new RestTemplateBuilder()
                            .interceptors((request, body, execution) -> {
                                logger.info(String.format("Calling %s %s", request.getMethod(), request.getURI()));
                                ClientHttpResponse clientHttpResponse = execution.execute(request, body);
                                logger.info(String.format("Call completed %s %s responded with %s", request.getMethod(), request.getURI(), clientHttpResponse.getStatusCode()));
                                return clientHttpResponse;
                            })
                            .build();
                }
            }
        }

        // Return the restTemplate object
        return restTemplate;
    }
}
