package pl_java.abstract_class.exercise_1.part_0.employee;

public class Hourly extends Employee{
  
    private double workedHours;
    private double rate;
    
    public Hourly(String firstName, String lastName, double workedHours, double rate){
      super(firstName,lastName);
      this.rate = rate;
      this.workedHours = workedHours;
      calculateSalary(this.rate * this.workedHours);
      System.out.println("Inside HourlyEmployee Constructor");
    }

    protected void calculateSalary(double salary){
      setSalary(salary);
    }

    @Override
    public double getSalary(){
      System.out.println("Inside HourlyEmployee getSalary Method");
      return super.getSalary();
    }
  }
