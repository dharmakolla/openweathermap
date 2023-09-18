package com.openweather.vo;

public class WeatherInfo{

	public double minTemp;
	
	public double maxTemp;
	
	public boolean willRain;
	
	public String timeStamp;
	
	public String summary;

	public double getMinTemp() {
		return minTemp;
	}

	public void setMinTemp(double minTemp) {
		this.minTemp = minTemp;
	}

	public double getMaxTemp() {
		return maxTemp;
	}

	public void setMaxTemp(double maxTemp) {
		this.maxTemp = maxTemp;
	}

	public boolean isWillRain() {
		return willRain;
	}

	public void setWillRain(boolean willRain) {
		this.willRain = willRain;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
}
