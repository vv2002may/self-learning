package pl_java.exercise_3;

public class Snake {

    /*
     * Attributes
     */
    String dir[] = {"EAST", "NORTH", "WEST", "SOUTH"};
    private int length;
    private String color;
    private String direction;


    /*
     * Constructors
     */
    public Snake(String color, int length, String direction) {
        setColor(color);
        setLength(length);
        for (String i : dir) {
            if (i.equals(direction)) {
                setDirection(direction);
                return;
            }
        }
        System.out
                .println("Error: Direction can only be {\"EAST\", \"NORTH\", \"WEST\", \"SOUTH\"}");
    }

    public Snake(String direction) {
        setColor("Green");
        setLength(3);
        for (String i : dir) {
            if (i.equals(direction)) {
                setDirection(direction);
                return;
            }
        }
        System.out
                .println("Error: Direction can only be {\"EAST\", \"NORTH\", \"WEST\", \"SOUTH\"}");
    }
    /*
     * Getters
     */

    public String getColor() {
        return this.color;
    }

    public int getLength() {
        return this.length;
    }

    public String getDirection() {
        return this.direction;
    }


    /*
     * Setters
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setColor(String color) {
        this.color = color;
    }

    /*
     * Methods
     */

    public void modifyLength(int length) {
        this.length = this.length + length;
    }

    public void turnLeft() {
        for (int i = 0; i < 4; i++) {
            if (direction.equals(dir[i])) {
                direction = dir[(i + 1) % 4];
                break;
            }
        }
    }

    public void turnRigth() {
        for (int i = 0; i < 4; i++) {
            if (direction.equals(dir[i])) {
                if (i == 0)
                    direction = dir[3];
                else
                    direction = dir[(i - 1)];
                break;
            }
        }
    }

}
