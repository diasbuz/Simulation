package org.project;

import org.project.entities.Entity;
import org.project.entities.EntityType;

import java.util.*;

public class BFS {

    private WorldMap map;

    public BFS(WorldMap map) {
        this.map = map;
    }

    public Coordinate findNearestObjectWithType(Coordinate start, EntityType type) {
        LinkedList<Coordinate> queue = new LinkedList<>();
        Set<Coordinate> visited = new HashSet<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();
            Entity entity = map.getEntity(current);
            if (entity != null && entity.getType() == type) {
                return current;
            }

            List<Coordinate> neighbors = getAdjacentCoordinates(current);

            for (Coordinate neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        return null;
    }

    public Coordinate findNearestCoordinate(Coordinate start, Coordinate target) {
        LinkedList<Coordinate> queue = new LinkedList<>();
        Set<Coordinate> visited = new HashSet<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();

            if (isAdjacent(current, target) && map.isSquareEmpty(current)) {
                return current;
            }

            List<Coordinate> neighbors = getAdjacentCoordinates(current);

            boolean foundFreeCoordinate = false;
            for (Coordinate neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
                if (map.isSquareEmpty(neighbor)) {
                    foundFreeCoordinate = true;
                }
            }

            if (!foundFreeCoordinate) {
                return current;
            }
        }

        return null;
    }

    private boolean isAdjacent(Coordinate a, Coordinate b) {
        int deltaX = Math.abs(a.getX() - b.getX());
        int deltaY = Math.abs(a.getY() - b.getY());
        return deltaX <= 1 && deltaY <= 1 && (deltaX + deltaY != 0);
    }

    private List<Coordinate> getAdjacentCoordinates(Coordinate coordinate) {
        List<Coordinate> adjacentCoordinates = new ArrayList<>();
        int x = coordinate.getX();
        int y = coordinate.getY();

        if (x > 1) {
            adjacentCoordinates.add(new Coordinate(x - 1, y));
        }
        if (x < map.getMapSize()) {
            adjacentCoordinates.add(new Coordinate(x + 1, y));
        }
        if (y > 1) {
            adjacentCoordinates.add(new Coordinate(x, y - 1));
        }
        if (y < map.getMapSize()) {
            adjacentCoordinates.add(new Coordinate(x, y + 1));
        }
        return adjacentCoordinates;
    }
}
