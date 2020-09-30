package com.francislainy.gatling_tool.dto.stats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MeanNumberOfRequestsPerSecond___ {

    @SerializedName("total")
    @Expose
    public Double total;
    @SerializedName("ok")
    @Expose
    public Double ok;
    @SerializedName("ko")
    @Expose
    public Integer ko;

}
