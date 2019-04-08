package epam.labs.dzmitry.zorych.validator;

import epam.labs.dzmitry.zorych.entity.RequestParam;

public interface ParamValidator {
    boolean validate(RequestParam requestParam);
}
