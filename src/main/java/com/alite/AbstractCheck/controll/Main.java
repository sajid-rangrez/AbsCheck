package com.alite.AbstractCheck.controll;

import com.alite.AbstractCheck.MyRepository;
import com.alite.AbstractCheck.entity.Citation;
import com.alite.AbstractCheck.service.AbsTextService;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class Main {
    @Autowired
    private MyRepository repository;
    @Autowired
    private AbsTextService service;
    @GetMapping("/start")
    public void findAll() throws IOException {
        service.getData();
    }
    @GetMapping("/csvPrint")
    public void generateCSV(){
        service.getDataToCSV();
    };
}
