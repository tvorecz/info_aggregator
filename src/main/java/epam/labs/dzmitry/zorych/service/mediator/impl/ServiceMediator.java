package epam.labs.dzmitry.zorych.service.mediator.impl;

import epam.labs.dzmitry.zorych.entity.*;
import epam.labs.dzmitry.zorych.service.mediator.CommonServiceMediator;
import epam.labs.dzmitry.zorych.service.mediator.InvalidParameterException;
import epam.labs.dzmitry.zorych.service.mediator.ReceivingDataIsFailedException;
import epam.labs.dzmitry.zorych.service.mediator.ResponseFiller;
import epam.labs.dzmitry.zorych.service.commonservice.CannotGetDataException;
import epam.labs.dzmitry.zorych.service.commonservice.CommonService;
import epam.labs.dzmitry.zorych.service.validator.ParamValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides accessing to dao according to current request parameters
 */
public class ServiceMediator implements CommonServiceMediator {
    private ParamValidator validator;

    private CommonService<Location> locationService;
    private CommonService<Weather> weatherService;
    private CommonService<RateOfExchange> rateOfExchangeService;

    private List<ResponseFiller> responseFillers;

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

        responseFillers = new ArrayList<>();
        responseFillers.add(new ResponseFillerByCurrency(rateOfExchangeService));
        responseFillers.add(new ResponseFillerByWeather(weatherService));
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
            Location locationForFill = new Location(requestParam.getLatitude(), requestParam.getLongitude());

            try {
                Location location = locationService.get(locationForFill);

                response.setLocation(location);
                response.setIsLocation(requestParam.isLocation());

                for (ResponseFiller responseFiller : responseFillers) {
                    responseFiller.fill(location, response, requestParam);
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
