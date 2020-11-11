package com.francislainy.gatling_tool.debug.model_manual;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class MeanNumberOfRequestsPerSecond {

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
