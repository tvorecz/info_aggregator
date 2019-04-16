package epam.labs.dzmitry.zorych.service.mediator.impl;

import epam.labs.dzmitry.zorych.entity.*;
import epam.labs.dzmitry.zorych.service.commonservice.CannotGetDataException;
import epam.labs.dzmitry.zorych.service.commonservice.CommonService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ResponseFillerByWeatherTest {
    private CommonService<Weather> weatherService;
    private Location locationForMock;
    private ResponseParam actualResponseParam;
    private ResponseParam expectedResponseParam;
    private ResponseParam expectedResponseParamNonFilled;
    private RequestParam requestParam;
    private RequestParam requestParamNonFilled;
    private Weather actualWeather;
    private Weather expectedWeather;

    @Before
    public void fillData() throws CannotGetDataException {
        weatherService = Mockito.mock(CommonService.class);

        locationForMock = new Location();
        locationForMock.setDate(LocalDate.of(1990, 01, 15));
        locationForMock.setLatitude(BigDecimal.valueOf(90));
        locationForMock.setLongitude(BigDecimal.valueOf(90));
        locationForMock.setTimeZone("Minsk/Belarus");
        locationForMock.setCurrencyCode(Currency.BYN);
        locationForMock.setContinent("Europe");
        locationForMock.setCountry("Belarus");
        locationForMock.setCity("Minsk");

        actualWeather = new Weather();
        actualWeather.setSummary("Warm");
        actualWeather.setIcon("sun");
        actualWeather.setTemperature(23);
        actualWeather.setApparentTemperature(23);
        actualWeather.setPressure(232);
        actualWeather.setWindSpeed(23);
        actualWeather.setWindGust(23);

        expectedWeather = new Weather();
        expectedWeather.setSummary("Warm");
        expectedWeather.setIcon("sun");
        expectedWeather.setTemperature(23);
        expectedWeather.setApparentTemperature(23);
        expectedWeather.setPressure(232);
        expectedWeather.setWindSpeed(23);
        expectedWeather.setWindGust(23);
        expectedWeather.setLocation(locationForMock);

        requestParam = new RequestParam();
        requestParam.setWeather(true);

        requestParamNonFilled = new RequestParam();
        requestParamNonFilled.setWeather(false);

        actualResponseParam = new ResponseParam();

        expectedResponseParam = new ResponseParam();
        expectedResponseParam.setIsWeather(true);
        expectedResponseParam.setWeather(expectedWeather);

        expectedResponseParamNonFilled = new ResponseParam();

        Mockito.when(weatherService.get(locationForMock)).thenReturn(actualWeather);
    }

    @Test
    public void testFillerFilled() throws CannotGetDataException {
        ResponseFillerByWeather responseFillerByWeather = new ResponseFillerByWeather(weatherService);

        responseFillerByWeather.fill(locationForMock, actualResponseParam, requestParam);

        Assert.assertEquals(actualResponseParam, expectedResponseParam);
    }

    @Test
    public void testFillerNonFilled() throws CannotGetDataException {
        ResponseFillerByWeather responseFillerByWeather = new ResponseFillerByWeather(weatherService);

        responseFillerByWeather.fill(locationForMock, actualResponseParam, requestParamNonFilled);

        Assert.assertEquals(actualResponseParam, expectedResponseParamNonFilled);
    }
}
