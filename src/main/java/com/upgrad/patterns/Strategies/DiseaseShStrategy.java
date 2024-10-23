package com.upgrad.patterns.Strategies;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upgrad.patterns.Config.RestServiceGenerator;
import com.upgrad.patterns.Entity.DiseaseShResponse;
import com.upgrad.patterns.Interfaces.IndianDiseaseStat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class DiseaseShStrategy implements IndianDiseaseStat {

    private static final Logger logger = LoggerFactory.getLogger(DiseaseShStrategy.class);

    private final RestTemplate restTemplate;

    @Value("${config.diseaseSh-io-url}")
    private String baseUrl;

    @Autowired
    public DiseaseShStrategy(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getActiveCount() {
        try {
            DiseaseShResponse response = getDiseaseShResponseResponses();
            double cases = response.getCases();
            return String.format("%.0f", cases);
        } catch (IOException e) {
            logger.error("Error fetching data from Disease.io", e);
            // Optionally throw a custom exception here
            return null; // Consider using Optional<String>
        }
    }

    private DiseaseShResponse getDiseaseShResponseResponses() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassPathResource resource = new ClassPathResource("DiseaseSh.json");
        return objectMapper.readValue(resource.getFile(), DiseaseShResponse.class);
    }

    @Override
    public Object getInfectedRatio() {
        // Implementation for infected ratio
        return null; // Placeholder
    }

    @Override
    public String GetActiveCount() {
        return "";
    }
}
