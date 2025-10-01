package pl_java.abstract_class.exercise_1.part_0.employee;

public abstract class Employee{
    private String firstName;
    private String lastName;
    private double salary;
  
    public Employee(String firstName, String lastName){
      this.firstName = firstName;
      this.lastName = lastName;
      System.out.println("Inside Employee Constructor");
    }

    protected abstract void calculateSalary(double salary);

    protected void setSalary(double salary){
      this.salary=salary;
    }
  
    public String fullName(){
      return this.firstName +" "+ this.lastName;
    }
  
    public double getSalary(){
      System.out.println("Inside Employee getSalary Method");
      return salary;
    }
  }  
