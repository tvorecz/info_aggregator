package epam.labs.dzmitry.zorych.entitybuilder.impl;

import epam.labs.dzmitry.zorych.entity.Currency;
import epam.labs.dzmitry.zorych.entity.RateOfExchange;
import epam.labs.dzmitry.zorych.entitybuilder.DataSourceException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class RateOfExchangeBuilderTest {
    private final static String JSON = "{\"BYN_USD\":0.467563,\"BYN_EUR\":0.416257,\"BYN_GBP\":0.358616,\"BYN_CNY\":3" +
                                       ".141042}";
    private final static String JSON_ERROR = "{\"status\":400,\"error\":\"Invalid query format.\"}";

    private static RateOfExchange exceptedRateOfExchange;

    @BeforeClass
    public static void fillData() {
        exceptedRateOfExchange = new RateOfExchange();
        exceptedRateOfExchange.setBaseIsoCode(Currency.BYN);

        Map<Currency, BigDecimal> currencies = new HashMap<>();

        currencies.put(Currency.USD, BigDecimal.valueOf(0.467563));
        currencies.put(Currency.EUR, BigDecimal.valueOf(0.416257));
        currencies.put(Currency.GBP, BigDecimal.valueOf(0.358616));
        currencies.put(Currency.CNY, BigDecimal.valueOf(3.141042));

        exceptedRateOfExchange.setCurrencies(currencies);
    }

    @Test
    public void testRateOfExchangeBuilder() throws DataSourceException {
        RateOfExchangeBuilder rateOfExchangeBuilder = new RateOfExchangeBuilder();
        RateOfExchange actualRateOfExchange = rateOfExchangeBuilder.build(JSON);

        Assert.assertEquals(actualRateOfExchange.getBaseIsoCode(), exceptedRateOfExchange.getBaseIsoCode());
        Assert.assertEquals(actualRateOfExchange.getCurrencies(), exceptedRateOfExchange.getCurrencies());
    }

    @Test(expected = DataSourceException.class)
    public void testRateOfExchangeBuilderException() throws DataSourceException {
        RateOfExchangeBuilder rateOfExchangeBuilder = new RateOfExchangeBuilder();
        rateOfExchangeBuilder.build(JSON_ERROR);
    }
}
