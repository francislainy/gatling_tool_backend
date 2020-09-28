package com.francislainy.gatling_tool.helper;

import ch.qos.logback.classic.turbo.TurboFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.francislainy.gatling_tool.model.entity.JsonTutorial;
import com.francislainy.gatling_tool.model.entity.Tutorial;
import com.francislainy.gatling_tool.model.entity.Tutorials;
import com.google.gson.Gson;
import org.apache.commons.csv.*;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import util.Util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class JsonHelper {

    public static String TYPE = "application/json";

    public static boolean hasJsonFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<JsonTutorial> jsonToTutorials(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withDelimiter(',').withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            Gson gson = new Gson();
//            BufferedReader br = new BufferedReader(
//                    new FileReader("/Users/camposf/IdeaProjects/gatling_tool/file.json"));
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(is));

            //convert the json string back to object
            Tutorial obj = gson.fromJson(br, Tutorial.class);

            System.out.println(obj);


//            List<JsonTutorial> tutorials = new ArrayList<>();

            Tutorial[] statesModels = gson.fromJson(fileReader, Tutorial[].class);




            List<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                JsonTutorial tutorial = new JsonTutorial(
                        Long.parseLong(csvRecord.get(0)),
                        csvRecord.get(1),
                        csvRecord.get(2),
                        Boolean.parseBoolean(csvRecord.get(3))
                );

//                tutorials.add(tutorial);
            }

//            return tutorials;
            return null;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse json file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream tutorialsToJson(List<JsonTutorial> tutorials) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            for (JsonTutorial tutorial : tutorials) {
                List<String> data = Arrays.asList(
                        String.valueOf(tutorial.getId()),
                        tutorial.getTitle(),
                        tutorial.getDescription(),
                        String.valueOf(tutorial.isPublished())
                );

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}