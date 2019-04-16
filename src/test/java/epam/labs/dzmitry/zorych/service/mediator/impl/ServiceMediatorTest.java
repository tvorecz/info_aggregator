package epam.labs.dzmitry.zorych.service.mediator.impl;

import com.nitorcreations.junit.runners.NestedRunner;
import epam.labs.dzmitry.zorych.entity.*;
import epam.labs.dzmitry.zorych.service.commonservice.CannotGetDataException;
import epam.labs.dzmitry.zorych.service.commonservice.CommonService;
import epam.labs.dzmitry.zorych.service.mediator.InvalidParameterException;
import epam.labs.dzmitry.zorych.service.mediator.ReceivingDataIsFailedException;
import epam.labs.dzmitry.zorych.service.validator.ParamValidator;
import epam.labs.dzmitry.zorych.service.validator.impl.RequestParamValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RunWith(NestedRunner.class)
public class ServiceMediatorTest {
    private RequestParam requestParam;
    private ResponseParam expectedResponseParam;
    private ServiceMediator mediator;

    private ParamValidator validator;
    private CommonService<Location> locationService;
    private CommonService<Weather> weatherService;
    private CommonService<RateOfExchange> rateOfExchangeService;

    private Location actualLocation;
    private Location expectedLocation;

    private Weather actualWeather;
    private Weather expectedWeather;

    private RateOfExchange actualRateOfExchange;
    private RateOfExchange expectedRateOfExchange;

    @Before
    public void setUp() {
        validator = new RequestParamValidator();

        locationService = Mockito.mock(CommonService.class);
        weatherService = Mockito.mock(CommonService.class);
        rateOfExchangeService = Mockito.mock(CommonService.class);

        mediator = new ServiceMediator(validator, locationService, weatherService, rateOfExchangeService);

        requestParam = new RequestParam();
    }

    public class GetResponceParamFromServiceMediator {

        @Before
        public void setUp() {
            actualLocation = new Location();
            actualLocation.setDate(LocalDate.of(2019, 12, 01));
            actualLocation.setLatitude(BigDecimal.valueOf(50.1));
            actualLocation.setLongitude(BigDecimal.valueOf(50.1));
            actualLocation.setTimeZone("Minsk/Belarus");
            actualLocation.setCurrencyCode(Currency.BYN);
            actualLocation.setContinent("Europe");
            actualLocation.setCountry("Belarus");
            actualLocation.setCity("Minsk");

            expectedLocation = new Location();
            expectedLocation.setDate(LocalDate.of(2019, 12, 01));
            expectedLocation.setLatitude(BigDecimal.valueOf(50.1));
            expectedLocation.setLongitude(BigDecimal.valueOf(50.1));
            expectedLocation.setTimeZone("Minsk/Belarus");
            expectedLocation.setCurrencyCode(Currency.BYN);
            expectedLocation.setContinent("Europe");
            expectedLocation.setCountry("Belarus");
            expectedLocation.setCity("Minsk");
        }

        public class WhenRequestParamCorrect {
            @Before
            public void setUp() {
                expectedResponseParam = new ResponseParam();

                requestParam.setLatitude(BigDecimal.valueOf(50.1));
                requestParam.setLongitude(BigDecimal.valueOf(50.1));
            }

            public class WhenNeedOnlyWeather {
                @Before
                public void setUp() throws CannotGetDataException {
                    actualWeather = new Weather();
                    actualWeather.setLocation(actualLocation);
                    actualWeather.setSummary("sunny");
                    actualWeather.setIcon("sun");
                    actualWeather.setTemperature(20);
                    actualWeather.setApparentTemperature(21);
                    actualWeather.setPressure(123);
                    actualWeather.setWindSpeed(5);
                    actualWeather.setWindGust(5);

                    expectedWeather = new Weather();
                    expectedWeather.setLocation(expectedLocation);
                    expectedWeather.setSummary("sunny");
                    expectedWeather.setIcon("sun");
                    expectedWeather.setTemperature(20);
                    expectedWeather.setApparentTemperature(21);
                    expectedWeather.setPressure(123);
                    expectedWeather.setWindSpeed(5);
                    expectedWeather.setWindGust(5);

                    expectedResponseParam.setLocation(expectedLocation);
                    expectedResponseParam.setWeather(expectedWeather);
                    expectedResponseParam.setIsWeather(true);

                    requestParam.setWeather(true);


                    Mockito.when(locationService.get(new Location(BigDecimal.valueOf(50.1), BigDecimal.valueOf(50.1)))).thenReturn(actualLocation);

                    Mockito.when(weatherService.get(actualLocation)).thenReturn(actualWeather);
                }

