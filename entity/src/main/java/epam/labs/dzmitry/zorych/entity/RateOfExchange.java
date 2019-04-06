package epam.labs.dzmitry.zorych.entity;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class RateOfExchange {
    @Setter
    @Getter
    private LocalDate date;

    @Setter
    @Getter
    private Currency baseIsoCode;

    @Getter
    @Setter
    private Map<Currency, BigDecimal> currencies;

}
