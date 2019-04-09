package epam.labs.dzmitry.zorych.mediator.impl;

import epam.labs.dzmitry.zorych.entity.*;
import epam.labs.dzmitry.zorych.mediator.CommonMediator;
import epam.labs.dzmitry.zorych.mediator.InvalidParameterException;
import epam.labs.dzmitry.zorych.mediator.ReceivingDataIsFailedException;
import epam.labs.dzmitry.zorych.service.CannotGetDataException;
import epam.labs.dzmitry.zorych.service.CommonService;
import epam.labs.dzmitry.zorych.validator.ParamValidator;

public class ServiceMediator implements CommonMediator {
    private ParamValidator validator;

    private CommonService<Location> locationService;
    private CommonService<Weather> weatherService;
    private CommonService<RateOfExchange> rateOfExchangeService;

    public ServiceMediator(ParamValidator validator,
                           CommonService<Location> locationService,
                           CommonService<Weather> weatherService,
                           CommonService<RateOfExchange> rateOfExchangeService) {
        this.validator = validator;
        this.locationService = locationService;
        this.weatherService = weatherService;
        this.rateOfExchangeService = rateOfExchangeService;
    }

    @Override
    public ResponseParam get(RequestParam requestParam) throws InvalidParameterException,
                                                               ReceivingDataIsFailedException {
        ResponseParam response = new ResponseParam();

        if(validator.validate(requestParam)) {
            Weather weather = null;
            RateOfExchange currency = null;

            Location locationForFill = new Location(requestParam.getLatitude(), requestParam.getLongitude());

            try {
                Location location = locationService.get(locationForFill);

                response.setLocation(location);
                response.setLocation(true);

                if(requestParam.isWeather()) {
                    weather = weatherService.get(location);

                    response.setWeather(weather);
                    response.setWeather(true);
                }

                if(requestParam.isCurrency()) {
                    currency = rateOfExchangeService.get(location);
                    response.setRateOfExchange(currency);
                    response.setCurrency(true);
                }
            } catch (CannotGetDataException e) {
                throw new ReceivingDataIsFailedException("Receiving data is failed.", e, e.getCode());
            }
        } else {
            throw new InvalidParameterException("Invalid location or parameters' combination.", 400);
        }

        return response;
    }
}
