package gr.vasilakos.air.airmicroservice.controller;

import gr.vasilakos.air.airmicroservice.dto.UserToNotifyDto;
import gr.vasilakos.air.airmicroservice.service.UserToNotifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/air")
@RequiredArgsConstructor
public class UserToNotifyController {

    private final UserToNotifyService userToNotifyService;

    @PostMapping("/notify")
    public ResponseEntity<UserToNotifyDto> userSubToAirData(@RequestBody UserToNotifyDto userToNotifyDto){
        return new ResponseEntity<>(userToNotifyService.userSubToAirData(userToNotifyDto), HttpStatus.OK);
    }

    @DeleteMapping ("/notify")
    public void userSubToAirData(@RequestParam String email){
        userToNotifyService.deleteUser(email);
    }
}
