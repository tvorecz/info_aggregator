package epam.labs.dzmitry.zorych.entity;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ResponseParam implements Serializable {
    private static final long serialVersionUID = 7038376738406505846L;

    @Setter
    @Getter
    private boolean isWeather;

    @Setter
    @Getter
    private boolean isCurrency;

    @Setter
    @Getter
    private boolean isLocation;

    @Setter
    @Getter
    private Weather weather;

    @Setter
    @Getter
    private RateOfExchange rateOfExchange;

    @Setter
    @Getter
    private Location location;
}
