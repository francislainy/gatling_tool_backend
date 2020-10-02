package com.francislainy.gatling_tool.helper;

import com.francislainy.gatling_tool.dto.stats.Stats;
import com.francislainy.gatling_tool.model.entity.stats.StatsEntity;
import com.google.gson.Gson;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

public class StatsJsonHelper {

    public static String TYPE = "application/json";

    public static boolean hasJsonFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static StatsEntity jsonToStats(MultipartFile file, UUID id) {

        try {
            Gson gson = new Gson();

            File myFile = convertMultiPartToFile(file);

            BufferedReader br = new BufferedReader(new FileReader(myFile));

            Stats stats = gson.fromJson(br, Stats.class);


            StatsEntity statsEntity = new StatsEntity();
            statsEntity.setGroup1Count(stats.stats.group1.count);
            statsEntity.setGroup1Name(stats.stats.group1.name);
            statsEntity.setGroup1Percentage(stats.stats.group1.percentage);

            statsEntity.setGroup2Count(stats.stats.group2.count);
            statsEntity.setGroup2Name(stats.stats.group2.name);
            statsEntity.setGroup2Percentage(stats.stats.group2.percentage);

            statsEntity.setGroup3Count(stats.stats.group3.count);
            statsEntity.setGroup3Name(stats.stats.group3.name);
            statsEntity.setGroup3Percentage(stats.stats.group3.percentage);

            statsEntity.setGroup4Count(stats.stats.group4.count);
            statsEntity.setGroup4Name(stats.stats.group4.name);
            statsEntity.setGroup4Percentage(stats.stats.group4.percentage);

            statsEntity.setMaxResponseTimeKo(stats.stats.maxResponseTime.ko);
            statsEntity.setMaxResponseTimeOk(stats.stats.maxResponseTime.ok);
            statsEntity.setMaxResponseTimeTotal(stats.stats.maxResponseTime.total);

            statsEntity.setMeanNumberOfRequestsPerSecondKo(stats.stats.meanNumberOfRequestsPerSecond.ko);
            statsEntity.setMeanNumberOfRequestsPerSecondOk(stats.stats.meanNumberOfRequestsPerSecond.ok);
            statsEntity.setMeanNumberOfRequestsPerSecondTotal(stats.stats.meanNumberOfRequestsPerSecond.total);

            statsEntity.setMeanResponseTimeKo(stats.stats.meanResponseTime.ko);
            statsEntity.setMeanResponseTimeOk(stats.stats.meanResponseTime.ok);
            statsEntity.setMeanResponseTimeTotal(stats.stats.meanResponseTime.total);

            statsEntity.setMinResponseTimeKo(stats.stats.minResponseTime.ko);
            statsEntity.setMinResponseTimeOk(stats.stats.minResponseTime.ok);
            statsEntity.setMinResponseTimeTotal(stats.stats.minResponseTime.total);

            statsEntity.setName(stats.name);
            statsEntity.setNumberOfRequestsKo(stats.stats.numberOfRequests.ko);
            statsEntity.setNumberOfRequestOk(stats.stats.numberOfRequests.ok);
            statsEntity.setNumberOfRequestsTotal(stats.stats.numberOfRequests.total);

            statsEntity.setPercentiles1Ko(stats.stats.percentiles1.ko);
            statsEntity.setPercentiles1Ok(stats.stats.percentiles1.ok);
            statsEntity.setPercentiles1Total(stats.stats.percentiles1.total);

            statsEntity.setPercentiles2Ko(stats.stats.percentiles2.ko);
            statsEntity.setPercentiles2Ok(stats.stats.percentiles2.ok);
            statsEntity.setPercentiles2Total(stats.stats.percentiles2.total);

            statsEntity.setPercentiles3Ko(stats.stats.percentiles3.ko);
            statsEntity.setPercentiles3Ok(stats.stats.percentiles3.ok);
            statsEntity.setPercentiles3Total(stats.stats.percentiles3.total);

            statsEntity.setPercentiles4Ko(stats.stats.percentiles4.ko);
            statsEntity.setPercentiles4Ok(stats.stats.percentiles4.ok);
            statsEntity.setPercentiles4Total(stats.stats.percentiles4.total);

            statsEntity.setStandardDeviationKo(stats.stats.standardDeviation.ko);
            statsEntity.setStandardDeviationOk(stats.stats.standardDeviation.ok);
            statsEntity.setStandardDeviationTotal(stats.stats.standardDeviation.total);

            statsEntity.setCategoryId(id);


            return statsEntity;

        } catch (IOException e) {
            throw new RuntimeException("fail to parse json file: " + e.getMessage());
        }
    }


    private static File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

//    public static ByteArrayInputStream tutorialsToJson(List<Stats> tutorials) {
//        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
//
//        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
//             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
//            for (Stats tutorial : tutorials) {
//                List<String> data = Arrays.asList(
//                        String.valueOf(tutorial.getId()),
//                        tutorial.getTitle(),
//                        tutorial.getDescription(),
//                        String.valueOf(tutorial.isPublished())
//                );
//
//                csvPrinter.printRecord(data);
//            }
//
//            csvPrinter.flush();
//            return new ByteArrayInputStream(out.toByteArray());
//        } catch (IOException e) {
//            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
//        }
//    }
}