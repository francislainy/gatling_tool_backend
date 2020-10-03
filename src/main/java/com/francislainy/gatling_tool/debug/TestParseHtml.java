package com.francislainy.gatling_tool.debug;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.francislainy.gatling_tool.debug.model.Contents;
import com.francislainy.gatling_tool.debug.model.Contents_;
import com.francislainy.gatling_tool.debug.model.ReqAuthorize;
import com.francislainy.gatling_tool.dto.stats.Stats;
import com.google.gson.Gson;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.*;

public class TestParseHtml {

    public static void main(String[] args) throws IOException {

        parseJson1();
    }


    private static void parseJson1() throws IOException {

        Gson gson = new Gson();

        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(new File("/Users/camposf/IdeaProjects/gatling_tool/stats.json"), Map.class);

        for (Object key : map.keySet()) {
            System.out.printf("key=%s, value=%s\n", key, map.get(key));
        }

        String contentsJson = gson.toJson(map.get("contents"));
        Contents contents = gson.fromJson(contentsJson, Contents.class);

        Contents_ contents_ = contents.contents;
        String jsonContents_ = gson.toJson(contents_);


        Map contents_Map = gson.fromJson(jsonContents_, Map.class);

        MyLinkedMap myLinkedMap = new MyLinkedMap(contents_Map);


        for (int i = 0; i < myLinkedMap.size(); i++) {

            String jsonReqAuthorize = gson.toJson(myLinkedMap.getValue(i));
            ReqAuthorize reqAuthorize = gson.fromJson(jsonReqAuthorize, ReqAuthorize.class);
            System.out.println(reqAuthorize);
        }

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

    private static void parseHtml() {
        try {
            Document document = Jsoup.parse(new File("index.html"), "utf-8");
            System.out.println(document.title());

            ArrayList<String> list = new ArrayList<>();
            Element table = document.select("table").get(0); //select the first table.
            Elements rows = table.select("tr");

            for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
                Element row = rows.get(i);
                Elements tds = row.select("td");

                if (i > 3) { //First three elements are headers

                    String item = tds.get(0).text().split(":")[0];
                    list.add(item);
                }
            }


            Set<String> set = new LinkedHashSet<String>(list);
            Object[] list1 = set.toArray();

            for (int i = 0; i < list1.length; i++) {
                System.out.println(list1[i]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

