package gr.vasilakos.air.airmicroservice.controller;

import gr.vasilakos.air.airmicroservice.dto.AirDataDto;
import gr.vasilakos.air.airmicroservice.service.AirDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/air")
@RequiredArgsConstructor
public class AirDataController {

    private final AirDataService airDataService;

    @GetMapping("/search-all")
    public ResponseEntity<List<AirDataDto>> getData(
            @RequestParam String location,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Instant start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Instant  end
    ){

        return new ResponseEntity<>(airDataService.findByLocationAndDates(location,start,end), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<AirDataDto> getData(
            @RequestParam String location
    ){
        return new ResponseEntity<>(airDataService.findLastEntry(location), HttpStatus.OK);
    }

}
