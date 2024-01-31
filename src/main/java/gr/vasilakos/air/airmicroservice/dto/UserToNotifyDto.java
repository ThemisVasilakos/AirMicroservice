package gr.vasilakos.air.airmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserToNotifyDto {

    private String email;
    private String coordinates;

    private Double lessAirTemperature;
    private Double moreAirTemperature;

    private Double lessAirHumidity;
    private Double moreAirHumidity;

    private Double lessAirC02;
    private Double moreAirC02;

    private Double lessAirVOCs;
    private Double moreAirVOCs;

    private Double lessAirPM25;
    private Double moreAirPM25;

    private Double lessAirC0;
    private Double moreAirC0;
}
