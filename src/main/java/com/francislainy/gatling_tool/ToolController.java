package com.francislainy.gatling_tool;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RequestMapping("/gatling_tool")
@RestController
public class ToolController {

    private List<String> books;
    private Map result;

    public ToolController() {

        result = new HashMap();

        Map map = new HashMap();

        map.put("id", "1");
        map.put("name", "name1");
        map.put("run_date", "2020-08-08");
        map.put("created_date", "2019-08-08");
        map.put("category", "blended1");

        Map map1 = new HashMap();

        map1.put("id", "2");
        map1.put("name", "name2");
        map1.put("run_date", "2020-08-08");
        map1.put("created_date", "2019-08-08");
        map1.put("category", "blended2");


        ArrayList list = new ArrayList();
        list.add(map);
        list.add(map1);

        result = new HashMap();
        result.put("table_items", list);

        books = new ArrayList<>();
        books.add("Hacking with Spring Boot 2.3");
        books.add("97 Things Every Java Programmer Should Know");
        books.add("Spring Boot: Up and Running");
    }

    @GetMapping
    public List<String> list() {
        return books;
    }

    @PostMapping
    public void create(@RequestBody Map<String, String> payload) {
        books.add(payload.get("title"));
    }

    @PutMapping
    public void update(@RequestBody Map<String, String> payload) {
        String oldTitle = payload.get("oldtitle");
        String newTitle = payload.get("newtitle");
        if (books.contains(oldTitle)) {
            books.set(books.indexOf(oldTitle), newTitle);
        }
    }

    //    http://localhost:8081/gatling_tool?title=TEST
//    @DeleteMapping
//    public void delete(@RequestParam String title) {
////        if (books.contains(title)) {
////            books.remove(books.indexOf(title));
////        }
//
//        map.remove("table_items");
//    }


    @DeleteMapping
    public void deleteMap(@RequestParam String id) {

        result.remove("table_items");

        result = new HashMap();

        Map map = new HashMap();

        map.put("id", "1");
        map.put("name", "name1");
        map.put("run_date", "2020-08-08");
        map.put("created_date", "2019-08-08");
        map.put("category", "blended1");


        Map map1 = new HashMap();

        map1.put("id", "2");
        map1.put("name", "name2");
        map1.put("run_date", "2020-08-08");
        map1.put("created_date", "2019-08-08");
        map1.put("category", "blended2");


        ArrayList list = new ArrayList();

        if (id.equals("1")) {
            list.add(map1);
        } else {
            list.add(map);
        }

        result.put("table_items", list);

    }

    @GetMapping("/table_items")
    public Map getTableItems() {

        return result;
    }

}
