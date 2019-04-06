package epam.labs.dzmitry.zorych.entity;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Location implements Serializable {
    private static final long serialVersionUID = -4952413286235286159L;

    @Setter
    @Getter
    private BigDecimal latitude;

    @Setter
    @Getter
    private BigDecimal longitude;

    @Setter
    @Getter
    private String timeZone;

    @Getter
    @Setter
    private String isoCode;

    @Getter
    @Setter
    private String continent;

    @Getter
    @Setter
    private String country;

    @Getter
    @Setter
    private String city;

}
