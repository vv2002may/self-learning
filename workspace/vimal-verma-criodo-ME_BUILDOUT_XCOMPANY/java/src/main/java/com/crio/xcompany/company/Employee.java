package com.crio.xcompany.company;


public class Employee {
    private String name;
    private Gender gender;
    private String manager;


    // TODO: CRIO_TASK_MODULE_XCOMPANY
    // Please define all the methods required here as mentioned in the XCompany BuildOut Milestone
    // before implementing the logic.
    // This will ensure that the project can be compiled successfully.


    public Employee(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
        this.manager = null;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Gender getGender() {
        return gender;
    }


    public void setGender(Gender gender) {
        this.gender = gender;
    }


    public String getManager() {
        return manager;
    }


    public void setManager(String manager) {
        this.manager = manager;
    }


    @Override
    public String toString() {
        return "Employee [name=" + name + ", gender=" + gender + "]";
    }
}
