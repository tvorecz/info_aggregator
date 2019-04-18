package epam.labs.dzmitry.zorych.entitybuilder;

import epam.labs.dzmitry.zorych.entity.Currency;
import epam.labs.dzmitry.zorych.entity.Location;
import epam.labs.dzmitry.zorych.entity.RateOfExchange;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class RateOfExchangeBuilder {
    private Location location = new LocationBuilder().build();
    private Map<Currency, BigDecimal> currencies = new HashMap<>();

    public RateOfExchangeBuilder() {
        currencies.put(Currency.BYN, BigDecimal.valueOf(2.1));
    }

    public RateOfExchangeBuilder withLocation(Location value) {
        location = value;

        return this;
    }

    public RateOfExchangeBuilder withCurrency(Currency key, double value) {
        currencies.put(key, BigDecimal.valueOf(value));

        return this;
    }

    public RateOfExchange build() {
        RateOfExchange  rateOfExchange = new RateOfExchange();

        rateOfExchange.setLocation(location);
        rateOfExchange.setCurrencies(currencies);

        return rateOfExchange;
    }
}
