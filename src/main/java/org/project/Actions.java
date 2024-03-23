package org.project;

import org.project.entities.*;
import org.project.entities.creatures.Creature;
import org.project.entities.creatures.Herbivore;
import org.project.entities.creatures.Predator;

import java.util.ArrayList;

public class Actions {
    WorldMap worldMap;

    Actions(WorldMap worldMap)
    {
        this.worldMap = worldMap;
    }

    public ArrayList<Creature> initActions()
    {
        ArrayList<Creature> creatures = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Coordinate coordinate = generatePosition();
            worldMap.setEntity(coordinate, new Rock(coordinate, EntityType.Rock));
        }

        for (int i = 0; i < 5; i++) {
            Coordinate coordinate = generatePosition();
            worldMap.setEntity(coordinate, new Tree(coordinate, EntityType.Tree));
        }

        for (int i = 0; i < 15; i++) {
            Coordinate coordinate = generatePosition();
            worldMap.setEntity(coordinate, new Grass(coordinate, EntityType.Grass));
        }

        for (int i = 0; i < 8; i++) {
            Coordinate coordinate = generatePosition();
            worldMap.setEntity(coordinate, new Herbivore(coordinate, EntityType.Herbivore, 1, 1));
            creatures.add(new Herbivore(coordinate, EntityType.Herbivore, 1, 1));
        }

        for (int i = 0; i < 5; i++) {
            Coordinate coordinate = generatePosition();
            worldMap.setEntity(coordinate, new Predator(coordinate, EntityType.Predator, 1,1));
            creatures.add(new Predator(coordinate, EntityType.Predator, 1,1));
        }
        return creatures;
    }

    public void turnActions(ArrayList<Creature> creatures) {
        for (Creature creature : creatures) {
            BFS bfs = new BFS(worldMap);
            EntityType targetType;
            if (creature instanceof Herbivore) {
                targetType = EntityType.Grass;
                if(((Herbivore) creature).getHP()<=0)
                {
                    creatures.remove(creature);
                }
            } else {
                targetType = EntityType.Herbivore;
            }
            Coordinate targetCoordinate = bfs.findNearestObjectWithType(creature.getCoordinate(), targetType);
            if (targetCoordinate != null) {
                Coordinate newCoordinate = bfs.findNearestCoordinate(creature.getCoordinate(), targetCoordinate);
                if(newCoordinate == null)
                {
                    creatures.remove(creature);
                    worldMap.removeEntity(creature.getCoordinate());
                }
                else {
                    creature.makeMove(newCoordinate, worldMap, creatures);
                }
            }
        }
    }


    public Coordinate generatePosition()
    {
        Integer x = (int)(Math.random()* worldMap.MAP_SIZE)+1;
        Integer y = (int)(Math.random()* worldMap.MAP_SIZE)+1;
        while (!worldMap.isSquareEmpty(new Coordinate(x,y)))
        {
            x = (int)(Math.random()* worldMap.MAP_SIZE)+1;
            y = (int)(Math.random()* worldMap.MAP_SIZE)+1;
        }
        return new Coordinate(x,y);
    }
}