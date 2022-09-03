package fr.laurentvrevin.mareu.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.laurentvrevin.mareu.model.Employees;

public class DummyEmployeesGenerator {
    public static List<Employees> DUMMY_EMPLOYEES = Arrays.asList(
            new Employees(1, "Caroline", "Développeuse",
                    "caroline@lamzone.com"),
            new Employees(2, "Caroline", "Développeuse",
                    "caroline@lamzone.com"),
            new Employees(3, "Caroline", "Développeuse",
                    "caroline@lamzone.com"),
            new Employees(4, "Caroline", "Développeuse",
                    "caroline@lamzone.com"),
            new Employees(5, "Caroline", "Développeuse",
                    "caroline@lamzone.com"),
            new Employees(6, "Caroline", "Développeuse",
                    "caroline@lamzone.com"),
            new Employees(7, "Caroline", "Développeuse",
                    "caroline@lamzone.com"),
            new Employees(8, "Caroline", "Développeuse",
                    "caroline@lamzone.com"),
            new Employees(9, "Caroline", "Développeuse",
                    "caroline@lamzone.com"),
            new Employees(10, "Caroline", "Développeuse",
                    "caroline@lamzone.com"),
            new Employees(11, "Caroline", "Développeuse",
                    "caroline@lamzone.com"),
            new Employees(12, "Caroline", "Développeuse",
                    "caroline@lamzone.com"),
            new Employees(13, "Caroline", "Développeuse",
                    "caroline@lamzone.com")
    );

    static List<Employees> generateEmployees() {
        return new ArrayList<>(DUMMY_EMPLOYEES);
    }
}