                @Test
                public void shouldReturnResponceWithWeather() throws
                                                              ReceivingDataIsFailedException,
                                                              InvalidParameterException {
                    ResponseParam actualResponseParam = mediator.get(requestParam);

                    Assert.assertEquals(actualResponseParam, expectedResponseParam);
                }

                public class WhenNeedCurrency {
                    @Before
                    public void setUp() throws CannotGetDataException {
                        actualRateOfExchange = new RateOfExchange();
                        actualRateOfExchange.setLocation(actualLocation);
                        Map<Currency, BigDecimal> actualCurrencyMap = new HashMap<>();
                        actualCurrencyMap.put(Currency.BYN, BigDecimal.valueOf(2.1));
                        actualRateOfExchange.setCurrencies(actualCurrencyMap);

                        expectedRateOfExchange = new RateOfExchange();
                        expectedRateOfExchange.setLocation(expectedLocation);
                        Map<Currency, BigDecimal> expectedCurrencyMap = new HashMap<>();
                        expectedCurrencyMap.put(Currency.BYN, BigDecimal.valueOf(2.1));
                        expectedRateOfExchange.setCurrencies(expectedCurrencyMap);

                        expectedResponseParam.setRateOfExchange(expectedRateOfExchange);
                        expectedResponseParam.setIsCurrency(true);

                        requestParam.setCurrency(true);

                        Mockito.when(rateOfExchangeService.get(actualLocation)).thenReturn(actualRateOfExchange);
                    }

                    @Test
                    public void shouldReturnResponceWithCurrencyAndWeather() throws
                                                                             ReceivingDataIsFailedException,
                                                                             InvalidParameterException {
                        ResponseParam actualResponseParam = mediator.get(requestParam);

                        Assert.assertEquals(actualResponseParam, expectedResponseParam);
                    }

                    public class WhenNeedOnlyCurrency{
                        @Before
                        public void setUp() {
                            expectedResponseParam.setIsWeather(false);
                            expectedResponseParam.setWeather(null);
                            requestParam.setWeather(false);
                        }

                        @Test
                        public void shouldReturnResponceWithCurrency() throws
                                                                                 ReceivingDataIsFailedException,
                                                                                 InvalidParameterException {
                            ResponseParam actualResponseParam = mediator.get(requestParam);

                            Assert.assertEquals(actualResponseParam, expectedResponseParam);
                        }
                    }

                    public class WhenNeedAll {
                        @Before
                        public void setUp() {
                            expectedResponseParam.setIsLocation(true);

                            requestParam.setLocation(true);
                        }

                        @Test
                        public void shouldReturnResponceWithAll() throws
                                                                       ReceivingDataIsFailedException,
                                                                       InvalidParameterException {
                            ResponseParam actualResponseParam = mediator.get(requestParam);

                            Assert.assertEquals(actualResponseParam, expectedResponseParam);
                        }

                        public class WhenDataSourceReturnException {
                            @Before
                            public void setUp() throws CannotGetDataException {
                                Mockito.when(locationService.get(new Location(BigDecimal.valueOf(50.1), BigDecimal.valueOf(50.1)))).thenThrow(CannotGetDataException.class);

                                Mockito.when(weatherService.get(actualLocation)).thenThrow(CannotGetDataException.class);

                                Mockito.when(rateOfExchangeService.get(actualLocation)).thenThrow(CannotGetDataException.class);
                            }

                            @Test(expected = ReceivingDataIsFailedException.class)
                            public void shouldReturnInvalidParameterException() throws
                                                                                ReceivingDataIsFailedException,
                                                                                InvalidParameterException {
                                mediator.get(requestParam);
                            }
                        }
                    }
                }
            }
        }

        public class WhenRequestParamNonCorrect {
            @Before
            public void setUp() {
                expectedResponseParam = new ResponseParam();

                requestParam.setLatitude(BigDecimal.valueOf(190));
                requestParam.setLongitude(BigDecimal.valueOf(-200));
            }

            @Test(expected = InvalidParameterException.class)
            public void shouldReturnInvalidParameterException() throws
                                                                ReceivingDataIsFailedException,
                                                                InvalidParameterException {
                mediator.get(requestParam);
            }
        }
    }
}
