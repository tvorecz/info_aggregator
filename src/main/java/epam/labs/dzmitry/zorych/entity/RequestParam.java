package epam.labs.dzmitry.zorych.entity;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Serve for sending request information from controller to services
 */
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class RequestParam implements Serializable {
    private static final long serialVersionUID = 6254043388346691481L;

    @Getter
    @Setter
    private boolean weather;

    @Getter
    @Setter
    private boolean currency;

    @Getter
    @Setter
    private boolean location;

    @Getter
    @Setter
    private BigDecimal latitude;

    @Getter
    @Setter
    private BigDecimal longitude;
}
