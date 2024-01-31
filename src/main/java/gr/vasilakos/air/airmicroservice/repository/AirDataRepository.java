package gr.vasilakos.air.airmicroservice.repository;

import gr.vasilakos.air.airmicroservice.model.AirData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.time.Instant;
import java.util.List;

public interface AirDataRepository extends MongoRepository<AirData,String> {

    AirData findFirstByCoordinatesOrderByTimestampDesc(String coordinates);
    @Query("{'timestamp': {$gte: ?0, $lte: ?1}, 'coordinates': ?2}")
    List<AirData> findByTimestampAndCoordinatesOOrderByTimestampAirHumidityAsc(Instant startDate, Instant endDate, String coordinates);
}
