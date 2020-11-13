package com.francislainy.gatling_tool.helper;

import com.francislainy.gatling_tool.dto.stats.Group;
import com.francislainy.gatling_tool.dto.stats.ReqAuthorize;
import com.francislainy.gatling_tool.dto.stats.Stats;
import com.francislainy.gatling_tool.model.entity.report.Report;
import com.francislainy.gatling_tool.model.entity.stats.StatsEntity;
import com.google.gson.Gson;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

public class StatsJsonHelper {

    public static String TYPE = "application/json";

    public static boolean hasJsonFormat(MultipartFile file) {

        return TYPE.equals(file.getContentType());
    }


    public static List<StatsEntity> jsonToStatsList(MultipartFile file, UUID id) {

        try {

            File myFile = convertMultiPartToFile(file);
            Gson gson = new Gson();
            BufferedReader br = new BufferedReader(new FileReader(myFile));

            StatsEntity statsEntity;
            List<StatsEntity> statsEntityList = new ArrayList<>();

            Map map = gson.fromJson(br, Map.class);

            // Save global info to get global combined stats
            String globalStatsJson = gson.toJson(map.get("stats"));
            Stats stats = gson.fromJson(globalStatsJson, Stats.class);
            statsEntity = addStatsToDbEntity(stats, id);
            statsEntityList.add(statsEntity);


            // Save individual stats
            String contentsJson = gson.toJson(map.get("contents"));

            Map contentsMap = gson.fromJson(contentsJson, Map.class);
            MyLinkedMap myLinkedContentsMap = new MyLinkedMap(contentsMap);


            for (int a = 0; a < myLinkedContentsMap.size(); a++) {

                String jsonGroup = gson.toJson(myLinkedContentsMap.getValue(a));
                Map groupsMap = gson.fromJson(jsonGroup, Map.class);
                MyLinkedMap myLinkedGroupMap = new MyLinkedMap(groupsMap);

                Group group = gson.fromJson(jsonGroup, Group.class);

                if (myLinkedGroupMap.get("contents") != null) {

                    String contents_Json = gson.toJson(myLinkedGroupMap.get("contents"));
                    Map contents_group_Map = gson.fromJson(contents_Json, Map.class);

                    MyLinkedMap myLinkedMap = new MyLinkedMap(contents_group_Map);


                    for (int i = 0; i < contents_group_Map.size(); i++) {

                        String jsonReqAuthorize = gson.toJson(myLinkedMap.getValue(i));
                        ReqAuthorize reqAuthorize = gson.fromJson(jsonReqAuthorize, ReqAuthorize.class);

                        Stats st = reqAuthorize.stats;

                        statsEntity = addStatsToDbEntity(st, id);
                        statsEntityList.add(statsEntity);

                    }

                } else {

                    Stats st = group.stats;

                    statsEntity = addStatsToDbEntity(st, id);
                    statsEntityList.add(statsEntity);

                }

            }

            return statsEntityList;

        } catch (IOException e) {
            throw new RuntimeException("fail to parse json file: " + e.getMessage());
        }
    }


    private static StatsEntity addStatsToDbEntity(Stats stats, UUID id) {
        StatsEntity statsEntity = new StatsEntity();
        statsEntity.setGroup1Count(stats.group1.count);
        statsEntity.setGroup1Name(stats.group1.name);
        statsEntity.setGroup1Percentage(stats.group1.percentage);

        statsEntity.setGroup2Count(stats.group2.count);
        statsEntity.setGroup2Name(stats.group2.name);
        statsEntity.setGroup2Percentage(stats.group2.percentage);

        statsEntity.setGroup3Count(stats.group3.count);
        statsEntity.setGroup3Name(stats.group3.name);
        statsEntity.setGroup3Percentage(stats.group3.percentage);

        statsEntity.setGroup4Count(stats.group4.count);
        statsEntity.setGroup4Name(stats.group4.name);
        statsEntity.setGroup4Percentage(stats.group4.percentage);

        statsEntity.setMaxResponseTimeKo(stats.maxResponseTime.ko);
        statsEntity.setMaxResponseTimeOk(stats.maxResponseTime.ok);
        statsEntity.setMaxResponseTimeTotal(stats.maxResponseTime.total);

        statsEntity.setMeanNumberOfRequestsPerSecondKo(stats.meanNumberOfRequestsPerSecond.ko);
        statsEntity.setMeanNumberOfRequestsPerSecondOk(stats.meanNumberOfRequestsPerSecond.ok);
        statsEntity.setMeanNumberOfRequestsPerSecondTotal(stats.meanNumberOfRequestsPerSecond.total);

        statsEntity.setMeanResponseTimeKo(stats.meanResponseTime.ko);
        statsEntity.setMeanResponseTimeOk(stats.meanResponseTime.ok);
        statsEntity.setMeanResponseTimeTotal(stats.meanResponseTime.total);

        statsEntity.setMinResponseTimeKo(stats.minResponseTime.ko);
        statsEntity.setMinResponseTimeOk(stats.minResponseTime.ok);
        statsEntity.setMinResponseTimeTotal(stats.minResponseTime.total);

        statsEntity.setName(stats.name);

        statsEntity.setNumberOfRequestsKo(stats.numberOfRequests.ko);
        statsEntity.setNumberOfRequestOk(stats.numberOfRequests.ok);
        statsEntity.setNumberOfRequestsTotal(stats.numberOfRequests.total);

        statsEntity.setPercentiles1Ko(stats.percentiles1.ko);
        statsEntity.setPercentiles1Ok(stats.percentiles1.ok);
        statsEntity.setPercentiles1Total(stats.percentiles1.total);

        statsEntity.setPercentiles2Ko(stats.percentiles2.ko);
        statsEntity.setPercentiles2Ok(stats.percentiles2.ok);
        statsEntity.setPercentiles2Total(stats.percentiles2.total);

        statsEntity.setPercentiles3Ko(stats.percentiles3.ko);
        statsEntity.setPercentiles3Ok(stats.percentiles3.ok);
        statsEntity.setPercentiles3Total(stats.percentiles3.total);

        statsEntity.setPercentiles4Ko(stats.percentiles4.ko);
        statsEntity.setPercentiles4Ok(stats.percentiles4.ok);
        statsEntity.setPercentiles4Total(stats.percentiles4.total);

        statsEntity.setStandardDeviationKo(stats.standardDeviation.ko);
        statsEntity.setStandardDeviationOk(stats.standardDeviation.ok);
        statsEntity.setStandardDeviationTotal(stats.standardDeviation.total);


        statsEntity.setReport(new Report(id));
        return statsEntity;
    }


    private static File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }


    static class MyLinkedMap<K, V> extends LinkedHashMap<K, V> {

        public MyLinkedMap(Map<? extends K, ? extends V> m) {
            super(m);
        }

        public V getValue(int i) {

            Map.Entry<K, V> entry = this.getEntry(i);
            if (entry == null) return null;

            return entry.getValue();
        }

        public Map.Entry<K, V> getEntry(int i) {
            // check if negative index provided
            Set<Map.Entry<K, V>> entries = entrySet();
            int j = 0;

            for (Map.Entry<K, V> entry : entries)
                if (j++ == i) return entry;

            return null;

        }

    }

}