import java.util.LinkedList;

public class Snake {
    private int length;
    private LinkedList<Position> positions = new LinkedList<>();
    private Direction direction;

    public Snake(int length, Position startPosition, Direction direction) {
        this.length = length;
        this.direction = direction;
        positions.add(startPosition);

        // Initialize the snake's body based on its direction
        for (int i = 1; i < length; i++) {
            int x = startPosition.getX();
            int y = startPosition.getY();

            if (direction == Direction.RIGHT) {
                x -= i;
            } else if (direction == Direction.LEFT) {
                x += i;
            } else if (direction == Direction.UP) {
                y -= i;
            } else if (direction == Direction.DOWN) {
                y += i;
            }
            positions.addLast(new Position(x, y));
        }
    }

    // Getters and setters
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        if (direction == Direction.RIGHT && this.direction==Direction.LEFT) {
           return;
        } else if (direction == Direction.LEFT && this.direction==Direction.RIGHT) {
            return;
        } else if (direction == Direction.DOWN && this.direction==Direction.UP) {
            return;
        } else if (direction == Direction.UP && this.direction==Direction.DOWN) {
            return;
        }
        this.direction = direction;
    }

    public LinkedList<Position> getBody() {
        return positions;
    }

    // Method to move the snake in its current direction
    public void move() {
        Position head = positions.getFirst();
        int x = head.getX();
        int y = head.getY();

        if (direction == Direction.RIGHT) {
            x += 1;
        } else if (direction == Direction.LEFT) {
            x -= 1;
        } else if (direction == Direction.UP) {
            y += 1;
        } else if (direction == Direction.DOWN) {
            y -= 1;
        }

        // Add the new head position
        positions.addFirst(new Position(x, y));
        // Remove the tail to maintain the length
        positions.removeLast();
    }

    // Method to grow the snake (increase length)
    public void grow() {
        length++;
        // Adds an extra segment to the snake by not removing the tail
        Position head = positions.getFirst();
        int x = head.getX();
        int y = head.getY();
        if (direction == Direction.RIGHT) {
            x += 1;
        } else if (direction == Direction.LEFT) {
            x -= 1;
        } else if (direction == Direction.UP) {
            y += 1;
        } else if (direction == Direction.DOWN) {
            y -= 1;
        }

        // Add the new head position
        positions.addFirst(new Position(x, y));
    }

}