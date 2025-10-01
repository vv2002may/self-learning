package pl_java.abstract_class.exercise_3.shapes;

public class Triangle {
    private double height;
    private double base;

    public Triangle(double height, double base){
        setHeight(height);
        setWidth(base);
    }

    public double getHeight(){
        return height;
    }
    public double getBase(){
        return base;
    }
    public void setHeight(double height){
        this.height=height;
    }
    public void setWidth(double base){
        this.base=base;
    }
    public  double calculateArea(){
        return (.5*base*height);
    }
    public double calculateCircumference(){
        return (base+height+Math.sqrt(base*base + height*height));
    }
}
