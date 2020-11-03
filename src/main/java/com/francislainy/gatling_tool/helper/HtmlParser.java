package com.francislainy.gatling_tool.helper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlParser {

    public static void main(String[] args) {

//        retrieveNumUsers();


        try {
            Document document =
                    Jsoup.parse(new File("/Users/camposf/IdeaProjects/gatling_tool/index2.html"), "utf-8");
            Elements scriptElements = document.getElementsByTag("script");

            for (Element element : scriptElements) {
                for (DataNode node : element.dataNodes()) {
                    String wholeData = node.getWholeData();

                    Pattern pattern = Pattern.compile("var timestamp = (.*?);", Pattern.DOTALL);

                    Matcher matcher = pattern.matcher(wholeData);
                    if (matcher.find()) {
                        System.out.println(matcher.group(1));
                    }

                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void retrieveNumUsers() {
        try {
            Document document =
                    Jsoup.parse(new File("/Users/camposf/IdeaProjects/gatling_tool/index_o.html"), "utf-8");

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

            String numUsers = usersList.get(usersList.size() - 1);

            System.out.println(numUsers);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
