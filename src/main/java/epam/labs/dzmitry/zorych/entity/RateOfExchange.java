package epam.labs.dzmitry.zorych.entity;

import lombok.*;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Contains rates of exchange for current location
 */
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class RateOfExchange {
    @Setter
    @Getter
    private Location location;

    @Getter
    @Setter
    private Map<Currency, BigDecimal> currencies;

}
