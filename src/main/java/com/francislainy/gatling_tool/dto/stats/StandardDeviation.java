package com.francislainy.gatling_tool.dto.stats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StandardDeviation {

    @SerializedName("total")
    @Expose
    public Integer total;
    @SerializedName("ok")
    @Expose
    public Integer ok;
    @SerializedName("ko")
    @Expose
    public Integer ko;

}
