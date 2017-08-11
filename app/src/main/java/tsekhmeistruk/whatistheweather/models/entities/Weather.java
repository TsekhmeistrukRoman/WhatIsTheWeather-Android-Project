package tsekhmeistruk.whatistheweather.models.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Weather implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("main")
    @Expose
    private String mainInformation;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("icon")
    @Expose
    private String icon;

    public Integer getId() {
        return id;
    }

    public String getMainInformation() {
        return mainInformation;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

}
