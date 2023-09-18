package com.openweathermap.service;

import java.util.Map;

import org.json.JSONArray;

import com.openweather.vo.WeatherInfo;

public interface WeatherInfoService {

	public abstract Map<String, WeatherInfo> getWeatherInfoData(JSONArray response);
}
