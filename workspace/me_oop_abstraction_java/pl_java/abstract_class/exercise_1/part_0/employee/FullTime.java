package pl_java.abstract_class.exercise_1.part_0.employee;

public class FullTime extends Employee{
    
    public FullTime(String firstName, String lastName, double salary){
      super(firstName,lastName);
      calculateSalary(salary);
      System.out.println("Inside FullTimeEmployee Constructor");
    }

    @Override
    protected void calculateSalary(double salary){
      setSalary(salary);
    }
  
    public double getSalary(){
      System.out.println("Inside FullTimeEmployee getSalary Method");
      return super.getSalary();
    }
  }
