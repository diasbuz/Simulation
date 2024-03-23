package org.project;

import java.util.Objects;

public class Coordinate {
    public Integer getX() {
        if(x == null){
            return null;
        }
        return x;
    }

    public Integer getY() {
        if(y == null){
            return null;
        }
        return y;
    }

    public final Integer x;
    public final Integer y;

    public Coordinate(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return Objects.equals(x, that.x) && Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString()
    {
        return x + " " + y;
    }
}
