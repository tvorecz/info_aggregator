package epam.labs.dzmitry.zorych.entity;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ErrorInformation implements Serializable {
    private static final long serialVersionUID = 8912167449777957574L;

    @Setter
    @Getter
    private int code;

    @Setter
    @Getter
    private String message;

    public ErrorInformation(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
