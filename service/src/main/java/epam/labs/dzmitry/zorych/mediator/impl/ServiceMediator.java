package epam.labs.dzmitry.zorych.mediator.impl;

import epam.labs.dzmitry.zorych.entity.RequestParam;
import epam.labs.dzmitry.zorych.entity.ResponseParam;
import epam.labs.dzmitry.zorych.mediator.CommonMediator;
import epam.labs.dzmitry.zorych.mediator.InvalidParameterException;
import epam.labs.dzmitry.zorych.validator.ParamValidator;

public class ServiceMediator implements CommonMediator {
    private ParamValidator validator;

    @Override
    public ResponseParam get(RequestParam requestParam) throws InvalidParameterException {
        ResponseParam response = new ResponseParam();

        if(validator.validate(requestParam)) {

        } else {
            throw new InvalidParameterException("Invalid location or parameters' combination.", 400);
        }

        return response;
    }
}
