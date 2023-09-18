package com.openweathermap.controller;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.openweather.utils.Constants;
import com.openweather.vo.WeatherInfo;
import com.openweathermap.service.WeatherInfoService;

import jakarta.websocket.server.PathParam;



@RestController
public class OpenweathermapController {
	
	@Autowired
	WeatherInfoService weatherInfoService;
	
	@RequestMapping("/hello")
	public String hello() {
		return "Hello world";
	}
	
	@RequestMapping(value="/getWeatherData")
	public String getWeatherData(String cityName) {
		String uri="https://api.openweathermap.org/data/2.5/forecast?q=hyderabad&appid=d2929e9483efc82c82c32ee7e02d563e&cnt=10";
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		return result;	
	}
	
	@PostMapping(value="/getWeatherDataByCity")
	@ResponseBody
	public ResponseEntity<Object> getWeatherInfo(@RequestParam(value = "city") String city) {
		String uri="https://api.openweathermap.org/data/2.5/forecast?q="+city+"&appid=d2929e9483efc82c82c32ee7e02d563e&cnt=24";
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		JSONObject jsonObject = new JSONObject(result);
		JSONArray jsonResponse = new JSONArray(jsonObject.getJSONArray(Constants.LIST));
		Map<String, WeatherInfo> response = (Map<String, WeatherInfo>) weatherInfoService.getWeatherInfoData(jsonResponse);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
