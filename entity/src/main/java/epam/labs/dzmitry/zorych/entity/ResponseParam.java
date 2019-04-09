package epam.labs.dzmitry.zorych.entity;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ResponseParam implements Serializable {
    private static final long serialVersionUID = 7038376738406505846L;

    @Getter
    private boolean isWeather;

    @Getter
    private boolean isCurrency;

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

    public void setIsWeather(boolean weather) {
        isWeather = weather;
    }

    public void setIsCurrency(boolean currency) {
        isCurrency = currency;
    }

    public void setIsLocation(boolean location) {
        isLocation = location;
    }
}
