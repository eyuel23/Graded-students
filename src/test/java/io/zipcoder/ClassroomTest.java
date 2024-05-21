package io.zipcoder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static junit.framework.TestCase.assertTrue;

public class ClassroomTest {
    private Classroom classroom;
    private Student student1;
    private Student student2;
    private Student student3;
    private Student student4;
    private Student student5;

    @Before
    public void setup() {
        classroom = new Classroom(6);
        student1 = new Student("John", "Doe", new Double[]{100.0, 95.0});
        student2 = new Student("Jane", "Smith", new Double[]{90.0, 80.0});
        student3 = new Student("Alice", "Johnson", new Double[]{75.0, 70.0});
        student4 = new Student("Bob", "Brown", new Double[]{65.0, 60.0});
        student5 = new Student("Charlie", "Davis", new Double[]{55.0, 50.0});

        classroom.addStudent(student1);
        classroom.addStudent(student2);
        classroom.addStudent(student3);
        classroom.addStudent(student4);
        classroom.addStudent(student5);
    }
    @Test
    public void testAddStudent() {
        Student student6 = new Student("New", "Student", new Double[]{100.0});
        classroom.addStudent(student6);
        Student[] students = classroom.getStudents();
        boolean found = false;
        for (Student student : students) {
            if (student != null && student.getFirstName().equals("New") && student.getLastName().equals("Student")) {
                found = true;
                break;
            }
        }
        Assert.assertTrue(found);
    }


    @Test
    public void testRemoveStudent() {
        classroom.removeStudent("Alice", "Johnson");
        Student[] students = classroom.getStudents();
        boolean found = false;
        for (Student student : students) {
            if (student != null && student.getFirstName().equals("Alice") && student.getLastName().equals("Johnson")) {
                found = true;
                break;
            }
        }
        Assert.assertFalse(found);
        // Verify that the array is reordered correctly
        Assert.assertEquals(student4, students[2]);
        Assert.assertNull(students[4]);
    }

    @Test
    public void testGetStudentsByScore() {
        Student[] sortedStudents = classroom.getStudentsByScore();
        Assert.assertEquals(student1, sortedStudents[0]);
        Assert.assertEquals(student2, sortedStudents[1]);
        Assert.assertEquals(student3, sortedStudents[2]);
        Assert.assertEquals(student4, sortedStudents[3]);
        Assert.assertEquals(student5, sortedStudents[4]);
    }

    @Test
    public void testGetGradeBook() {
        Map<Student, String> gradeBook = classroom.getGradeBook();
        Assert.assertEquals("A", gradeBook.get(student1));
        Assert.assertEquals("B", gradeBook.get(student2));
        Assert.assertEquals("C", gradeBook.get(student3));
        Assert.assertEquals("D", gradeBook.get(student4));
    }

    @Test
    public void testGetAverageExamScore() {
        Double expectedAverage = (97.5 + 85.0 + 72.5 + 62.5 + 52.5) / 5;
        Assert.assertEquals(expectedAverage, classroom.getAverageExamScore(), 0.01);
    }
}
