package com.francislainy.gatling_tool.dto.stats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReqGetNotificatioEaa9b {

    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("path")
    @Expose
    public String path;
    @SerializedName("pathFormatted")
    @Expose
    public String pathFormatted;
    @SerializedName("stats")
    @Expose
    public Stats____ stats;

}
