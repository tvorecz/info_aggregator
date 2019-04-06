package epam.labs.dzmitry.zorych.entitybuilder.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import epam.labs.dzmitry.zorych.entity.Currency;
import epam.labs.dzmitry.zorych.entity.RateOfExchange;
import epam.labs.dzmitry.zorych.entitybuilder.EntityBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RateOfExchangeBuilder implements EntityBuilder<RateOfExchange> {
    private final static Currency[] CURRENCIES = {Currency.EUR, Currency.USD, Currency.GBP, Currency.CNY};

    @Override
    public RateOfExchange build(String json) {
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonRoot = jsonParser.parse(json);
        JsonObject jsonObject = jsonRoot.getAsJsonObject();

        Set<String> keySet = jsonObject.keySet();
        String base = (keySet.toArray(new String[keySet.size()]))[0].substring(0, 3);


        RateOfExchange rateOfExchange = new RateOfExchange();

        rateOfExchange.setDate(LocalDate.now());
        rateOfExchange.setBaseIsoCode(Currency.valueOf(base));

        Map<Currency, BigDecimal> currencies = new HashMap<>();

        for (Currency currency : CURRENCIES) {
            currencies.put(currency, jsonObject.get(base + "_" + currency.name()).getAsBigDecimal());
        }
        
        rateOfExchange.setCurrencies(currencies);

        return rateOfExchange;
    }
}
