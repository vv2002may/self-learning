package pl_java.abstract_class.exercise_1.part_0;

import java.util.List;

import pl_java.abstract_class.exercise_1.part_0.employee.Employee;

import java.util.ArrayList;

public class Payroll{
  List<Employee> employees = new ArrayList<Employee>();

  public void add(Employee e){
    employees.add(e);
  }

  public void print(){
    for(Employee e : employees){
      System.out.println(e.fullName() +"\t Rs."+ e.getSalary());
    }
  }
}
