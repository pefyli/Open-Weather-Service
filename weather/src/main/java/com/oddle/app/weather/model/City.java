package com.oddle.app.weather.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "city")
public class City {
	/*
	 * {"coord":{"lon":-0.1257,"lat":51.5085}
	 * ,"weather":[{"id":803,"main":"Clouds","description":"broken clouds","icon":
	 * "04d"}],
	 * "base":"stations","main":{"temp":280.55,"feels_like":278.48,"temp_min":278.
	 * 76, "temp_max":282.73,"pressure":1023,"humidity":57},"visibility":10000,
	 * "wind":{"speed":3.09,"deg":0},"clouds":{"all":75},"dt":1648896065,
	 * "sys":{"type":2,"id":268730,"country":"GB","sunrise":1648877626,"sunset":
	 * 1648924448}, "timezone":3600,"id":2643743,"name":"London","cod":200}
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "city_id")
	private Integer cityId;

	@Column(name = "longitude", nullable = true)
	private Float longitude;

	@Column(name = "latitude", nullable = true)
	private Float latitude;

	@Column(name = "base", nullable = true)
	private String base;

	@Column(name = "visibility", nullable = true)
	private Integer visibility;

	@Column(name = "dt", nullable = true)
	private Integer dt;

	@Column(name = "time_zone", nullable = true)
	private Integer timeZone;

	@Column(name = "name", nullable = true)
	private String name;

	@Column(name = "cod", nullable = true)
	private Integer cod;

	@Column(name = "created_date_time", nullable = false)
	private String createDateTime;

	@Column(name = "updated_date_time", nullable = true)
	private String updateDateTime;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "weather_id", referencedColumnName = "id")
	private Weather weather;
	
	public City() {
	}

	public City(String name, Float longitude, Float latitude, String base, Integer visbility,
			Integer timeZone, Integer cod, String createDateTime, String updateDateTime, Weather weather) {
		setName(name);
		setLongitude(longitude);
		setLatitude(latitude);
		setBase(base);
		setVisibility(visbility);
		setTimeZone(timeZone);
		setCod(cod);
		setCreateDateTime(createDateTime);
		setUpdateDateTime(updateDateTime);
		setWeather(weather);
	}
	
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Float getLongitude() {
		return this.longitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLatitude() {
		return this.latitude;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getCityId() {
		return this.cityId;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getBase() {
		return this.base;
	}

	public void setDt(Integer dt) {
		this.dt = dt;
	}

	public Integer getDt() {
		return this.dt;
	}

	public void setTimeZone(Integer timeZone) {
		this.timeZone = timeZone;
	}

	public Integer getTimeZone() {
		return this.timeZone;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setVisibility(Integer visibility) {
		this.visibility = visibility;
	}

	public Integer getVisibility() {
		return this.visibility;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public Integer getCod() {
		return this.cod;
	}

	public String getCreateDateTime() {
		return this.createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getUpdateDateTime() {
		return this.updateDateTime;
	}

	public void setUpdateDateTime(String updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	
	public void setWeather(Weather weather) {
		this.weather = weather;
	}

	public Weather getWeather() {
		return this.weather;
	}
}
