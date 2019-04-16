package epam.labs.dzmitry.zorych.config;

import epam.labs.dzmitry.zorych.dal.dao.JsonDao;
import epam.labs.dzmitry.zorych.entity.Location;
import epam.labs.dzmitry.zorych.entity.RateOfExchange;
import epam.labs.dzmitry.zorych.entity.Weather;
import epam.labs.dzmitry.zorych.dal.factory.AbstractDaoFactory;
import epam.labs.dzmitry.zorych.dal.factory.DaoBuildingFailedException;
import epam.labs.dzmitry.zorych.dal.factory.impl.DaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

@Configuration
@Import(AspectConfig.class)
@EnableAspectJAutoProxy
@PropertySource("classpath:source_settings.properties")
public class DalConfig {
    @Autowired
    private Environment environment;

    @Bean
    public AbstractDaoFactory daoFactory(){
        return new DaoFactory();
    }

    @Bean(name = "weatherDao")
    public JsonDao<Weather> weatherDao() throws DaoBuildingFailedException {
        return daoFactory().createDao(environment.getProperty("weather.url"), environment.getProperty("weather.key"), environment.getProperty("weather.provider"));
    }



    @Bean(name = "locationDao")
    public JsonDao<Location> locationDao() throws DaoBuildingFailedException {
        return daoFactory().createDao(environment.getProperty("location.url"), environment.getProperty("location.key"), environment.getProperty("location.provider"));
    }



    @Bean(name = "rateOfExchangeDao")
    public JsonDao<RateOfExchange> rateOfExchangeDao() throws DaoBuildingFailedException {
        return daoFactory().createDao(environment.getProperty("currency.url"), environment.getProperty("currency.key"), environment.getProperty("currency.provider"));
    }
}
