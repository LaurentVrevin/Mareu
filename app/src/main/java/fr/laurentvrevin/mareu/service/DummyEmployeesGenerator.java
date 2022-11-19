package fr.laurentvrevin.mareu.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.laurentvrevin.mareu.model.Employee;

public class DummyEmployeesGenerator {
    public static ArrayList<Employee> DUMMY_EMPLOYEES = new ArrayList<>(Arrays.asList(
            new Employee(1, "Juliette", "Manager",
                    "juliette@lamzone.com"),
            new Employee(2, "Lydie", "DRH",
                    "lydie@lamzone.com"),
            new Employee(3, "Laura", "Designeuse",
                    "laura@lamzone.com"),
            new Employee(4, "Laurent", "Développeur",
                    "laurent@lamzone.com"),
            new Employee(5, "Carla", "Manager",
                    "carla@lamzone.com"),
            new Employee(6, "Elodie", "Commerciale",
                    "elodie@lamzone.com"),
            new Employee(7, "Léa", "Marketing",
                    "lea@lamzone.com"),
            new Employee(8, "Camille", "Graphiste",
                    "camille@lamzone.com"),
            new Employee(9, "Andréa", "Développeuse",
                    "andrea@lamzone.com"),
            new Employee(10, "Justine", "Commerciale",
                    "justine@lamzone.com"),
            new Employee(11, "Noémie", "Développeuse",
                    "noemie@lamzone.com"),
            new Employee(12, "Eliya", "Développeuse",
                    "eliya@lamzone.com"),
            new Employee(13, "Marine", "Développeuse",
                    "marine@lamzone.com")
    ));

    static List<Employee> generateEmployees() {
        return new ArrayList<>(DUMMY_EMPLOYEES);
    }
}
