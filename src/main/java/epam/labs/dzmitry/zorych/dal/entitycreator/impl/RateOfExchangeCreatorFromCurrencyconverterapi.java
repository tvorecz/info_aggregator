package epam.labs.dzmitry.zorych.dal.entitycreator.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import epam.labs.dzmitry.zorych.entity.Currency;
import epam.labs.dzmitry.zorych.entity.RateOfExchange;
import epam.labs.dzmitry.zorych.dal.entitycreator.DataSourceException;
import epam.labs.dzmitry.zorych.dal.entitycreator.EntityCreator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Create RateOfExchange from json-string, receiving from CurrencyConverterApi
 */
public class RateOfExchangeCreatorFromCurrencyconverterapi implements EntityCreator<RateOfExchange> {
    private final static Currency[] CURRENCIES = {Currency.EUR, Currency.USD, Currency.RUB, Currency.GBP, Currency.CNY};

    @Override
    public RateOfExchange create(String json) throws DataSourceException {
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonRoot = jsonParser.parse(json);
        JsonObject jsonObject = jsonRoot.getAsJsonObject();

        JsonElement error = jsonObject.get("error");

        if(error != null) {
            JsonElement status = jsonObject.get("status");
            throw new DataSourceException(error.getAsString(), status.getAsInt());
        }

        Set<String> keySet = jsonObject.keySet();
        String base = (keySet.toArray(new String[keySet.size()]))[0].substring(0, 3);


        RateOfExchange rateOfExchange = new RateOfExchange();

        Map<Currency, BigDecimal> currencies = new HashMap<>();

        for (Currency currency : CURRENCIES) {
            currencies.put(currency, jsonObject.get(base + "_" + currency.name()).getAsBigDecimal());
        }
        
        rateOfExchange.setCurrencies(currencies);

        return rateOfExchange;
    }
}
