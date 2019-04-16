package epam.labs.dzmitry.zorych.dal.entitycreator.impl;

import epam.labs.dzmitry.zorych.dal.entitycreator.DataSourceException;
import epam.labs.dzmitry.zorych.entity.Currency;
import epam.labs.dzmitry.zorych.entity.RateOfExchange;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class RateOfExchangeBuilderFromCurrencyconverterapiTest {
    private final static String JSON = "{\"BYN_USD\":0.467563,\"BYN_EUR\":0.416257,\"BYN_GBP\":0.358616,\"BYN_CNY\":3" +
                                       ".141042,\"BYN_RUB\":3.121111}";
    private final static String JSON_ERROR = "{\"status\":400,\"error\":\"Invalid query format.\"}";

    private RateOfExchange exceptedRateOfExchange;

    @Before
    public void fillData() {
        exceptedRateOfExchange = new RateOfExchange();

        Map<Currency, BigDecimal> currencies = new HashMap<>();

        currencies.put(Currency.USD, BigDecimal.valueOf(0.467563));
        currencies.put(Currency.EUR, BigDecimal.valueOf(0.416257));
        currencies.put(Currency.GBP, BigDecimal.valueOf(0.358616));
        currencies.put(Currency.CNY, BigDecimal.valueOf(3.141042));
        currencies.put(Currency.RUB, BigDecimal.valueOf(3.121111));

        exceptedRateOfExchange.setCurrencies(currencies);
    }

    @Test
    public void testRateOfExchangeBuilder() throws DataSourceException {
        RateOfExchangeCreatorFromCurrencyconverterapi rateOfExchangeBuilderFromCurrencyconverterapi = new RateOfExchangeCreatorFromCurrencyconverterapi();
        RateOfExchange actualRateOfExchange = rateOfExchangeBuilderFromCurrencyconverterapi.create(JSON);

        Assert.assertEquals(actualRateOfExchange.getCurrencies(), exceptedRateOfExchange.getCurrencies());
    }

    @Test(expected = DataSourceException.class)
    public void testRateOfExchangeBuilderException() throws DataSourceException {
        RateOfExchangeCreatorFromCurrencyconverterapi rateOfExchangeBuilderFromCurrencyconverterapi = new RateOfExchangeCreatorFromCurrencyconverterapi();
        rateOfExchangeBuilderFromCurrencyconverterapi.create(JSON_ERROR);
    }
}
