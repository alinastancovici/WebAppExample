package com.siit.webapp;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentsRepository {


    public List<Student> getStudents(){

        List<Student> studentList = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader("src\\main\\resources\\students.csv"))) {
            List<String[]> r = reader.readAll();
            r.forEach(strings -> {
                studentList.add(new Student(strings[0],strings[1],List.of(
                        Integer.valueOf(strings[2]),Integer.valueOf(strings[3]))));
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
        return studentList;
    }

}
