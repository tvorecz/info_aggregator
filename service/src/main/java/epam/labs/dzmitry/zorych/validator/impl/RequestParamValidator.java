package epam.labs.dzmitry.zorych.validator.impl;

import epam.labs.dzmitry.zorych.entity.RequestParam;
import epam.labs.dzmitry.zorych.validator.ParamValidator;

/**
 * Validator for current request params
 */
public class RequestParamValidator implements ParamValidator {

    @Override
    public boolean validate(RequestParam requestParam) {
        if(requestParam.getLatitude().doubleValue() > 90.0 || requestParam.getLatitude().doubleValue() < -90.0) {
            return false;
        } else if(requestParam.getLongitude().doubleValue() > 180.0 || requestParam.getLongitude().doubleValue() < -180.0) {
            return false;
        } else if(!requestParam.isWeather() && !requestParam.isCurrency() && !requestParam.isLocation()) {
            return false;
        }

        return true;
    }
}
