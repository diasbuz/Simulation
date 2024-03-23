package org.project;

import org.project.entities.creatures.Creature;

import java.util.ArrayList;

import java.util.concurrent.TimeUnit;
public class Simulation {
    WorldMap worldMap = new WorldMap();

    ArrayList<Creature> creatures = new ArrayList<>();
    int stage = 0;
    MapConsoleRenderer mcr = new MapConsoleRenderer();
    Actions actions = new Actions(worldMap);

    public void startSimulation() throws InterruptedException {
        creatures = actions.initActions();
        while (true) {
           System.out.println(mcr.render(worldMap));
            actions.turnActions(creatures);
            stage++;
            TimeUnit.SECONDS.sleep(1);
//            System.out.println("-----------------------------------------------");
        }
    }

}
