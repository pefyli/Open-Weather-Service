package com.oddle.app.weather;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

import com.oddle.app.weather.constant.Constant;


@SpringBootTest
public class WeatherApplicationRequestTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void sequentialRequestTest() throws Exception {
		getTodayWeatherTest();
		addWeatherTest();
		int cityId = getWeatherTest();		
		updateWeatherTest(cityId);
		deleteWeatherTest(cityId);
		searchWeatherTest();
	}

	@SuppressWarnings("deprecation")
	public void getTodayWeatherTest() throws Exception {
		boolean isPassed = false;
		MvcResult mvcResult = mockMvc.perform(get("/weather/search/city?cityName=London")).andReturn();
		int status = mvcResult.getResponse().getStatus();
		if (status == 200)
			isPassed = true;
		Assert.isTrue(isPassed);;
	}
	
	public int getWeatherTest() throws Exception {
		mockMvc.perform(get("/weather?cityName=Paris")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json")).andExpect(jsonPath("$[0].name", is("Paris")));
	    int cityId = 0;
		String response = mockMvc.perform(get("/weather?cityName=Paris")).andReturn().getResponse().getContentAsString();
		JSONArray responseArr = new JSONArray(response);
		for(int i=0; i<responseArr.length(); i++) {
			JSONObject responseObj = new JSONObject(responseArr.get(i).toString());
			cityId = responseObj.getInt(Constant.CITY_ID);

		}
		return cityId;
	}

	public void updateWeatherTest(int cityId) throws Exception {
		String updateJson = 
				"{\r\n" + 
				"\"longitude\": 40.0,\r\n" + 
				"\"latitude\": 52,\r\n" + 
				"\"base\": \"stations\",\r\n" + 
				"\"visibility\": 10000,\r\n" + 
				"\"dt\": 1648970677,\r\n" + 
				"\"timeZone\": 3600,\r\n" + 
				"\"name\": \"Paris\",\r\n" + 
				"\"cod\": 200,\r\n" + 
				"\"createDateTime\": \"2022-04-03 15:00:00\",\r\n" + 
				"\"updateDateTime\": \"2022-04-03 15:39:00\",\r\n" + 
				"\"weather\": {\r\n" + 
				"\"weatherId\": 1,\r\n" + 
				"\"main\": \"Clouds\",\r\n" + 
				"\"description\": \"few clouds\",\r\n" + 
				"\"icon\": \"02d\",\r\n" + 
				"\"createDateTime\": \"2022-04-02 00:00:00\",\r\n" + 
				"\"updateDateTime\": \"2022-04-03 15:37:00\"\r\n" + 
				"}\r\n" + 
				"}";
		mockMvc.perform(put("/weather/" + cityId).contentType("application/json").content(updateJson)).andReturn();
		mockMvc.perform(get("/weather?cityName=Paris")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json")).andExpect(jsonPath("$[0].longitude", is(40.0)));

	}

	@SuppressWarnings("deprecation")
	public void addWeatherTest() throws Exception {
		boolean isPassed = false;
		String inputJson = 
				"{\r\n" + 
				"\"longitude\": 20,\r\n" + 
				"\"latitude\": 52,\r\n" + 
				"\"base\": \"stations\",\r\n" + 
				"\"visibility\": 10000,\r\n" + 
				"\"dt\": 1648970677,\r\n" + 
				"\"timeZone\": 3600,\r\n" + 
				"\"name\": \"Paris\",\r\n" + 
				"\"cod\": 200,\r\n" + 
				"\"createDateTime\": \"2022-04-03 15:00:00\",\r\n" + 
				"\"updateDateTime\": \"2022-04-03 15:39:00\",\r\n" + 
				"\"weather\": {\r\n" + 
				"\"main\": \"Clouds\",\r\n" + 
				"\"description\": \"few clouds\",\r\n" + 
				"\"icon\": \"02d\",\r\n" + 
				"\"createDateTime\": \"2022-04-02 00:00:00\",\r\n" + 
				"\"updateDateTime\": \"2022-04-03 15:37:00\"\r\n" + 
				"}\r\n" + 
				"}";
		MvcResult mvcResult = mockMvc.perform(post("/weather").contentType("application/json").content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		if (status == 201)
			isPassed = true;
		Assert.isTrue(isPassed);
	}

	@SuppressWarnings("deprecation")
	public void deleteWeatherTest(int cityId) throws Exception {
		boolean isPassed = false;
		MvcResult mvcResult = mockMvc.perform(delete("/weather/" + cityId)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		if (status == 200)
			isPassed = true;
		Assert.isTrue(isPassed);
	}
	
	@SuppressWarnings("deprecation")
	public void searchWeatherTest() throws Exception {
		String fromDateTime = "2022-04-01 15:00:00";
		String toDateTime = "2022-04-04 15:00:00";
		String uri = "/weather/search?fromDateTime=" + fromDateTime + "&toDateTime=" + toDateTime;
		MvcResult mvcResult = mockMvc.perform(get(uri)).andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		JSONArray jsonArray =  new JSONArray(response);
		Assert.notNull(jsonArray);
	}
}
