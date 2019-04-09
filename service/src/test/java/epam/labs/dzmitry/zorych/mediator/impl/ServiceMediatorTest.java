package epam.labs.dzmitry.zorych.mediator.impl;

import epam.labs.dzmitry.zorych.entity.*;
import epam.labs.dzmitry.zorych.service.CommonService;
import org.junit.BeforeClass;

import java.math.BigDecimal;

public class ServiceMediatorTest {
    private static RequestParam requestParam;
    private static ResponseParam responseParam;
    private static ServiceMediator mediator;
    private static CommonService<Location> locationService;
    private static CommonService<Weather> weatherService;
    private static CommonService<RateOfExchange> rateOfExchangeService;


    @BeforeClass
    public static void fillData() {
        requestParam = new RequestParam();
        requestParam.setWeather(true);
        requestParam.setCurrency(true);
        requestParam.setLocation(true);
        requestParam.setLatitude(BigDecimal.valueOf(50.000));
        requestParam.setLongitude(BigDecimal.valueOf(50.000));

        responseParam = new ResponseParam();
//        responseParam.setIsWeather();
//        responseParam.setIsCurrency();
//        responseParam.setIsLocation();
//        responseParam.setWeather();
//        responseParam.setRateOfExchange();
//        responseParam.setLocation();



    }
}
