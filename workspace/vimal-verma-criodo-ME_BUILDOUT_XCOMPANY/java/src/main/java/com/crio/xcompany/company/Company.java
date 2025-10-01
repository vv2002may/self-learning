package com.crio.xcompany.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


public class Company {
    private String companyName;
    private Employee founder;
    private Map<String, Employee> employeeBook;
    private Map<String, List<Employee>> managerBook;


    private Company(String companyName, Employee founder) {
        this.companyName = companyName;
        this.founder = founder;
        employeeBook = new HashMap<String, Employee>();
        employeeBook.put(founder.getName(), founder);
        managerBook = new HashMap<String, List<Employee>>();
    }


    public static Company create(String companyName, Employee founder) {
        return new Company(companyName, founder);
    }


    public String getCompanyName() {
        return companyName;
    }

    public void registerEmployee(String employeeName, Gender gender) {
        Employee newEmployee = new Employee(employeeName, gender);
        employeeBook.put(employeeName, newEmployee);
    };

    public Employee getEmployee(String employeeName) {
        return employeeBook.get(employeeName);
    }

    public void assignManager(String employeeName, String managerName) {
        Employee employee = employeeBook.get(employeeName);
        List<Employee> employees = managerBook.getOrDefault(managerName, new ArrayList<>());
        employees.add(employee);
        managerBook.put(managerName, employees);
        employee.setManager(managerName);
        return;
    }


    public List<Employee> getDirectReports(String managerName) {
        return managerBook.get(managerName);
    }


    public List<Employee> getTeamMates(String employeeName) {
        Employee employee = employeeBook.get(employeeName);
        String manager = employee.getManager();
        List<Employee> employees = new ArrayList<>(managerBook.get(manager));
        Employee managerEmp = employeeBook.get(manager);
        employees.add(0, managerEmp);
        return employees;
    }


    public void deleteEmployee(String employeeName) {
        employeeBook.remove(employeeName);
    }


    public List<List<Employee>> getEmployeeHierarchy(String managerName) {
        
        List<List<Employee>> employeeGroups = new ArrayList<>();
        Employee manager = employeeBook.get(managerName);
        List<Employee> employee1 = new ArrayList<>();
        employee1.add(manager);
        employeeGroups.add(employee1);
        
        Queue<Employee> eq = new LinkedList<>();
        eq.add(manager);
        while (!eq.isEmpty()) {
            Employee employee = eq.poll();
            List<Employee> employees = managerBook.getOrDefault(employee.getName(),null);

            if (employees != null) {
                employeeGroups.add(employees);

                for (Employee emp : employees) {
                    eq.add(emp);
                }
            }

        }

        return employeeGroups;
    }



    // TODO: CRIO_TASK_MODULE_XCOMPANY
    // Please define all the methods required here as mentioned in the XCompany BuildOut Milestone
    // for each functionality before implementing the logic.
    // This will ensure that the project can be compiled successfully.

}
