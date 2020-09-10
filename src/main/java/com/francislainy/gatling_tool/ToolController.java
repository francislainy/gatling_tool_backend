package com.francislainy.gatling_tool;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RequestMapping("/gatling-tool")
@RestController

public class ToolController {

    private List<String> categories;
    private Map result;
    private ArrayList list;

    public ToolController() {

        result = new HashMap();
        list = new ArrayList();

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


        list.add(map);
        list.add(map1);

        result.put("table_items", list);

        categories = new ArrayList<>();
        categories.add("Blended");
        categories.add("CRS");
        categories.add("None");
    }


    @GetMapping
    public List<String> list() {
        return categories;
    }


    @PostMapping
    public void create(@RequestBody Map<String, String> payload) {
        categories.add(payload.get("title"));
    }


    @PutMapping
    public void update(@RequestBody Map<String, String> payload) {
        String oldTitle = payload.get("oldtitle");
        String newTitle = payload.get("newtitle");
        if (categories.contains(oldTitle)) {
            categories.set(categories.indexOf(oldTitle), newTitle);
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

        HashMap mainHashMap = new HashMap();
        for (Object o : list) {

            HashMap hashMap = (HashMap) o;

            if (hashMap.get("id").equals(id)) {
                mainHashMap = hashMap;
            }
        }

        list.remove(mainHashMap);

    }


    @GetMapping("/table-items")
    public Map getTableItems() {

        return result;
    }


    @GetMapping("/get-categories")
    public Map getCategories() {

        Map result = new HashMap();
        result.put("categories", categories);
        return result;
    }


    @PostMapping("/create-category")
    public void createCategory(@RequestBody Map<String, String> payload) {
        categories.add(payload.get("category_name"));
    }

}


