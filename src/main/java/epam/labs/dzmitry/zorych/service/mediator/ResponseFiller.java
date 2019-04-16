package epam.labs.dzmitry.zorych.service.mediator;

import epam.labs.dzmitry.zorych.entity.Location;
import epam.labs.dzmitry.zorych.entity.RequestParam;
import epam.labs.dzmitry.zorych.entity.ResponseParam;
import epam.labs.dzmitry.zorych.service.commonservice.CannotGetDataException;

public interface ResponseFiller {
    void fill(Location location, ResponseParam responseParam, RequestParam requestParam) throws
                                                                                         CannotGetDataException,
                                                                                         ReceivingDataIsFailedException;
}
