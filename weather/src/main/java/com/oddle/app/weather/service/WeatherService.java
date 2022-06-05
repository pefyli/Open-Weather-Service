package com.oddle.app.weather.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;  
import com.oddle.app.weather.constant.Constant;
import com.oddle.app.weather.dao.CityDAO;
import com.oddle.app.weather.dao.WeatherDAO;
import com.oddle.app.weather.model.City;
import com.oddle.app.weather.model.Weather;
import com.oddle.app.weather.util.UtilService;

@Service
public class WeatherService {
	Logger logger = LoggerFactory.logger(WeatherService.class);

	@Autowired
	@Resource
	CityDAO cityDAO;
	
	@Autowired
	@Resource
	WeatherDAO weatherDAO;

	public City findWeatherByCityId(final Integer cityId) {
		City byCityId = cityDAO.findWeatherByCityId(cityId);
		return byCityId;
	}

	public List<City> findCurrentWeatherByCity(final String city) {
		logger.info("city name: " + city);
		List<City> byCity = cityDAO.findCurrentWeatherByCity(city);
		if (byCity.isEmpty()) {
			logger.info("Don't have data for your requested city.");
		} else {
			logger.info("Find " + byCity.size() + " records");
		}
		return byCity;
	}

	public void addWeatherByCity(City city) {
		logger.info("create city name: " + city.getName());
		city.setCreateDateTime(UtilService.getCurrentTime());
		city.setUpdateDateTime(null);		
		cityDAO.save(city);
	}

	public void updateWeatherByCity(final City city) {
		logger.info("update city id: " + city.getCityId());	
		cityDAO.save(city);
	}

	public void delete(final City city) {
		logger.info("Delete city id: " + city.getCityId());
		cityDAO.delete(city);
	}

	public List<City> search(final String fromDateTime, final String toDateTime) {
		logger.info("Search for weather data from " + fromDateTime + " to " + toDateTime);
		return cityDAO.searchRangeData(fromDateTime, toDateTime);
	}
	
	public City searchWeaterForTodayByCity(final String cityName){
		logger.info("Search for today's weather data for " + cityName);
		City city = new City();
		Weather weather = new Weather();
		
        String uri = Constant.OPEN_WEATHER_API + cityName + "&appid=" + Constant.API_KEY;
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(uri, String.class);
        logger.info("open weather api response: " + response);
        
        JSONObject reponseObj = new JSONObject(response);
        JSONObject weatherObj = new JSONObject(reponseObj.getJSONArray(Constant.WEATHER).get(0).toString());
        
        weather.setMain(weatherObj.getString(Constant.MAIN));
        weather.setDescription(weatherObj.getString(Constant.DESCRIPTION));
        weather.setWeatherId(weatherObj.getInt(Constant.ID));
        weather.setIcon(weatherObj.getString(Constant.ICON));
        
        city.setCityId(reponseObj.getInt(Constant.ID));
		city.setBase(reponseObj.getString(Constant.BASE));
		city.setCod(reponseObj.getInt(Constant.COD));
		city.setDt(reponseObj.getInt(Constant.DT));
		city.setLatitude(reponseObj.getJSONObject(Constant.COORD).getFloat(Constant.LAT));
		city.setLongitude(reponseObj.getJSONObject(Constant.COORD).getFloat(Constant.LON));
		city.setName(reponseObj.getString(Constant.NAME));
		city.setTimeZone(reponseObj.getInt(Constant.TIMEZONE));
		city.setVisibility(reponseObj.getInt(Constant.VISIBILITY));
		city.setWeather(weather);
        
        return city;
	}
	
	public City setUpdatedCityInfo(City city, final City updatedCity) {		  
		city.setBase(updatedCity.getBase());
		city.setCod(updatedCity.getCod());
		city.setUpdateDateTime(UtilService.getCurrentTime());
		city.setDt(updatedCity.getDt());
		city.setLatitude(updatedCity.getLatitude());
		city.setLongitude(updatedCity.getLongitude());
		city.setName(updatedCity.getName());
		city.setTimeZone(updatedCity.getTimeZone());
		city.setVisibility(updatedCity.getVisibility());
		city.setWeather(updatedCity.getWeather());
		return city;
	}
}
