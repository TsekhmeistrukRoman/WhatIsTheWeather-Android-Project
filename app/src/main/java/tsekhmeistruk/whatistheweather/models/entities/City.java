package tsekhmeistruk.whatistheweather.models.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("coord")
    @Expose
    private Coordination coordination;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("population")
    @Expose
    private Integer population;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordination getCoordination() {
        return coordination;
    }

    public String getCountry() {
        return country;
    }

    public Integer getPopulation() {
        return population;
    }

}
