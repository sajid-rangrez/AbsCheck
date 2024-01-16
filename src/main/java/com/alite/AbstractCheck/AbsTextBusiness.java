package com.alite.AbstractCheck;

import com.alite.AbstractCheck.entity.Citation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbsTextBusiness {
    @Autowired
    private MyRepository repository;
    public List<Citation> findAllRequired(){
//        System.out.println("inside test");
        return repository.findAllRequired();
    }

    public void updateProblem(Citation record){
        repository.save(record);
    }
}
