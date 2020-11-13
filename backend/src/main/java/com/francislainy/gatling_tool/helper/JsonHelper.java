package com.francislainy.gatling_tool.helper;

import com.francislainy.gatling_tool.model.entity.JsonTutorial;
import com.francislainy.gatling_tool.model.entity.Tutorials;
import com.google.gson.Gson;
import org.apache.commons.csv.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

public class JsonHelper {

    public static String TYPE = "application/json";

    public static boolean hasJsonFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<JsonTutorial> jsonToTutorials(MultipartFile file) {

        try {
            Gson gson = new Gson();

            File myFile = convertMultiPartToFile(file);

            BufferedReader br = new BufferedReader(new FileReader(myFile));

            Tutorials tutorials = gson.fromJson(br, Tutorials.class);

            List<JsonTutorial> tutorialsList = new ArrayList<>();


            for (JsonTutorial jsonTutorial : tutorials.getTutorials()) {

                tutorialsList.add(jsonTutorial);
            }

            return tutorialsList;

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