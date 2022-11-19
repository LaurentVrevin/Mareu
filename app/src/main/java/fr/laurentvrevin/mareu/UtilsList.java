package fr.laurentvrevin.mareu;

import java.util.ArrayList;

import fr.laurentvrevin.mareu.model.Employee;

public class UtilsList {
    static public String listEmployeesToString(ArrayList<Employee> employees){
        StringBuilder employeestostring = new StringBuilder();
        String prefixe = "";
        for (Employee e : employees){
            employeestostring.append(prefixe);
            prefixe = ", ";
            employeestostring.append(e.getEmail());
        }
        return employeestostring.toString();
    }
}
