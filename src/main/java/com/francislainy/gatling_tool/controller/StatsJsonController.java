package com.francislainy.gatling_tool.controller;

import com.francislainy.gatling_tool.dto.category.CategoryQueryDto;
import com.francislainy.gatling_tool.helper.StatsJsonHelper;
import com.francislainy.gatling_tool.message.ResponseMessage;
import com.francislainy.gatling_tool.service.StatsJsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@CrossOrigin()
@Controller
@RequestMapping("/api/gatling-tool/json")
public class StatsJsonController {

    @Autowired
    StatsJsonService fileService;

    @PostMapping(value = "/import")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @RequestBody CategoryQueryDto categoryQueryDto) {
        String message = "";

        UUID id = categoryQueryDto.getId();

        if (StatsJsonHelper.hasJsonFormat(file)) {
            try {
                fileService.save(file, id);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload a json file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }



//    @GetMapping("/tutorials")
//    public ResponseEntity<List<Tutorial>> getAllTutorials() {
//        try {
//            List<Tutorial> tutorials = fileService.getAllTutorials();
//
//            if (tutorials.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(tutorials, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @GetMapping("/download")
//    public ResponseEntity<Resource> getFile() {
//        String filename = "tutorials.csv";
//        InputStreamResource file = new InputStreamResource(fileService.load());
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
//                .contentType(MediaType.parseMediaType("application/csv"))
//                .body(file);
//    }
}
