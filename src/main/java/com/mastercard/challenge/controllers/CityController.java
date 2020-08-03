package com.mastercard.challenge.controllers;

import com.mastercard.challenge.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Optional;

@RestController
public class CityController {

    @Autowired
    CityService cityService;

    @PostConstruct
    public void initializeGraph() throws IOException {
        cityService.loadRoadsFile();
        cityService.createGraph();
    }

    @GetMapping("/connected")
    public String index(@RequestParam Optional<String> origin, @RequestParam Optional<String> destination) throws IOException {
        if( origin.isPresent() && destination.isPresent()){
            if(cityService.areCitiesConnected(origin.get(),destination.get())){
                return "yes";
            }
        }
        return "no";
    }

}
