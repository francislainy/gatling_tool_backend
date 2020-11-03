package com.francislainy.gatling_tool.helper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlHelper {

    public static String TYPE = "text/html";

    public static boolean hasHtmlFormat(MultipartFile file) {

        return TYPE.equals(file.getContentType());
    }

    public static int htmlToTutorials(MultipartFile file) {

        try {

            File myFile = convertMultiPartToFile(file);

            retrieveDurationSeconds(myFile);
            int numUsers = retrieveNumUsers(myFile);

            return numUsers;

        } catch (IOException e) {
            throw new RuntimeException("fail to parse html file: " + e.getMessage());
        }
    }


    private static void retrieveDurationSeconds(File file) {
        try {
            Document document =
                    Jsoup.parse(file, "utf-8");

            String wholeData = String.valueOf(document.body());

            Pattern pattern = Pattern.compile("duration : (.*?)seconds", Pattern.DOTALL);

            Matcher matcher = pattern.matcher(wholeData);
            if (matcher.find()) {
                System.out.println(matcher.group(1));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static int retrieveNumUsers(File file) {

        int numUsers = 0;

        try {
            Document document =
                    Jsoup.parse(file, "utf-8");

            String wholeData = String.valueOf(document.body());

            Pattern pattern0 = Pattern.compile("allUsersChart = (.*?);", Pattern.DOTALL);

            Matcher matcher0 = pattern0.matcher(wholeData);
            String string0 = "";
            if (matcher0.find()) {
                string0 = matcher0.group(1);
            }

            Pattern pattern = Pattern.compile("data: \\[(.*?)tooltip", Pattern.DOTALL);

            Matcher matcher = pattern.matcher(string0);
            ArrayList<String> userTimesList = new ArrayList();
            ArrayList<String> usersList = new ArrayList();
            String string1 = "";
            if (matcher.find()) {
                string1 = matcher.group(1);
            }

            for (String s : string1.split("\\[(.*?)")) {
                userTimesList.add(s.substring(0, s.length() - 2)); //remove ] and comma
            }

            int i = 0;
            for (String s : userTimesList) {
                if (i != 0) { //First item is messed up because of line break so removing it from the list as it's not a number
                    usersList.add(s.split(",")[1]);
                }
                i++;
            }

            Collections.sort(usersList);

            numUsers = Integer.parseInt(usersList.get(usersList.size() - 1));

            System.out.println(numUsers);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return numUsers;

    }

    private static File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}