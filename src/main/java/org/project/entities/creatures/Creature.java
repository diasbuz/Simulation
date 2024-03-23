package org.project.entities.creatures;

import org.project.*;

import org.project.entities.Entity;
import org.project.entities.EntityType;

import java.util.ArrayList;

public abstract class Creature extends Entity {
    public final Integer speed;
    public Creature(Coordinate coordinate, EntityType type, Integer speed) {
        super(coordinate, type);
        this.speed = speed;
    }

    public abstract void makeMove(Coordinate coordinate, WorldMap worldMap, ArrayList<Creature> creatures);

}
