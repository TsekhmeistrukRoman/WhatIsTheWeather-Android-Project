package tsekhmeistruk.whatistheweather.models.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherForecast {

    @SerializedName("city")
    @Expose
    private City city;
    @SerializedName("cod")
    @Expose
    private String code;
    @SerializedName("message")
    @Expose
    private Double message;
    @SerializedName("cnt")
    @Expose
    private Integer daysNumber;
    @SerializedName("list")
    @Expose
    private List<MainWeatherInfo> mainWeatherInfoList = null;

    public City getCity() {
        return city;
    }

    public String getCode() {
        return code;
    }

    public Double getMessage() {
        return message;
    }

    public Integer getDaysNumber() {
        return daysNumber;
    }

    public List<MainWeatherInfo> getList() {
        return mainWeatherInfoList;
    }

}
