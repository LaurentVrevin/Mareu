package fr.laurentvrevin.mareu.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.laurentvrevin.mareu.model.Employees;

public class DummyEmployeesGenerator {
    public static ArrayList<Employees> DUMMY_EMPLOYEES = new ArrayList<>(Arrays.asList(
            new Employees(1, "Juliette", "Manager",
                    "juliette@lamzone.com"),
            new Employees(2, "Lydie", "DRH",
                    "lydie@lamzone.com"),
            new Employees(3, "Laura", "Designeuse",
                    "laura@lamzone.com"),
            new Employees(4, "Laurent", "Développeur",
                    "laurent@lamzone.com"),
            new Employees(5, "Carla", "Manager",
                    "carla@lamzone.com"),
            new Employees(6, "Elodie", "Commerciale",
                    "elodie@lamzone.com"),
            new Employees(7, "Léa", "Marketing",
                    "lea@lamzone.com"),
            new Employees(8, "Camille", "Graphiste",
                    "camille@lamzone.com"),
            new Employees(9, "Andréa", "Développeuse",
                    "andrea@lamzone.com"),
            new Employees(10, "Justine", "Commerciale",
                    "justine@lamzone.com"),
            new Employees(11, "Noémie", "Développeuse",
                    "noemie@lamzone.com"),
            new Employees(12, "Eliya", "Développeuse",
                    "eliya@lamzone.com"),
            new Employees(13, "Marine", "Développeuse",
                    "marine@lamzone.com")
    ));

    static List<Employees> generateEmployees() {
        return new ArrayList<>(DUMMY_EMPLOYEES);
    }
}
