package com.oddle.app.weather.model;

import javax.persistence.*;

@Entity
@Table(name = "weather")
public class Weather {
 /*"weather":[{"id":803,"main":"Clouds","description":"broken clouds","icon":"04d"}]*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer weatherId;
	
	@Column(name = "main", nullable = true)
	private String main;
	
	@Column(name = "description", nullable = true)
	private String description;
	
	@Column(name = "icon", nullable = true)
	private String icon;
	
	@Column(name = "created_date_time", nullable = false)
	private String createDateTime; 
	
	@Column(name = "updated_date_time", nullable = true)
	private String updateDateTime; 
	
	public Weather() {}
	
    public Weather(String main, String description, String icon, String createDateTime, String updateDateTime) {
    	setMain(main);
    	setDescription(description);
    	setIcon(icon);
    	setCreateDateTime(createDateTime);
    	setUpdateDateTime(updateDateTime);
    }
	
	public void setWeatherId(Integer weatherId) {
		this.weatherId = weatherId;
	}
	
	public Integer getWeatherId() {
		return this.weatherId;
	}
	
	public void setMain(String main) {
		this.main = main;
	}
	
	public String getMain() {
		return this.main;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public String getIcon() {
		return this.icon;
	}
	
	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}
	
	public String getCreateDateTime() {
		return this.createDateTime;
	}
	
	public void setUpdateDateTime(String updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	
	public String getUpdateDateTime() {
		return this.updateDateTime;
	}
}
