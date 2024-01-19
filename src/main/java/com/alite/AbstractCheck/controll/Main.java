package com.alite.AbstractCheck.controll;

import com.alite.AbstractCheck.MyRepository;
import com.alite.AbstractCheck.entity.Citation;
import com.alite.AbstractCheck.service.AbsTextService;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Scanner;


//@RestController
@Component
public class Main implements CommandLineRunner {
    @Autowired
    private MyRepository repository;
    @Autowired
    private AbsTextService service;


    @Override
    public void run(String... args) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.println("Select:");
        System.out.println("To Compare type: 1");
        System.out.println("To Generate Only CSV type: 2");
        System.out.println("To Exit Type: 3");
        int t  = scan.nextInt();
        if(t == 1) service.getData();
        else if(t == 2) service.getDataToCSV();
        else if(t == 3) System.out.println("End");
        else System.out.println("Wrong Input");
        System.out.println("Program Ended!");
    }


//    @GetMapping("/start")
//    public void findAll() throws IOException {
//        service.getData();
//    }
//    @GetMapping("/csvPrint")
//    public void generateCSV(){
//        service.getDataToCSV();
//    };
}
