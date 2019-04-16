package epam.labs.dzmitry.zorych.service.validator.impl;

import epam.labs.dzmitry.zorych.entity.RequestParam;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class RequestParamValidatorTest {
    private RequestParam validRequestParam;
    private RequestParam nonValidRequestParam;
    private RequestParamValidator requestParamValidator;

    @Before
    public void fillData() {
        validRequestParam = new RequestParam();
        validRequestParam.setCurrency(true);
        validRequestParam.setWeather(true);
        validRequestParam.setLocation(false);
        validRequestParam.setLatitude(BigDecimal.valueOf(90));
        validRequestParam.setLongitude(BigDecimal.valueOf(-180));

        nonValidRequestParam = new RequestParam();
        nonValidRequestParam.setWeather(false);
        nonValidRequestParam.setCurrency(false);
        nonValidRequestParam.setLocation(false);
        nonValidRequestParam.setLatitude(BigDecimal.valueOf(190));
        nonValidRequestParam.setLongitude(BigDecimal.valueOf(280));

        requestParamValidator = new RequestParamValidator();
    }

    @Test
    public void testValidRequestParam() {
        Assert.assertTrue(requestParamValidator.validate(validRequestParam));
    }

    @Test
    public void testNonValidRequestParam() {
        Assert.assertFalse(requestParamValidator.validate(nonValidRequestParam));
    }


}
