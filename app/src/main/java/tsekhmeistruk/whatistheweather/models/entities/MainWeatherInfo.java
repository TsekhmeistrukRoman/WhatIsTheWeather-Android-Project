package tsekhmeistruk.whatistheweather.models.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainWeatherInfo {

    @SerializedName("dt")
    @Expose
    private Integer time;
    @SerializedName("temp")
    @Expose
    private Temperature temperature;
    @SerializedName("pressure")
    @Expose
    private Double pressure;
    @SerializedName("humidity")
    @Expose
    private Integer humidity;
    @SerializedName("weather")
    @Expose
    private java.util.List<Weather> weather = null;
    @SerializedName("speed")
    @Expose
    private Double speed;
    @SerializedName("deg")
    @Expose
    private Integer degrees;
    @SerializedName("clouds")
    @Expose
    private Integer clouds;
    @SerializedName("rain")
    @Expose
    private Double rain;
    @SerializedName("snow")
    @Expose
    private Double snow;

    public Integer getTime() {
        return time;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public Double getPressure() {
        return pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public java.util.List<Weather> getWeather() {
        return weather;
    }

    public Double getSpeed() {
        return speed;
    }

    public Integer getDegrees() {
        return degrees;
    }

    public Integer getClouds() {
        return clouds;
    }

    public Double getRain() {
        return rain;
    }

    public Double getSnow() {
        return snow;
    }

}
