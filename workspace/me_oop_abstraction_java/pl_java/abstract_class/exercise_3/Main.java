package pl_java.abstract_class.exercise_3;

import pl_java.abstract_class.exercise_3.shapes.Circle;
import pl_java.abstract_class.exercise_3.shapes.Rectangle;
import pl_java.abstract_class.exercise_3.shapes.Triangle;

class Main {
    public static void main(String[] args) {
          // Rectangle test
          double width = 5, length = 7;
          Rectangle rectangle = new Rectangle(width, length);
          System.out.println("Rectangle width: " + width + " and length: " + length
                  + "\nResulting area: " + rectangle.calculateArea()
                  + "\nResulting perimeter: " + rectangle.calculateCircumference() + "\n");
  
          // Circle test
          double radius = 5;
          Circle circle = new Circle(radius);
          System.out.println("Circle radius: " + radius
              + "\nResulting Area: " + circle.calculateArea()
              + "\nResulting Perimeter: " + circle.calculateCircumference() + "\n");
  
          // Triangle test
          double tWidth = 5, tLength = 7;
          Triangle triangle = new Triangle(tWidth,tLength);
          System.out.println("Triangle : " + tWidth + ", " + tLength + 
          "\nResulting Area: " + triangle.calculateArea()
                  + "\nResulting Perimeter: " + triangle.calculateCircumference() + "\n");
    }
  }
