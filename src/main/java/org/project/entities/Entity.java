package org.project.entities;

import org.project.Coordinate;

public abstract class Entity {
    public Coordinate coordinate;

    public EntityType type;

    public Entity(Coordinate coordinate, EntityType type) {
        this.coordinate = coordinate;
        this.type = type;
    }

    public Coordinate getCoordinate()
    {
        return coordinate;
    }


    public EntityType getType() {
        return this.type;
    }
}
