package gr.vasilakos.air.airmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirDataDto {

    private Double airTemperature;
    private Double airHumidity;
    private Double airC02;
    private Double airVOCs;
    private Double airPM25;
    private Double airC0;
    private Instant timestamp;
    private String coordinates;
}
