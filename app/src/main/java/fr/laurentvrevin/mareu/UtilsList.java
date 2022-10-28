package fr.laurentvrevin.mareu;

import java.util.ArrayList;

import fr.laurentvrevin.mareu.model.Employees;

public class UtilsList {
    static public String listEmployeesToString(ArrayList<Employees> employees){
        StringBuilder employeestostring = new StringBuilder();
        String prefixe = "";
        for (Employees e : employees){
            employeestostring.append(prefixe);
            prefixe = ", ";
            employeestostring.append(e.getEmail());
        }
        return employeestostring.toString();
    }
}
