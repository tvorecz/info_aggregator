package epam.labs.dzmitry.zorych.mediator.impl;

import epam.labs.dzmitry.zorych.entity.*;
import epam.labs.dzmitry.zorych.mediator.CommonServiceMediator;
import epam.labs.dzmitry.zorych.mediator.InvalidParameterException;
import epam.labs.dzmitry.zorych.mediator.ReceivingDataIsFailedException;
import epam.labs.dzmitry.zorych.service.CannotGetDataException;
import epam.labs.dzmitry.zorych.service.CommonService;
import epam.labs.dzmitry.zorych.validator.ParamValidator;

/**
 * Provides accessing to dao according to current request parameters
 */
public class ServiceMediator implements CommonServiceMediator {
    private ParamValidator validator;

    private CommonService<Location> locationService;
    private CommonService<Weather> weatherService;
    private CommonService<RateOfExchange> rateOfExchangeService;

    /**
     * Create instance of mediator
     * @param validator Validator for request params
     * @param locationService Service for accessing to location-dao
     * @param weatherService Service for accessing to weather-dao
     * @param rateOfExchangeService Service for accessing to currency-dao
     */
    public ServiceMediator(ParamValidator validator,
                           CommonService<Location> locationService,
                           CommonService<Weather> weatherService,
                           CommonService<RateOfExchange> rateOfExchangeService) {
        this.validator = validator;
        this.locationService = locationService;
        this.weatherService = weatherService;
        this.rateOfExchangeService = rateOfExchangeService;
    }

    /**
     * Form response according ro current request params
     * @param requestParam Request params
     * @return
     * @throws InvalidParameterException Generates when request params are invalid
     * @throws ReceivingDataIsFailedException Generates when received exception from data layer
     */
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
                response.setIsLocation(requestParam.isLocation());

                if(requestParam.isWeather()) {
                    weather = weatherService.get(location);

                    weather.setLocation(location);

                    response.setWeather(weather);
                    response.setIsWeather(true);
                }

                if(requestParam.isCurrency()) {
                    if(location.getCurrencyCode() != null) {
                        currency = rateOfExchangeService.get(location);
                        currency.setLocation(location);
                        response.setRateOfExchange(currency);
                        response.setIsCurrency(true);
                    } else {
                        throw new ReceivingDataIsFailedException("No iso code.", 404);
                    }
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
