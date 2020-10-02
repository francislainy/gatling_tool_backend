package com.francislainy.gatling_tool.dto.stats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.UUID;

@Data
public class Stats {

    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("stats")
    @Expose
    public Stats_ stats;

    public Stats(String name) {
        this.name = name;
    }
}
