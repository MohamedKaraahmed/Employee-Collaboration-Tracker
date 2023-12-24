package com.example.employeecollaborationtracker.service.Impl;

import com.example.employeecollaborationtracker.service.DataLoader;
import com.example.employeecollaborationtracker.service.ValidationService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
public class DataLoaderImpl implements DataLoader {


    private final ValidationService validationService;
    private final String CSV_FILE_PATH = "src/main/resources/inputData/assignmentsLoader.csv";

    @Autowired
    public DataLoaderImpl(ValidationService validationService) {
        this.validationService = validationService;
    }

    @Override
    @PostConstruct
    public void loadDataFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String inputLine;
            //read line from CSV file
            while ((inputLine = br.readLine()) != null) {
                String[] CSVRecord = inputLine.split(",");
                //validate the CSV line data
                validationService.validateCSVRecord(CSVRecord);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

