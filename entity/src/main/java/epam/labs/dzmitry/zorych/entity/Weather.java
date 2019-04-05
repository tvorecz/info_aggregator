package epam.labs.dzmitry.zorych.entity;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Weather {
    @Setter
    @Getter
    private Location location;

    @Setter
    @Getter
    private String summary;

    @Setter
    @Getter
    private String icon;

    @Setter
    @Getter
    private int temperature;

    @Setter
    @Getter
    private int apparentTemperature;

    @Setter
    @Getter
    private double pressure;

    @Setter
    @Getter
    private double windSpeed;

    @Setter
    @Getter
    private double windGust;
}
