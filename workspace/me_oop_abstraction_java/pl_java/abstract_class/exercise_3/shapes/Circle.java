package pl_java.abstract_class.exercise_3.shapes;

public class Circle {
    private double radius;

    public Circle(double radius){
        setRadius(radius);
    }
    public double getRadius(){
        return radius;
    }
    public void setRadius(double radius){
        this.radius=radius;
    }
    public  double calculateArea(){
        return (3.14*radius*radius);
    }
    public double calculateCircumference(){
        return (2*3.14*radius);
    }
}
