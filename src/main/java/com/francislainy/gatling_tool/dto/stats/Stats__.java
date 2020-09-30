package com.francislainy.gatling_tool.dto.stats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stats__ {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("numberOfRequests")
    @Expose
    public NumberOfRequests_ numberOfRequests;
    @SerializedName("minResponseTime")
    @Expose
    public MinResponseTime_ minResponseTime;
    @SerializedName("maxResponseTime")
    @Expose
    public MaxResponseTime_ maxResponseTime;
    @SerializedName("meanResponseTime")
    @Expose
    public MeanResponseTime_ meanResponseTime;
    @SerializedName("standardDeviation")
    @Expose
    public StandardDeviation_ standardDeviation;
    @SerializedName("percentiles1")
    @Expose
    public Percentiles1_ percentiles1;
    @SerializedName("percentiles2")
    @Expose
    public Percentiles2_ percentiles2;
    @SerializedName("percentiles3")
    @Expose
    public Percentiles3_ percentiles3;
    @SerializedName("percentiles4")
    @Expose
    public Percentiles4_ percentiles4;
    @SerializedName("group1")
    @Expose
    public Group1_ group1;
    @SerializedName("group2")
    @Expose
    public Group2_ group2;
    @SerializedName("group3")
    @Expose
    public Group3_ group3;
    @SerializedName("group4")
    @Expose
    public Group4_ group4;
    @SerializedName("meanNumberOfRequestsPerSecond")
    @Expose
    public MeanNumberOfRequestsPerSecond_ meanNumberOfRequestsPerSecond;

}
