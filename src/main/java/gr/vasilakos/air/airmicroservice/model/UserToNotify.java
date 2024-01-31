package gr.vasilakos.air.airmicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "user-to-notify")
public class UserToNotify {

    @Id
    private String id;

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
