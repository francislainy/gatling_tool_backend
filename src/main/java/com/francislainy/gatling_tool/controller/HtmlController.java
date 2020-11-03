package com.francislainy.gatling_tool.controller;

import com.francislainy.gatling_tool.helper.HtmlHelper;
import com.francislainy.gatling_tool.message.ResponseMessage;
import com.francislainy.gatling_tool.service.HtmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin()
@Controller
@RequestMapping("/api/gatling-tool/html")
public class HtmlController {

    @Autowired
    HtmlService fileService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (HtmlHelper.hasHtmlFormat(file)) {
            try {

                int numUsers = fileService.save(file);

                message += "Uploaded the file successfully: " + file.getOriginalFilename() + " - " + numUsers + " users";
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "! \n" + e.getMessage();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload a html file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

}
