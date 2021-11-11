package com.example.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckWhenStudentEmailExist() {
        String email = "jamila@gmail.com";
        Student student = new Student(
                "Jamila",
                email,
                Gender.MALE
        );
        underTest.save(student);

        boolean expected = underTest.selectExistsEmail(email);

        assertThat(expected).isTrue();
    }

    @Test
    void itShouldCheckWhenStudentEmailDoesNotExists() {
        String email = "jamila@gmail.com";

        boolean expected = underTest.selectExistsEmail(email);



        assertThat(expected).isFalse();
    }
}