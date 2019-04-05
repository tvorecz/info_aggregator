package epam.labs.dzmitry.zorych.entity;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class RateOfExchange {
    @Setter
    @Getter
    private LocalDate date;

    @Setter
    @Getter
    private String baseIsoCode;



}
