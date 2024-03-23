package org.project.entities.creatures;

import org.project.*;
import org.project.entities.EntityType;

import java.util.ArrayList;

public class Herbivore extends Creature {
    private Integer HP;

    public Herbivore(Coordinate coordinate, EntityType type, Integer speed, Integer HP) {
        super(coordinate, type, speed);
        this.HP = HP;
    }

    public Integer getHP() {
        return HP;
    }

    public void takeATK(Integer ATK) {
        HP -= ATK;
    }

    @Override
    public void makeMove(Coordinate newCoordinate, WorldMap worldMap, ArrayList<Creature> creatures) {
        if (HP <= 0) {
            worldMap.removeEntity(this.coordinate);
            creatures.remove(this);
        } else {
            if (worldMap.isSquareEmpty(newCoordinate)) {
                worldMap.moveEntity(this.coordinate, newCoordinate);
                this.coordinate = newCoordinate;
            }
        }
    }
}
