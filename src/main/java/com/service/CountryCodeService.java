package com.service;

import com.controller.EmployeeController;
import com.exception.CountryCodeServiceException;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Accesses downstream service which maps country name to a country code
 */
@Service
public class CountryCodeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private final Map<String, String> countryCodeMapping = new HashMap<String, String>();

    @Autowired
    private RestTemplate restTemplate;
    @Value("${countries.rest.url}")
    private String countriesRestUrl;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    /**
     * Store the country mapping in a Map if not already added.
     *
     * @param countryCode
     * @return
     */
    public String getCountry(final String countryCode) {

        if (countryCodeMapping.containsKey(countryCode)) {
            return countryCodeMapping.get(countryCode);
        } else {
            try {

                final String response = restTemplate.getForObject(this.countriesRestUrl + "{countryCode}", String.class, countryCode);
                final String jsonPath = "$.[*].name";
                final DocumentContext jsonContext = JsonPath.parse(response);
                final List<String> result = jsonContext.read(jsonPath);

                if (result.size() == 0) {
                    return CountryCodeServiceStatus.ERROR_NA.errorCode;
                }
                final String countryName = result.get(0);
                countryCodeMapping.put(countryCode, countryName);
                return countryName;

            } catch (HttpClientErrorException clientErrorException) {
                if (clientErrorException.getStatusCode() == HttpStatus.NOT_FOUND) {
                    logger.info("Country name for code :{} not available", countryCode, clientErrorException.getMessage());
                    return CountryCodeServiceStatus.ERROR_NA.errorCode;
                } else {
                    throw new CountryCodeServiceException(clientErrorException);
                }

            }
        }
    }
}


