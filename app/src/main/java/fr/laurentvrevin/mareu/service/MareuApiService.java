package fr.laurentvrevin.mareu.service;

import java.util.List;

import fr.laurentvrevin.mareu.model.Employees;

public interface MareuApiService {
    List<Employees> getEmployees();
}
