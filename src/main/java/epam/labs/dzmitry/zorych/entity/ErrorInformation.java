package epam.labs.dzmitry.zorych.entity;

import lombok.*;

import java.io.Serializable;

/**
 * Information about exception for receiving to controller
 */
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class ErrorInformation implements Serializable {
    private static final long serialVersionUID = 8912167449777957574L;

    /**
     * Code of error
     */
    @Setter
    @Getter
    private int code;

    /**
     * Message error
     */
    @Setter
    @Getter
    private String message;

    public ErrorInformation(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
