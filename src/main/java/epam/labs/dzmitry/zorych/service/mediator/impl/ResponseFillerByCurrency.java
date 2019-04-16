package epam.labs.dzmitry.zorych.service.mediator.impl;

import epam.labs.dzmitry.zorych.entity.*;
import epam.labs.dzmitry.zorych.service.mediator.ReceivingDataIsFailedException;
import epam.labs.dzmitry.zorych.service.mediator.ResponseFiller;
import epam.labs.dzmitry.zorych.service.commonservice.CannotGetDataException;
import epam.labs.dzmitry.zorych.service.commonservice.CommonService;

public class ResponseFillerByCurrency implements ResponseFiller {
    private CommonService<RateOfExchange> rateOfExchangeService;

    public ResponseFillerByCurrency(CommonService<RateOfExchange> rateOfExchangeService) {
        this.rateOfExchangeService = rateOfExchangeService;
    }

    @Override
    public void fill(Location location, ResponseParam responseParam, RequestParam requestParam) throws
                                                                                                CannotGetDataException,
                                                                                                ReceivingDataIsFailedException {
        if(requestParam.isCurrency()) {
            if(location.getCurrencyCode() != null) {
                RateOfExchange currency = rateOfExchangeService.get(location);
                currency.setLocation(location);
                responseParam.setRateOfExchange(currency);
                responseParam.setIsCurrency(true);
            } else {
                throw new ReceivingDataIsFailedException("No iso code.", 404);
            }
        }
    }
}
