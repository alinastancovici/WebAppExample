package com.siit.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

@Service
public class CatalogueService {

    @Autowired
    public CatalogueService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    private final StudentsRepository studentsRepository;
    public String createStudentCatalogue(){
        List<Student> studentListToBeProcessed = studentsRepository.getStudents();
        String result = "";

        for (Student student: studentListToBeProcessed) {
            result = result.concat(student.getFirstName()).concat(" ").concat(student.getLastName().concat("<br></br>"));
        }

        return result;
    }

    public double calculateAverage(Student student) {
        if (student.getGrades() == null || student.getGrades().isEmpty()) {
            return 0;
        }

        double sum = 0;
        for (Integer grades : student.getGrades()) {
            sum +=grades;
        }
        return sum / student.getGrades().size();
    }

    public Map<Student,Double> getRanking() {
        Map<Student,Double> studentRanking = new HashMap<>();

        for( Student student: studentsRepository.getStudents()) {
            studentRanking.put(student,calculateAverage(student));
        }
        return studentRanking.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(comparingByValue()))
                .collect(
                        toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
                                LinkedHashMap::new));
    }

}
