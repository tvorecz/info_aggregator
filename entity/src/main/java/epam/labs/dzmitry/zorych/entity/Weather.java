package epam.labs.dzmitry.zorych.entity;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Weather implements Serializable {
    private static final long serialVersionUID = 7472098404637048742L;

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
    private double temperature;

    @Setter
    @Getter
    private double apparentTemperature;

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
