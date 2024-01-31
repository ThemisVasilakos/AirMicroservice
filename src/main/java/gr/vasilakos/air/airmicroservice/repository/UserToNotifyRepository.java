package gr.vasilakos.air.airmicroservice.repository;

import gr.vasilakos.air.airmicroservice.model.UserToNotify;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

public interface UserToNotifyRepository extends MongoRepository<UserToNotify,String> {

    List<UserToNotify> findByEmail(String email);

    @Query("{'coordinates': ?0, 'lessAirTemperature': {$lte: ?1} }")
    List<UserToNotify> findByCoordinatesAndLessAirTemperature(String coordinates, Double airTemperature);

    @Query("{'coordinates': ?0, 'moreAirTemperature': {$gte: ?1} }")
    List<UserToNotify> findByCoordinatesAndMoreAirTemperature(String coordinates, Double airTemperature);

    @Query("{'coordinates': ?0, 'lessAirHumidity': {$lte: ?1} }")
    List<UserToNotify> findByCoordinatesAndLessAirHumidity(String coordinates, Double airHumidity);

    @Query("{'coordinates': ?0, 'moreAirHumidity': {$gte: ?1} }")
    List<UserToNotify> findByCoordinatesAndMoreAirHumidity(String coordinates, Double airHumidity);

    @Query("{'coordinates': ?0, 'lessAirC02': {$lte: ?1} }")
    List<UserToNotify> findByCoordinatesAndLessAirC02(String coordinates, Double airC02);

    @Query("{'coordinates': ?0, 'moreAirC02': {$gte: ?1} }")
    List<UserToNotify> findByCoordinatesAndMoreAirC02(String coordinates, Double airC02);

    @Query("{'coordinates': ?0, 'lessAirVOCs': {$lte: ?1} }")
    List<UserToNotify> findByCoordinatesAndLessAirVOCs(String coordinates, Double airVOCs);

    @Query("{'coordinates': ?0, 'moreAirVOCs': {$gte: ?1} }")
    List<UserToNotify> findByCoordinatesAndMoreAirVOCs(String coordinates, Double airVOCs);

    @Query("{'coordinates': ?0, 'lessAirPM25': {$lte: ?1} }")
    List<UserToNotify> findByCoordinatesAndLessAirPM25(String coordinates, Double airPM25);

    @Query("{'coordinates': ?0, 'moreAirPM25': {$gte: ?1} }")
    List<UserToNotify> findByCoordinatesAndMoreAirPM25(String coordinates, Double airPM25);

    @Query("{'coordinates': ?0, 'lessAirC0': {$lte: ?1} }")
    List<UserToNotify> findByCoordinatesAndLessAirC0(String coordinates, Double airC0);

    @Query("{'coordinates': ?0, 'moreAirC0': {$gte: ?1} }")
    List<UserToNotify> findByCoordinatesAndMoreAirC0(String coordinates, Double airC0);

}
