package com.siit.fullAppExample;

import com.siit.webapp.CatalogueService;
import com.siit.webapp.Student;
import com.siit.webapp.StudentsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

public class CatalogueServiceTest {

    @Mock
    StudentsRepository studentsRepository;

    CatalogueService catalogueService;

    @BeforeEach
    void init() {
        catalogueService = new CatalogueService(studentsRepository);
    }

    @Test
    void calculateAverageTest() {
        //given
        Student student = new Student("Ion", "Popescu", List.of(6,7,8));
        //when
        double average = catalogueService.calculateAverage(student);
        //then
        Assertions.assertEquals(7,average);
    }

}
