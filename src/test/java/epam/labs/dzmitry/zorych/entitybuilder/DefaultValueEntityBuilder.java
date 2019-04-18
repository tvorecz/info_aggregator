package epam.labs.dzmitry.zorych.entitybuilder;

import epam.labs.dzmitry.zorych.entity.*;

import java.math.BigDecimal;

public class DefaultValueEntityBuilder {

    public static Location defaultLocation() {
        return new LocationBuilder().build();
    }

    public static Weather defaultWeather() {
        return new WeatherBuilder().build();
    }

    public static RateOfExchange defaultRateOfExchange() {
        return new RateOfExchangeBuilder().build();
    }

    public static RequestParam defaultRequestParam() {
        return RequestParam.builder()
                .currency(true)
                .weather(true)
                .location(true)
                .latitude(BigDecimal.valueOf(50.0))
                .longitude(
                        BigDecimal.valueOf(50.0))
                .build();
    }

    public static ResponseParam defaultResponseParam() {
        return ResponseParam.builder()
                .isCurrency(true)
                .isLocation(true)
                .isWeather(true)
                .location(defaultLocation())
                .rateOfExchange(defaultRateOfExchange())
                .weather(defaultWeather())
                .build();
    }
}
