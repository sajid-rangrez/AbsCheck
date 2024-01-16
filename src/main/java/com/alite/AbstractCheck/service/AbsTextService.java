package com.alite.AbstractCheck.service;

import com.alite.AbstractCheck.AbsTextBusiness;
import com.alite.AbstractCheck.CheckAbs;
import com.alite.AbstractCheck.entity.Citation;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AbsTextService {
    @Autowired
    AbsTextBusiness business;
    public void getData(){
       List<Citation> records = business.findAllRequired();
       for(Citation record : records){
           String absText = CheckAbs.getAbstract(record.getPui());
           if(absText != null){
               if(!record.getAbsText().equals(absText)){
                   record.setProblem(true);
                   record.setApiAbsText(absText);
                   System.out.println(record.getPui());
               }
               else{
                   record.setProblem(false);
                   record.setApiAbsText(null);
               }
           }
           else{
               record.setProblem(true);
               record.setApiAbsText(absText);
               System.out.println(record.getPui());
           }
           business.updateProblem(record);
       }
       System.out.println("Updated");
       records = business.findAllRequired();
        String filePath = "output.csv";
        writeDataToCsv(records, filePath);
        System.out.println("CSV file generated at: " + filePath);
    }
    public static void writeDataToCsv(List<Citation> citations, String filePath) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            // Write header
            String[] header = {"pui","Problem", "Abstract", "ApiAbstract"}; // Replace with your field names
            writer.writeNext(header);

            // Write data
            for (Citation obj : citations) {
                String[] data = {obj.getPui(),obj.getProblem().toString(), obj.getAbsText(), obj.getApiAbsText()};
                writer.writeNext(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static String ignoreNlm(String inputText){
        // Define the pattern to match
        String patternToIgnore = "NlmCategory=\"[^\"]*\"";

        // Create a pattern object
        Pattern pattern = Pattern.compile(patternToIgnore);

        // Create a matcher object
        Matcher matcher = pattern.matcher(inputText);

        // Replace matched patterns with an empty string
        return matcher.replaceAll("");

    }
}
