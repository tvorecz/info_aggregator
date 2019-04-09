package epam.labs.dzmitry.zorych.config;

import epam.labs.dzmitry.zorych.dao.JsonDao;
import epam.labs.dzmitry.zorych.entity.Location;
import epam.labs.dzmitry.zorych.entity.RateOfExchange;
import epam.labs.dzmitry.zorych.entity.Weather;
import epam.labs.dzmitry.zorych.factory.AbstractDaoFactory;
import epam.labs.dzmitry.zorych.factory.impl.DaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import java.lang.reflect.InvocationTargetException;

@Configuration
@Import(AspectConfig.class)
@EnableAspectJAutoProxy
@PropertySource("classpath:source_settings.properties")
public class DalConfig {
    @Autowired
    private Environment environment;
    @Autowired
    private AbstractDaoFactory daoFactory;

    @Bean
    public AbstractDaoFactory daoFactory(){
        return new DaoFactory();
    }

    @Bean(name = "weatherDao")
    public JsonDao<Weather> weatherDao() throws
                                         ClassNotFoundException,
                                         InvocationTargetException,
                                         InstantiationException,
                                         IllegalAccessException {
        return daoFactory.createDao(environment.getProperty("weather.url"), environment.getProperty("weather.key"), environment.getProperty("weather.provider"));
    }



    @Bean(name = "locationDao")
    public JsonDao<Location> locationDao() throws
                                           InvocationTargetException,
                                           ClassNotFoundException,
                                           InstantiationException,
                                           IllegalAccessException {
        return daoFactory.createDao(environment.getProperty("location.url"), environment.getProperty("location.key"), environment.getProperty("location.provider"));
    }



    @Bean(name = "rateOfExchangeDao")
    public JsonDao<RateOfExchange> rateOfExchangeDao() throws
                                           InvocationTargetException,
                                           ClassNotFoundException,
                                           InstantiationException,
                                           IllegalAccessException {
        return daoFactory.createDao(environment.getProperty("currency.url"), environment.getProperty("currency.key"), environment.getProperty("currency.provider"));
    }
}
