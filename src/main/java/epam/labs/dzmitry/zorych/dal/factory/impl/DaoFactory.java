package epam.labs.dzmitry.zorych.dal.factory.impl;

import epam.labs.dzmitry.zorych.dal.apimanager.JsonApiManager;
import epam.labs.dzmitry.zorych.dal.apimanager.impl.JsonRequestApiManager;
import epam.labs.dzmitry.zorych.dal.dao.JsonDao;
import epam.labs.dzmitry.zorych.dal.factory.AbstractDaoFactory;
import epam.labs.dzmitry.zorych.dal.dao.impl.JsonDaoImpl;
import epam.labs.dzmitry.zorych.dal.entitycreator.EntityCreator;
import epam.labs.dzmitry.zorych.dal.factory.DaoBuildingFailedException;
import epam.labs.dzmitry.zorych.dal.urlcreator.RequestUrlCreator;
import epam.labs.dzmitry.zorych.dal.urlcreator.impl.JsonRequestUrlCreatorByLocation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Factory creates data access objects for receiving data from rest-providers
 */
public class DaoFactory implements AbstractDaoFactory {
    /**
     * Key - provider name, Value - entity creator for current provider
     */
    private final Map<String, String> providers;

    public DaoFactory() {
        providers= new HashMap<>();
        providers.put("darksky", "epam.labs.dzmitry.zorych.dal.entitycreator.impl.WeatherCreatorFromDarksky");
        providers.put("currencyconverterapi", "epam.labs.dzmitry.zorych.dal.entitycreator.impl.RateOfExchangeCreatorFromCurrencyconverterapi");
        providers.put("opencagedata", "epam.labs.dzmitry.zorych.dal.entitycreator.impl.LocationCreatorFromOpencagedata");
    }

    /**
     * Create instance of dao according to urt template, access key and privider name
     * @param <T> Type of entity
     * @param urlTemplate Template of access url to rest-provider
     * @param apiKey Unique api key for access to rest-provider
     * @param apiProvider Name of rest-provider
     * @return
     * @throws DaoBuildingFailedException Generates in case when dao bulding finishs with error
     */
    @Override
    public <T> JsonDao<T> createDao(String urlTemplate, String apiKey, String apiProvider) throws
                                                                                           DaoBuildingFailedException {
        JsonDao<T> dao = null;
        try {
            dao = new JsonDaoImpl<>(weatherJsonManager(urlTemplate, apiKey), entityCreator(apiProvider));
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new DaoBuildingFailedException("Building dao is failed.", e);
        }

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
