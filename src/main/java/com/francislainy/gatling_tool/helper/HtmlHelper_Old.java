package com.francislainy.gatling_tool.helper;

import com.francislainy.gatling_tool.model.entity.HtmlTutorial;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class HtmlHelper_Old {

    public static String TYPE = "text/html";

    public static boolean hasHtmlFormat(MultipartFile file) {

        return TYPE.equals(file.getContentType());
    }

    public static List<HtmlTutorial> htmlToTutorials(MultipartFile file) {

        try {
            List<HtmlTutorial> tutorialsList = new ArrayList<>();

            File myFile = convertMultiPartToFile(file);

            try {
                Document document = Jsoup.parse(myFile, "utf-8");
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
                    System.out.println(list1[i] + " ---");
                    HtmlTutorial htmlTutorial = new HtmlTutorial();
                    htmlTutorial.setTitle((String) list1[i]);
                    tutorialsList.add(htmlTutorial);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


            return tutorialsList;

        } catch (IOException e) {
            throw new RuntimeException("fail to parse html file: " + e.getMessage());
        }
    }


    private static File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

}