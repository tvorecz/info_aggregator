package epam.labs.dzmitry.zorych.config;

import epam.labs.dzmitry.zorych.dao.JsonDao;
import epam.labs.dzmitry.zorych.entity.Location;
import epam.labs.dzmitry.zorych.entity.RateOfExchange;
import epam.labs.dzmitry.zorych.entity.Weather;
import epam.labs.dzmitry.zorych.mediator.CommonServiceMediator;
import epam.labs.dzmitry.zorych.mediator.impl.ServiceMediator;
import epam.labs.dzmitry.zorych.service.impl.CommonServiceImpl;
import epam.labs.dzmitry.zorych.validator.ParamValidator;
import epam.labs.dzmitry.zorych.validator.impl.RequestParamValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAspectJAutoProxy
@Import(DalConfig.class)
public class ServiceConfig {

    @Autowired
    public JsonDao<Weather> weatherDao;
    @Autowired
    public JsonDao<Location> locationDao;
    @Autowired
    public JsonDao<RateOfExchange> rateOfExchangeDao;

    @Bean
    public ParamValidator validator() {
        return new RequestParamValidator();
    }

    @Bean
    public CommonServiceMediator serviceMediator() {
        return new ServiceMediator(validator(), new CommonServiceImpl<Location>(locationDao), new CommonServiceImpl<Weather>(weatherDao), new CommonServiceImpl<RateOfExchange>(rateOfExchangeDao));
    }
}
