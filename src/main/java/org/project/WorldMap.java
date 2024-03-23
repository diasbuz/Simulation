package org.project;

import org.project.entities.*;

import java.util.HashMap;

public class WorldMap {

    public final Integer MAP_SIZE = 15;

    private HashMap<Coordinate, Entity> entities = new HashMap<>();

    public void setEntity(Coordinate coordinate, Entity entity)
    {
        entity.coordinate = coordinate;
        entities.put(coordinate, entity);
    }
    public void removeEntity(Coordinate coordinate)
    {
        entities.remove(coordinate);
    }

    public boolean isSquareEmpty(Coordinate coordinate)
    {
        return !entities.containsKey(coordinate);
    }

    public Entity getEntity(Coordinate coordinate)
    {
        return entities.get(coordinate);
    }

    public int getMapSize() {
        return MAP_SIZE;
    }

    public void moveEntity(Coordinate from, Coordinate to) {
        if (entities.containsKey(from)) {
            Entity entity = entities.remove(from);
            entities.put(to, entity);
        }
    }
}
