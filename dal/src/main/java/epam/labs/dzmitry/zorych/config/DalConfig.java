package epam.labs.dzmitry.zorych.config;

import epam.labs.dzmitry.zorych.apimanager.JsonApiManager;
import epam.labs.dzmitry.zorych.apimanager.impl.JsonRequestApiManager;
import epam.labs.dzmitry.zorych.dao.JsonDao;
import epam.labs.dzmitry.zorych.dao.impl.JsonDaoImpl;
import epam.labs.dzmitry.zorych.entity.Location;
import epam.labs.dzmitry.zorych.entity.RateOfExchange;
import epam.labs.dzmitry.zorych.entity.Weather;
import epam.labs.dzmitry.zorych.entitybuilder.EntityBuilder;
import epam.labs.dzmitry.zorych.entitybuilder.impl.LocationBuilder;
import epam.labs.dzmitry.zorych.entitybuilder.impl.RateOfExchangeBuilder;
import epam.labs.dzmitry.zorych.entitybuilder.impl.WeatherBuilder;
import epam.labs.dzmitry.zorych.urlbuilder.RequestUrlBuilder;
import epam.labs.dzmitry.zorych.urlbuilder.impl.JsonRequestUrlBuilderByLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@Configuration
@PropertySource("classpath:source_settings.properties")
public class DalConfig {
    @Autowired
    private Environment environment;

    @Bean(name = "weatherDao")
    public JsonDao<Weather> weatherDao() throws
                                         ClassNotFoundException,
                                         InvocationTargetException,
                                         InstantiationException,
                                         IllegalAccessException {
        JsonDao<Weather> dao = new JsonDaoImpl<>(weatherJsonManager(), weatherBuilder());

        return dao;
    }

    @Bean
    public RequestUrlBuilder weatherUrlBuilder() {
        String urlTemplate = environment.getProperty("weather.url");
        String key = environment.getProperty("weather.key");

        RequestUrlBuilder urlBuilder = new JsonRequestUrlBuilderByLocation(urlTemplate, key);

        return urlBuilder;
    }

    @Bean
    public JsonApiManager weatherJsonManager() {
        return new JsonRequestApiManager(weatherUrlBuilder());
    }

    @Bean
    public EntityBuilder<Weather> weatherBuilder() throws
                                                   ClassNotFoundException,
                                                   IllegalAccessException,
                                                   InvocationTargetException,
                                                   InstantiationException {
        Class builderClass = Class.forName(environment.getProperty("weather.builder"));

        Constructor[] constructors = builderClass.getConstructors();

        EntityBuilder<Weather> builder = (WeatherBuilder) constructors[0].newInstance();

        return builder;
    }



    @Bean(name = "locationDao")
    public JsonDao<Location> locationDao() throws
                                           InvocationTargetException,
                                           ClassNotFoundException,
                                           InstantiationException,
                                           IllegalAccessException {
        JsonDao<Location> dao = new JsonDaoImpl<>(locationJsonManager(), locationBuilder());

        return dao;
    }

    @Bean
    public RequestUrlBuilder locationUrlBuilder() {
        String urlTemplate = environment.getProperty("location.url");
        String key = environment.getProperty("location.key");

        RequestUrlBuilder urlBuilder = new JsonRequestUrlBuilderByLocation(urlTemplate, key);

        return urlBuilder;
    }

    @Bean
    public JsonApiManager locationJsonManager() {
        return new JsonRequestApiManager(locationUrlBuilder());
    }

    @Bean
    public EntityBuilder<Location> locationBuilder() throws
                                                     IllegalAccessException,
                                                     InvocationTargetException,
                                                     InstantiationException, ClassNotFoundException {
        Class builderClass = Class.forName(environment.getProperty("location.builder"));

        Constructor[] constructors = builderClass.getConstructors();

        EntityBuilder<Location> builder = (LocationBuilder) constructors[0].newInstance();

        return builder;
    }



    @Bean(name = "rateOfExchangeDao")
    public JsonDao<RateOfExchange> rateOfExchangeDao() throws
                                           InvocationTargetException,
                                           ClassNotFoundException,
                                           InstantiationException,
                                           IllegalAccessException {
        JsonDao<RateOfExchange> dao = new JsonDaoImpl<>(rateOfExchangeJsonManager(), rateOfExchangeBuilder());

        return dao;
    }

    @Bean
    public RequestUrlBuilder rateOfExchangeUrlBuilder() {
        String urlTemplate = environment.getProperty("currency.url");
        String key = environment.getProperty("currency.key");

        RequestUrlBuilder urlBuilder = new JsonRequestUrlBuilderByLocation(urlTemplate, key);

        return urlBuilder;
    }

    @Bean
    public JsonApiManager rateOfExchangeJsonManager() {
        return new JsonRequestApiManager(rateOfExchangeUrlBuilder());
    }

    @Bean
    public EntityBuilder<RateOfExchange> rateOfExchangeBuilder() throws
                                                     IllegalAccessException,
                                                     InvocationTargetException,
                                                     InstantiationException, ClassNotFoundException {
        Class builderClass = Class.forName(environment.getProperty("currency.builder"));

        Constructor[] constructors = builderClass.getConstructors();

        EntityBuilder<RateOfExchange> builder = (RateOfExchangeBuilder) constructors[0].newInstance();

        return builder;
    }

}
