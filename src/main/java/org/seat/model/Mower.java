package org.seat.model;

import org.seat.enums.Direction;

public class Mower {

    private int x;
    private int y;
    private Direction direction;
    private String movements;

    public Mower(int x, int y, Direction direction, String movements) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.movements = movements;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getMovements() {
        return movements;
    }

    public void setMovements(String movements) {
        this.movements = movements;
    }

    @Override
    public String toString() {
        return x + " " + y + " " + direction;
    }
}
