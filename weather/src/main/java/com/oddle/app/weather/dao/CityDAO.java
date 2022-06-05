package com.oddle.app.weather.dao;

import com.oddle.app.weather.model.City;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface CityDAO extends JpaRepository<City, Integer>{

	@Query(value = "SELECT * FROM weather.city city JOIN weather.weather weather ON city.weather_id = weather.id  where name=:name", nativeQuery = true)
	List<City> findCurrentWeatherByCity(@Param("name") String city);
	
	@Query(value = "SELECT * FROM weather.city city JOIN weather.weather weather ON city.weather_id = weather.id  where city_id=:id", nativeQuery = true)
    City findWeatherByCityId(@Param("id") Integer id);

	List<City> findByName(String name);
	
	List<City> findByNameLike(String name);
	
	@Query(value = "SELECT * FROM weather.city WHERE created_date_time BETWEEN :fromDateTime AND :toDateTime", nativeQuery = true)
	List<City> searchRangeData(@Param("fromDateTime") String fromDateTime, @Param("toDateTime") String toDateTime);
}
