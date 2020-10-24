package com.francislainy.gatling_tool.service.impl.stats;

import com.francislainy.gatling_tool.dto.stats.*;
import com.francislainy.gatling_tool.model.entity.stats.StatsEntity;
import com.francislainy.gatling_tool.repository.stats.StatsRepository;
import com.francislainy.gatling_tool.service.stats.StatsQueryService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StatsQueryServiceImpl implements StatsQueryService {

    @Autowired
    private final StatsRepository statsRepository;

    public StatsQueryServiceImpl(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    @Override
    public Stats getStats(UUID id) {

        if (statsRepository.findById(id).isPresent()) {
            StatsEntity statsEntity = statsRepository.findById(id).get();

            Stats stats = populateStats(statsEntity);


            return stats;
        } else {
            return null;
        }
    }


    @Override
    public List<Stats> listAllStats() {

        List<Stats> statsList = new ArrayList<>();

        statsRepository.findAll().forEach(statsEntity -> {

            Stats stats = populateStats(statsEntity);


            statsList.add(stats);
        });

        return statsList;
    }


    @Override
    public List<Stats> listAllStatsByReport(UUID id) {


        List<Stats> statsList = new ArrayList<>();

        statsRepository.findByReportId(id).forEach(statsEntity -> {

            Stats stats = populateStats(statsEntity);


            statsList.add(stats);

        });

        return statsList;
    }


    private Stats populateStats(StatsEntity statsEntity) {

        Stats stats = new Stats();
        stats.setId(statsEntity.getId());

        stats.setReportId(statsEntity.getReportId());

        Group1 group1 = new Group1();
        group1.setCount(statsEntity.getGroup1Count());
        group1.setName(statsEntity.getGroup1Name());
        group1.setPercentage(statsEntity.getGroup1Percentage());

        stats.setGroup1(group1);

        Group2 group2 = new Group2();
        group2.setCount(statsEntity.getGroup2Count());
        group2.setName(statsEntity.getGroup2Name());
        group2.setPercentage(statsEntity.getGroup2Percentage());

        stats.setGroup2(group2);

        Group3 group3 = new Group3();
        group3.setCount(statsEntity.getGroup3Count());
        group3.setName(statsEntity.getGroup3Name());
        group3.setPercentage(statsEntity.getGroup3Percentage());

        stats.setGroup3(group3);

        Group4 group4 = new Group4();
        group4.setCount(statsEntity.getGroup4Count());
        group4.setName(statsEntity.getGroup4Name());
        group4.setPercentage(statsEntity.getGroup4Percentage());

        stats.setGroup4(group4);

        MaxResponseTime maxResponseTime = new MaxResponseTime();
        maxResponseTime.setKo(statsEntity.getMaxResponseTimeKo());
        maxResponseTime.setOk(statsEntity.getMaxResponseTimeOk());
        maxResponseTime.setTotal(statsEntity.getMaxResponseTimeTotal());

        stats.setMaxResponseTime(maxResponseTime);

        MeanNumberOfRequestsPerSecond meanNumberOfRequestsPerSecond = new MeanNumberOfRequestsPerSecond();
        meanNumberOfRequestsPerSecond.setKo(statsEntity.getMeanResponseTimeKo());
        meanNumberOfRequestsPerSecond.setOk(statsEntity.getMeanResponseTimeOk());
        meanNumberOfRequestsPerSecond.setTotal(statsEntity.getMeanResponseTimeTotal());

        stats.setMeanNumberOfRequestsPerSecond(meanNumberOfRequestsPerSecond);

        MeanResponseTime meanResponseTime = new MeanResponseTime();
        meanResponseTime.setKo(statsEntity.getMeanResponseTimeKo());
        meanResponseTime.setOk(statsEntity.getMeanResponseTimeOk());
        meanResponseTime.setTotal(statsEntity.getMeanResponseTimeTotal());

        stats.setMeanResponseTime(meanResponseTime);


        MinResponseTime minResponseTime = new MinResponseTime();
        minResponseTime.setKo(statsEntity.getMinResponseTimeKo());
        minResponseTime.setOk(statsEntity.getMinResponseTimeOk());
        minResponseTime.setTotal(statsEntity.getMinResponseTimeTotal());

        stats.setMinResponseTime(minResponseTime);

        stats.setName(statsEntity.getName());

        NumberOfRequests numberOfRequests = new NumberOfRequests();
        numberOfRequests.setKo(statsEntity.getNumberOfRequestsKo());
        numberOfRequests.setOk(statsEntity.getNumberOfRequestOk());
        numberOfRequests.setTotal(statsEntity.getNumberOfRequestsTotal());

        stats.setNumberOfRequests(numberOfRequests);

        Percentiles1 percentiles1 = new Percentiles1();
        percentiles1.setKo(statsEntity.getPercentiles1Ko());
        percentiles1.setOk(statsEntity.getPercentiles1Ok());
        percentiles1.setTotal(statsEntity.getPercentiles1Total());

        stats.setPercentiles1(percentiles1);

        Percentiles2 percentiles2 = new Percentiles2();
        percentiles2.setKo(statsEntity.getPercentiles2Ko());
        percentiles2.setOk(statsEntity.getPercentiles2Ok());
        percentiles2.setTotal(statsEntity.getPercentiles2Total());

        stats.setPercentiles2(percentiles2);

        Percentiles3 percentiles3 = new Percentiles3();
        percentiles3.setKo(statsEntity.getPercentiles3Ko());
        percentiles3.setOk(statsEntity.getPercentiles3Ok());
        percentiles3.setTotal(statsEntity.getPercentiles3Total());

        stats.setPercentiles3(percentiles3);

        Percentiles4 percentiles4 = new Percentiles4();
        percentiles4.setKo(statsEntity.getPercentiles4Ko());
        percentiles4.setOk(statsEntity.getPercentiles4Ok());
        percentiles4.setTotal(statsEntity.getPercentiles4Total());

        stats.setPercentiles4(percentiles4);

        StandardDeviation standardDeviation = new StandardDeviation();
        standardDeviation.setKo(statsEntity.getStandardDeviationKo());
        standardDeviation.setOk(statsEntity.getStandardDeviationOk());
        standardDeviation.setTotal(statsEntity.getStandardDeviationTotal());

        stats.setStandardDeviation(standardDeviation);

        stats.setEndpoint(statsEntity.getEndpoint());

        return stats;
    }
}
