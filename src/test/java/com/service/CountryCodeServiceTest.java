package com.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test the CountriesService methods.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CountryCodeServiceTest {

    @Autowired
    private CountryCodeService countryCodeService;

    /**
     * For a country code that is not available on the external service test "NA" is returned
     */
    @Test
    public void testInvalidCountry() {
        Assertions.assertThat(countryCodeService.getCountry("irl")).isEqualTo(CountryCodeServiceStatus.ERROR_NA.errorCode);
    }

    /**
     * For a country code that is available on the external service test the corresponding country is returned.
     */
    @Test
    public void testValidCountry() {
        Assertions.assertThat(countryCodeService.getCountry("eesti")).isEqualTo("Estonia");
    }

}
