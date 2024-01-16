package com.alite.AbstractCheck.controll;

import com.alite.AbstractCheck.MyRepository;
import com.alite.AbstractCheck.entity.Citation;
import com.alite.AbstractCheck.service.AbsTextService;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class Main {
    @Autowired
    private MyRepository repository;
    @Autowired
    private AbsTextService service;

    @GetMapping("/start")
    public void findAll(){
        service.getData();
    }
    @GetMapping("/id/{id}")
    public Optional<Citation> byId(@PathVariable("id") String id){
        return repository.findByPui(id);
    }

    @GetMapping("/testencode")
    public void test(){
        String encodedString = "Eichengr&#xfc;n Eichengr&#xfc;n Eichengr&#xfc;n";
        String decodedString = StringEscapeUtils.unescapeHtml4(encodedString);

        System.out.println("Decoded String: " + decodedString);
    }

}
