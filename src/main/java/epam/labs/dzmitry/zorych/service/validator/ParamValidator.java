package epam.labs.dzmitry.zorych.service.validator;

import epam.labs.dzmitry.zorych.entity.RequestParam;

public interface ParamValidator {
    boolean validate(RequestParam requestParam);
}
