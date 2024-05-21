package io.zipcoder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Student {
    private String firstName;
    private String lastName;
    private List<Double> examScores;

    public Student(String firstName, String lastName, Double[] testScores) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.examScores = new ArrayList<>();
        for (Double score : testScores) {
            examScores.add(score);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getNumberOfExamsTaken() {
        return examScores.size();
    }

    public String getExamScores() {
        StringBuilder sb = new StringBuilder();
        sb.append("Exam Scores:\n");
        for (int i = 0; i < examScores.size(); i++) {
            sb.append(String.format(" \tExam %d -> %.2f\n", i + 1, examScores.get(i)));
        }
        return sb.toString();
    }

    public void addExamScore(Double examScore) {
        examScores.add(examScore);
    }

    public void setExamScore(int examNumber, Double newScore) {
        examScores.set(examNumber - 1, newScore);
    }

    public Double getAverageExamScore() {
        return examScores.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }

    @Override
    public String toString() {
        return String.format("Student Name: %s %s\n> Average Score: %.2f\n> %s", firstName, lastName, getAverageExamScore(), getExamScores());
    }
}
