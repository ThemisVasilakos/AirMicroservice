package gr.vasilakos.air.airmicroservice.service;

import gr.vasilakos.air.airmicroservice.dto.AirQueueDataDto;
import gr.vasilakos.air.airmicroservice.dto.AirQueueNotifyUsersDto;
import gr.vasilakos.air.airmicroservice.dto.UserToNotifyDto;
import gr.vasilakos.air.airmicroservice.model.AirData;
import gr.vasilakos.air.airmicroservice.model.UserToNotify;
import gr.vasilakos.air.airmicroservice.repository.UserToNotifyRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserToNotifyService {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.air.routing.key}")
    private String routingKey;

    private final UserToNotifyRepository userToNotifyRepository;

    private final RabbitTemplate rabbitTemplate;

    public UserToNotifyService(UserToNotifyRepository userToNotifyRepository, RabbitTemplate rabbitTemplate) {
        this.userToNotifyRepository = userToNotifyRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public UserToNotifyDto userSubToAirData(UserToNotifyDto userToNotifyDto){
        userToNotifyRepository.save(mapUserToNotifyDtoToUserToNotify(userToNotifyDto));
        return userToNotifyDto;
    }

    public void deleteUser(String email){
        List<UserToNotify> userToNotifyList = userToNotifyRepository.findByEmail(email);
        userToNotifyRepository.deleteAll(userToNotifyList);
    }

    public void getNotifyUserList(AirData airData){
        List<UserToNotify> userToNotifyList = userToNotifyRepository.findByCoordinatesAndLessAirTemperature(airData.getCoordinates(), airData.getAirTemperature());
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndMoreAirTemperature(airData.getCoordinates(), airData.getAirTemperature()));
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndLessAirHumidity(airData.getCoordinates(), airData.getAirHumidity()));
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndMoreAirHumidity(airData.getCoordinates(), airData.getAirHumidity()));
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndLessAirC02(airData.getCoordinates(), airData.getAirC02()));
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndMoreAirC02(airData.getCoordinates(), airData.getAirC02()));
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndLessAirVOCs(airData.getCoordinates(), airData.getAirVOCs()));
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndMoreAirVOCs(airData.getCoordinates(), airData.getAirVOCs()));
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndLessAirPM25(airData.getCoordinates(), airData.getAirPM25()));
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndMoreAirPM25(airData.getCoordinates(), airData.getAirPM25()));
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndLessAirC0(airData.getCoordinates(), airData.getAirC0()));
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndMoreAirC0(airData.getCoordinates(), airData.getAirC0()));

        List<String> emails = userToNotifyList.stream()
                .map(UserToNotify::getEmail).toList();

        AirQueueNotifyUsersDto airQueueNotifyUsersDto = new AirQueueNotifyUsersDto();
        airQueueNotifyUsersDto.setAirData(mapAirDataToAirQueueDataDto(airData));
        airQueueNotifyUsersDto.setEmails(emails);

        if(!emails.isEmpty()){
            rabbitTemplate.convertAndSend(exchange,routingKey,airQueueNotifyUsersDto);
        }

    }

    public AirQueueDataDto mapAirDataToAirQueueDataDto(AirData airData){
        return AirQueueDataDto.builder()
                .airTemperature(airData.getAirTemperature())
                .airHumidity(airData.getAirHumidity())
                .airC02(airData.getAirC02())
                .airVOCs(airData.getAirVOCs())
                .airPM25(airData.getAirPM25())
                .airC0(airData.getAirC0())
                .timestamp(airData.getTimestamp().toString())
                .coordinates(airData.getCoordinates())
                .build();
    }

    private UserToNotify mapUserToNotifyDtoToUserToNotify(UserToNotifyDto userToNotifyDto){
        return UserToNotify.builder()
                .email(userToNotifyDto.getEmail())
                .coordinates(userToNotifyDto.getCoordinates())
                .lessAirTemperature(userToNotifyDto.getLessAirTemperature())
                .moreAirTemperature(userToNotifyDto.getMoreAirTemperature())
                .lessAirHumidity(userToNotifyDto.getLessAirHumidity())
                .moreAirHumidity(userToNotifyDto.getMoreAirHumidity())
                .lessAirC02(userToNotifyDto.getLessAirC02())
                .moreAirC02(userToNotifyDto.getMoreAirC02())
                .lessAirVOCs(userToNotifyDto.getLessAirVOCs())
                .moreAirVOCs(userToNotifyDto.getMoreAirVOCs())
                .lessAirPM25(userToNotifyDto.getLessAirPM25())
                .moreAirPM25(userToNotifyDto.getMoreAirPM25())
                .lessAirC0(userToNotifyDto.getLessAirC0())
                .moreAirC0(userToNotifyDto.getMoreAirC0())
                .build();
    }



}
