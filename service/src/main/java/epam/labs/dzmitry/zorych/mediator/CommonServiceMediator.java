package epam.labs.dzmitry.zorych.mediator;

import epam.labs.dzmitry.zorych.entity.RequestParam;
import epam.labs.dzmitry.zorych.entity.ResponseParam;

public interface CommonServiceMediator {
    ResponseParam get(RequestParam requestParam) throws InvalidParameterException, ReceivingDataIsFailedException;
}
