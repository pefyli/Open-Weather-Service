package com.oddle.app.weather.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oddle.app.weather.model.City;
import com.oddle.app.weather.model.Response;
import com.oddle.app.weather.service.WeatherService;

@RestController
public class SearchWeatherController {
	@Autowired
	private WeatherService weatherService = new WeatherService();
	Response response = new Response();
	
    @GetMapping("/weather/search")
    public List<City> searchWeather(@RequestParam(value="fromDateTime") final String fromDateTime, @RequestParam(value="toDateTime") final String toDateTime) {
		return weatherService.search(fromDateTime, toDateTime);
    }
    
    @GetMapping("/weather/search/city")
    public City searchWeaterForTodayByCity(@RequestParam(value="cityName") final String cityName){
        return weatherService.searchWeaterForTodayByCity(cityName);
    }
}
