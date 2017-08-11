package tsekhmeistruk.whatistheweather.models.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Temperature implements Serializable {

    @SerializedName("day")
    @Expose
    private Double dayTemperature;
    @SerializedName("min")
    @Expose
    private Double minTemperature;
    @SerializedName("max")
    @Expose
    private Double maxTemperature;
    @SerializedName("night")
    @Expose
    private Double nightTemperature;
    @SerializedName("eve")
    @Expose
    private Double eveningTemperature;
    @SerializedName("morn")
    @Expose
    private Double morningTemperature;

    public Double getDayTemperature() {
        return dayTemperature;
    }

    public Double getMinTemperature() {
        return minTemperature;
    }

    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public Double getNightTemperature() {
        return nightTemperature;
    }

    public Double getEveningTemperature() {
        return eveningTemperature;
    }

    public Double getMorningTemperature() {
        return morningTemperature;
    }

}
