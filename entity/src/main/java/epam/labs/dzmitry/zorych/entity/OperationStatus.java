package epam.labs.dzmitry.zorych.entity;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class OperationStatus implements Serializable {
    private static final long serialVersionUID = -8767016561957105075L;

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String message;
}
