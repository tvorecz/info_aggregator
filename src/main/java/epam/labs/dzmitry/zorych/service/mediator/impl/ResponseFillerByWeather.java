package epam.labs.dzmitry.zorych.service.mediator.impl;

import epam.labs.dzmitry.zorych.entity.Location;
import epam.labs.dzmitry.zorych.entity.RequestParam;
import epam.labs.dzmitry.zorych.entity.ResponseParam;
import epam.labs.dzmitry.zorych.entity.Weather;
import epam.labs.dzmitry.zorych.service.mediator.ResponseFiller;
import epam.labs.dzmitry.zorych.service.commonservice.CannotGetDataException;
import epam.labs.dzmitry.zorych.service.commonservice.CommonService;

public class ResponseFillerByWeather implements ResponseFiller {
    private CommonService<Weather> weatherService;

    public ResponseFillerByWeather(CommonService<Weather> weatherService) {
        this.weatherService = weatherService;
    }

    @Override
    public void fill(Location location,
                     ResponseParam responseParam,
                     RequestParam requestParam) throws CannotGetDataException {
        if(requestParam.isWeather()) {
            Weather weather = weatherService.get(location);

            weather.setLocation(location);

            responseParam.setWeather(weather);
            responseParam.setIsWeather(true);
        }
    }
}
