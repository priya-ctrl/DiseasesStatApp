package com.upgrad.patterns.Strategies;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upgrad.patterns.Config.RestServiceGenerator;
import com.upgrad.patterns.Entity.JohnHopkinResponse;
import com.upgrad.patterns.Interfaces.IndianDiseaseStat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;

@Service
public class JohnHopkinsStrategy implements IndianDiseaseStat {

	private Logger logger = LoggerFactory.getLogger(JohnHopkinsStrategy.class);
	private RestTemplate restTemplate;

	@Value("${config.john-hopkins-url}")
	private String baseUrl;

	public JohnHopkinsStrategy() {
		restTemplate = RestServiceGenerator.GetInstance();
	}

	@Override
	public String GetActiveCount() {
		try {
			// Get the response from getJohnHopkinResponses method
			JohnHopkinResponse[] responses = getJohnHopkinResponses();

			// Filter for country "India" and sum the confirmed cases
			double confirmedCases = Arrays.stream(responses)
					.filter(response -> "India".equalsIgnoreCase(response.getCountry()))
					.mapToDouble(response -> response.getStats().getConfirmed())
					.sum();

			// Return the rounded response (0 decimal places)
			return String.format("%.0f", confirmedCases);

		} catch (IOException e) {
			// Log the error
			logger.error("Error fetching data from John Hopkins", e);

			// Return null in case of error
			return null;
		}
	}

	private JohnHopkinResponse[] getJohnHopkinResponses() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		ClassPathResource resource = new ClassPathResource("JohnHopkins.json");
		return objectMapper.readValue(resource.getFile(), JohnHopkinResponse[].class);
	}
}
