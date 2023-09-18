package com.openweathermap.service;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.openweather.vo.WeatherInfo;

@Service
public class WeatherInfoServiceImpl implements WeatherInfoService {
	
	private static final Logger logger = LoggerFactory.getLogger(WeatherInfoServiceImpl.class);

	@Override
	public Map<String, WeatherInfo> getWeatherInfoData(JSONArray response) {

		Map<String, WeatherInfo> weather = new HashMap<String,WeatherInfo>();

		try {
			for (int i = 0; i < response.length(); i++) {
				JSONObject jsonObject = response.getJSONObject(i);
				String summary = "";
				JSONObject mainObject = jsonObject.getJSONObject("main");
				
				if(Double.compare(mainObject.getDouble("temp_max"),40.0) > 0)
					summary = "Use sunscreen lotion ";

//				if(mainObject.getBoolean("willRain"))
//					summary += "Carry umbrella";
				
				String dateTimeText  = jsonObject.getString("dt_txt");
				String dateTime = dateTimeText.substring(0, 10);
				WeatherInfo weatherInfo = new WeatherInfo();
				
				if(weatherInfo.getMinTemp()==Double.NaN) {
					weatherInfo.setMinTemp(mainObject.getDouble("temp_min"));
				}else if(Double.compare(weatherInfo.getMinTemp(),mainObject.getDouble("temp_min")) > 0) {
					weatherInfo.setMinTemp(mainObject.getDouble("temp_min"));
				}
				
				if(weatherInfo.getMaxTemp()==Double.NaN) {
					weatherInfo.setMaxTemp(mainObject.getDouble("temp_max"));
				}else if(Double.compare(weatherInfo.getMaxTemp(),mainObject.getDouble("temp_max")) < 0) {
					weatherInfo.setMaxTemp(mainObject.getDouble("temp_max"));
				}
				weatherInfo.setSummary(summary);
				weatherInfo.setTimeStamp(dateTime);
				//weatherInfo.setWillRain(mainObject.getBoolean("willRain"));
				weather.put(dateTime, weatherInfo);

			}

		} catch (JSONException e) {
			logger.error("Exception occured in getting Weather Info",e.getStackTrace());

		} catch (Exception e) {
			logger.error("Exception occured in getting Weather Info",e.getStackTrace());
		}

		return weather;
	}

}
