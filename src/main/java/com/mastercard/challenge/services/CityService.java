package com.mastercard.challenge.services;

import com.mastercard.challenge.utils.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

@Service
public class CityService {

    @Autowired
    ResourceLoader resourceLoader;
    Resource resource;
    InputStream inputStream;
    HashMap<String,Integer> cityMapping = new HashMap<>();
    Integer cityNumber=0;
    Graph g;

    public void loadRoadsFile() throws IOException {
        resource = resourceLoader.getResource("classpath:static/city.txt");
        inputStream = resource.getInputStream();
        try ( BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream)) ) {
            String road = reader.readLine();
            while (road!=null){
                String [] cities = road.split(",");
                for ( String city : cities) {
                    if(!cityMapping.containsKey(city.trim())) {
                        cityMapping.put(city.trim(), cityNumber);
                        cityNumber++;
                    }
                }
                road = reader.readLine();
            }
        }
        cityMapping.forEach((key, value) -> System.out.println(key + ":" + value));
    }

    public void createGraph() throws IOException {
        inputStream = resource.getInputStream();
        g = new Graph(cityMapping.size());
        try ( BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream)) ) {
            String road = reader.readLine();
            while (road!=null){
                String [] cities = road.split(",");
                Integer originCityNumber = cityMapping.get(cities[0].trim());
                Integer destinationCityNumber= cityMapping.get(cities[1].trim());
                g.addEdge(originCityNumber,destinationCityNumber);
                g.addEdge(destinationCityNumber,originCityNumber);
                road = reader.readLine();
            }
        }
    }
    public boolean areCitiesConnected(String origin, String destination){
        if(cityMapping.containsKey((origin.trim()))){
            Integer originCityNumber = cityMapping.get(origin.trim());
            if(cityMapping.containsKey(destination.trim())){
                Integer destinationCityNumber= cityMapping.get(destination.trim());
                return g.isConnected(originCityNumber,destinationCityNumber);
            }
        }
        return  false;
    }
}
