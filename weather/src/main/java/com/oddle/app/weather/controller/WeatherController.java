package com.oddle.app.weather.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.oddle.app.weather.model.City;
import com.oddle.app.weather.model.Response;
import com.oddle.app.weather.service.WeatherService;

@RestController
public class WeatherController {
	Response response = new Response();
	
	@Autowired
	private WeatherService weatherService = new WeatherService();

    @GetMapping("/weather")
    public List<City> getWeather(@RequestParam(value="cityName") final String cityName) {
    	return weatherService.findCurrentWeatherByCity(cityName);  
    }
    
    @PostMapping("/weather")
    public ResponseEntity<String> addWeather(@RequestBody City city) {
    	weatherService.addWeatherByCity(city);
    	return response.customResponse(HttpStatus.CREATED, "HTTP Status will be CREATED (CODE 201)\n");
    }
    
    @PutMapping("/weather/{city_id}")
    public ResponseEntity<String> updateWeather(@PathVariable(value = "city_id") final Integer cityId,  @RequestBody final City city) {
        weatherService.updateWeatherByCity(weatherService.setUpdatedCityInfo(weatherService.findWeatherByCityId(cityId), city));
    	return response.customResponse(HttpStatus.NO_CONTENT, "HTTP Status will be NO_CONTENT (CODE 204)\n");    	
    }
    
    @DeleteMapping("/weather/{city_id}")
    public ResponseEntity<String> deleteWeather(@PathVariable(value = "city_id") final Integer cityId) {
        City byCityId = weatherService.findWeatherByCityId(cityId);	
        weatherService.delete(byCityId);
     	return response.customResponse(HttpStatus.OK, "HTTP Status will be ACCEPTED (CODE 200)\n");    	
    }  
}