package epam.labs.dzmitry.zorych.factory.impl;

import epam.labs.dzmitry.zorych.apimanager.JsonApiManager;
import epam.labs.dzmitry.zorych.apimanager.impl.JsonRequestApiManager;
import epam.labs.dzmitry.zorych.dao.JsonDao;
import epam.labs.dzmitry.zorych.dao.impl.JsonDaoImpl;
import epam.labs.dzmitry.zorych.entity.Location;
import epam.labs.dzmitry.zorych.entity.RateOfExchange;
import epam.labs.dzmitry.zorych.entity.Weather;
import epam.labs.dzmitry.zorych.entitycreator.EntityCreator;
import epam.labs.dzmitry.zorych.entitycreator.impl.WeatherCreatorFromDarksky;
import epam.labs.dzmitry.zorych.factory.AbstractDaoFactory;
import epam.labs.dzmitry.zorych.urlcreator.RequestUrlCreator;
import epam.labs.dzmitry.zorych.urlcreator.impl.JsonRequestUrlCreatorByLocation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class DaoFactory implements AbstractDaoFactory {
    private final Map<String, String> providers;

    public DaoFactory() {
        providers= new HashMap<>();
        providers.put("darksky", "epam.labs.dzmitry.zorych.entitycreator.impl.WeatherCreatorFromDarksky");
        providers.put("currencyconverterapi", "epam.labs.dzmitry.zorych.entitycreator.impl.RateOfExchangeCreatorFromCurrencyconverterapi");
        providers.put("opencagedata", "epam.labs.dzmitry.zorych.entitycreator.impl.LocationCreatorFromOpencagedata");
    }

    @Override
    public <T> JsonDao<T> createDao(String urlTemplate, String apiKey, String apiProvider) throws
                                                                                           ClassNotFoundException,
                                                                                           InvocationTargetException,
                                                                                           InstantiationException,
                                                                                           IllegalAccessException {
        JsonDao<T> dao = new JsonDaoImpl<>(weatherJsonManager(urlTemplate, apiKey), entityCreator(apiProvider));

        return dao;
    }

    private RequestUrlCreator weatherUrlBuilder(String urlTemplate, String apiKey) {
        RequestUrlCreator urlBuilder = new JsonRequestUrlCreatorByLocation(urlTemplate, apiKey);

        return urlBuilder;
    }

    private JsonApiManager weatherJsonManager(String urlTemplate, String apiKey) {
        return new JsonRequestApiManager(weatherUrlBuilder(urlTemplate, apiKey));
    }

    private <T> EntityCreator<T> entityCreator(String apiProvider) throws
                                                   ClassNotFoundException,
                                                   IllegalAccessException,
                                                   InvocationTargetException,
                                                   InstantiationException {
        Class builderClass = Class.forName(providers.get(apiProvider.toLowerCase()));

        Constructor[] constructors = builderClass.getConstructors();

        EntityCreator<T> builder = (EntityCreator<T>) constructors[0].newInstance();

        return builder;
    }
}
