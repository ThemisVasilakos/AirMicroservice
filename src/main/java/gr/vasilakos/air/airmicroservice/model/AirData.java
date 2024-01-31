package gr.vasilakos.air.airmicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "air-data")
public class AirData {

    private Double airTemperature;
    private Double airHumidity;
    private Double airC02;
    private Double airVOCs;
    private Double airPM25;
    private Double airC0;
    @CreatedDate
    private Instant timestamp;
    private String coordinates;

}
