package org.project.entities.creatures;

import org.project.*;
import org.project.entities.EntityType;

import java.util.ArrayList;

public class Predator extends Creature {
    private Integer ATK;

    public Predator(Coordinate coordinate, EntityType type, Integer speed, Integer ATK) {
        super(coordinate, type, speed);
        this.ATK = ATK;
    }

    @Override
    public void makeMove(Coordinate newCoordinate, WorldMap worldMap, ArrayList<Creature> creatures) {
        if (worldMap.getEntity(newCoordinate) instanceof Herbivore) {
            Herbivore prey = (Herbivore) worldMap.getEntity(newCoordinate);
            prey.takeATK(this.ATK);
            if (prey.getHP() <= 0) {
                worldMap.removeEntity(newCoordinate);
                creatures.remove(prey);
            }
        } else {
            worldMap.moveEntity(this.coordinate, newCoordinate);
            this.coordinate = newCoordinate;
        }
    }
}
