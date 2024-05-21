package io.zipcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Classroom {
    private Student[] students;

    public Classroom(int maxNumberOfStudents) {
        this.students = new Student[maxNumberOfStudents];
    }

    public Student[] getStudents() {
        return students;
    }

    public double getAverageExamScore() {
        double sum = 0.0;
        int count = 0;
        for (Student student : students) {
            if (student != null) {
                sum += student.getAverageExamScore();
                count++;
            }
        }
        return count > 0 ? sum / count : 0.0;
    }

    public void addStudent(Student student) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                students[i] = student;
                break;
            }
        }
    }

    public void removeStudent(String firstName, String lastName) {
        int index = -1;
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null && students[i].getFirstName().equals(firstName) && students[i].getLastName().equals(lastName)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            for (int i = index; i < students.length - 1; i++) {
                students[i] = students[i + 1];
            }
            students[students.length - 1] = null;
        }
    }

    public Student[] getStudentsByScore() {
        List<Student> nonNullStudents = new ArrayList<>();
        for (Student student : students) {
            if (student != null) {
                nonNullStudents.add(student);
            }
        }

        Student[] sortedStudents = nonNullStudents.toArray(new Student[0]);
        Arrays.sort(sortedStudents, (s1, s2) -> {
            int scoreCompare = Double.compare(s2.getAverageExamScore(), s1.getAverageExamScore());
            if (scoreCompare == 0) {
                int firstNameCompare = s1.getFirstName().compareTo(s2.getFirstName());
                if (firstNameCompare == 0) {
                    return s1.getLastName().compareTo(s2.getLastName());
                }
                return firstNameCompare;
            }
            return scoreCompare;
        });

        return sortedStudents;
    }

    public Map<Student, String> getGradeBook() {
        Map<Student, String> gradeBook = new HashMap<>();
        Student[] sortedStudents = getStudentsByScore();
        int totalStudents = sortedStudents.length;

        for (int i = 0; i < totalStudents; i++) {
            double percentile = (double) i / totalStudents * 100;

            if (percentile < 10) {
                gradeBook.put(sortedStudents[i], "A");
            } else if (percentile < 30) {
                gradeBook.put(sortedStudents[i], "B");
            } else if (percentile < 50) {
                gradeBook.put(sortedStudents[i], "C");
            } else if (percentile < 89) {
                gradeBook.put(sortedStudents[i], "D");
            } else{
                gradeBook.put(sortedStudents[i], "F");
            }
        }

        return gradeBook;
    }

}
