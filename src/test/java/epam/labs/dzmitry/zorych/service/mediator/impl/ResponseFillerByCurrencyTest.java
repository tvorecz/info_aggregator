package epam.labs.dzmitry.zorych.service.mediator.impl;

import epam.labs.dzmitry.zorych.entity.*;
import epam.labs.dzmitry.zorych.service.commonservice.CannotGetDataException;
import epam.labs.dzmitry.zorych.service.commonservice.CommonService;
import epam.labs.dzmitry.zorych.service.mediator.ReceivingDataIsFailedException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ResponseFillerByCurrencyTest {
    private CommonService<RateOfExchange> currencyService;
    private Location locationForMock;
    private ResponseParam actualResponseParam;
    private ResponseParam expectedResponseParam;
    private ResponseParam expectedResponseParamNonFilled;
    private RequestParam requestParam;
    private RequestParam requestParamNonFilled;
    private RateOfExchange actualRateOfExchange;
    private RateOfExchange expectedRateOfExchange;

    @Before
    public void fillData() throws CannotGetDataException {
        currencyService = Mockito.mock(CommonService.class);

        locationForMock = new Location();
        locationForMock.setDate(LocalDate.of(1990, 01, 15));
        locationForMock.setLatitude(BigDecimal.valueOf(90));
        locationForMock.setLongitude(BigDecimal.valueOf(90));
        locationForMock.setTimeZone("Minsk/Belarus");
        locationForMock.setCurrencyCode(Currency.BYN);
        locationForMock.setContinent("Europe");
        locationForMock.setCountry("Belarus");
        locationForMock.setCity("Minsk");

        actualRateOfExchange = new RateOfExchange();
        Map<Currency, BigDecimal> currencies = new HashMap<>();
        currencies.put(Currency.EUR, BigDecimal.valueOf(50.50));
        actualRateOfExchange.setCurrencies(currencies);

        expectedRateOfExchange = new RateOfExchange();
        expectedRateOfExchange.setLocation(locationForMock);
        Map<Currency, BigDecimal> expectedCurrencies = new HashMap<>();
        expectedCurrencies.put(Currency.EUR, BigDecimal.valueOf(50.50));
        expectedRateOfExchange.setCurrencies(expectedCurrencies);


        requestParam = new RequestParam();
        requestParam.setCurrency(true);

        requestParamNonFilled = new RequestParam();
        requestParamNonFilled.setCurrency(false);

        actualResponseParam = new ResponseParam();

        expectedResponseParam = new ResponseParam();
        expectedResponseParam.setIsCurrency(true);
        expectedResponseParam.setRateOfExchange(expectedRateOfExchange);

        expectedResponseParamNonFilled = new ResponseParam();

        Mockito.when(currencyService.get(locationForMock)).thenReturn(actualRateOfExchange);
    }

    @Test
    public void testFillerFilled() throws CannotGetDataException, ReceivingDataIsFailedException {
        ResponseFillerByCurrency responseFillerByWeather = new ResponseFillerByCurrency(currencyService);

        responseFillerByWeather.fill(locationForMock, actualResponseParam, requestParam);

        Assert.assertEquals(actualResponseParam, expectedResponseParam);
    }

    @Test
    public void testFillerNonFilled() throws CannotGetDataException, ReceivingDataIsFailedException {
        ResponseFillerByCurrency responseFillerByWeather = new ResponseFillerByCurrency(currencyService);

        responseFillerByWeather.fill(locationForMock, actualResponseParam, requestParamNonFilled);

        Assert.assertEquals(actualResponseParam, expectedResponseParamNonFilled);
    }
}
