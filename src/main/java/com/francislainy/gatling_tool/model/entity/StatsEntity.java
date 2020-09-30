package com.francislainy.gatling_tool.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "stats")
public class StatsEntity {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "number_of_requests_total")
    private int numberOfRequestsTotal;

    @Column(name = "number_of_requests_ok")
    private int numberOfRequestOk;

    @Column(name = "number_of_requests_ko")
    private int numberOfRequestsKo;

    @Column(name = "min_response_time_total")
    private int minResponseTimeTotal;

    @Column(name = "min_response_time_ok")
    private int minResponseTimeOk;

    @Column(name = "min_response_time_ko")
    private int minResponseTimeKo;

    @Column(name = "max_response_time_total")
    private int maxResponseTimeTotal;

    @Column(name = "max_response_time_ok")
    private int maxResponseTimeOk;

    @Column(name = "max_response_time_ko")
    private int maxResponseTimeKo;

    @Column(name = "mean_response_time_total")
    private int meanResponseTimeTotal;

    @Column(name = "mean_response_time_ok")
    private int meanResponseTimeOk;

    @Column(name = "mean_response_time_ko")
    private int meanResponseTimeKo;

    @Column(name = "standard_deviation_total")
    private int standardDeviationTotal;

    @Column(name = "standard_deviation_ok")
    private int standardDeviationOk;

    @Column(name = "standard_deviation_ko")
    private int standardDeviationKo;

    @Column(name = "percentiles1_total")
    private int percentiles1Total;

    @Column(name = "percentiles1_ok")
    private int percentiles1Ok;

    @Column(name = "percentiles1_ko")
    private int percentiles1Ko;

    @Column(name = "percentiles2_total")
    private int percentiles2Total;

    @Column(name = "percentiles2_ok")
    private int percentiles2Ok;

    @Column(name = "percentiles2_ko")
    private int percentiles2Ko;

    @Column(name = "percentiles3_total")
    private int percentiles3Total;

    @Column(name = "percentiles3_ok")
    private int percentiles3Ok;

    @Column(name = "percentiles3_ko")
    private int percentiles3Ko;

    @Column(name = "percentiles4_total")
    private int percentiles4Total;

    @Column(name = "percentiles4_ok")
    private int percentiles4Ok;

    @Column(name = "percentiles4_ko")
    private int percentiles4Ko;

    @Column(name = "group1_name")
    private String group1Name;

    @Column(name = "group1_count")
    private int group1Count;

    @Column(name = "group1_percentage")
    private int group1Percentage;

    @Column(name = "group2_name")
    private String group2Name;

    @Column(name = "group2_count")
    private int group2Count;

    @Column(name = "group2_percentage")
    private int group2Percentage;

    @Column(name = "group3_name")
    private String group3Name;

    @Column(name = "group3_count")
    private int group3Count;

    @Column(name = "group3_percentage")
    private int group3Percentage;

    @Column(name = "group4_name")
    private String group4Name;

    @Column(name = "group4_count")
    private int group4Count;

    @Column(name = "group4_percentage")
    private int group4Percentage;

    @Column(name = "mean_number_of_requests_per_second_total")
    private double meanNumberOfRequestsPerSecondTotal;

    @Column(name = "mean_number_of_requests_per_second_ok")
    private double meanNumberOfRequestsPerSecondOk;

    @Column(name = "mean_number_of_requests_per_second_ko")
    private double meanNumberOfRequestsPerSecondKo;

    @Column(name = "category_id")
    private int categoryId;
}
