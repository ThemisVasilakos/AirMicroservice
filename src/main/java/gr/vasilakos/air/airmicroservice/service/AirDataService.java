package gr.vasilakos.air.airmicroservice.service;

import gr.vasilakos.air.airmicroservice.dto.AirDataDto;
import gr.vasilakos.air.airmicroservice.model.AirData;
import gr.vasilakos.air.airmicroservice.repository.AirDataRepository;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirDataService {

    private final AirDataRepository airDataRepository;

    private final UserToNotifyService userToNotifyService;


    public AirDataService(AirDataRepository airDataRepository, UserToNotifyService userToNotifyService) {
        this.airDataRepository = airDataRepository;
        this.userToNotifyService = userToNotifyService;
    }

    public List<AirDataDto> findByLocationAndDates(String coordinates, Instant  start, Instant  end){
        List<AirData> res = null;

        res = airDataRepository.findByTimestampAndCoordinatesOOrderByTimestampAirHumidityAsc(start,end,coordinates);

        return res.stream()
                .map(this::mapAirDataToAirDataDto)
                .collect(Collectors.toList());
    }

    public AirDataDto findLastEntry(String location){
        return mapAirDataToAirDataDto(airDataRepository.findFirstByCoordinatesOrderByTimestampDesc(location));
    }

    public AirDataDto mapAirDataToAirDataDto(AirData airData){
        return AirDataDto.builder()
                .airTemperature(airData.getAirTemperature())
                .airHumidity(airData.getAirHumidity())
                .airC02(airData.getAirC02())
                .airVOCs(airData.getAirVOCs())
                .airPM25(airData.getAirPM25())
                .airC0(airData.getAirC0())
                .timestamp(airData.getTimestamp())
                .coordinates(airData.getCoordinates())
                .build();
    }

}
