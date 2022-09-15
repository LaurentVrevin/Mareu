package fr.laurentvrevin.mareu.service;

import java.util.ArrayList;
import java.util.List;

import fr.laurentvrevin.mareu.model.Employees;

public class DummyMareuApiService implements MareuApiService{
    private final List<Employees> mEmployees = DummyEmployeesGenerator.generateEmployees();
    //private final List<Employees> favoriteNeighbours = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Employees> getEmployees() {
        return mEmployees;
    }
}

