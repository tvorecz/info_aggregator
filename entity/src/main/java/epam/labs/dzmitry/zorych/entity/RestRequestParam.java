package epam.labs.dzmitry.zorych.entity;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class RestRequestParam implements Serializable {
    private static final long serialVersionUID = -5276429226625682733L;

    @Getter
    @Setter
    private String format;

    @Getter
    @Setter
    private boolean weather;

    @Getter
    @Setter
    private boolean currency;

    @Getter
    @Setter
    private String baseCurrency;
}
