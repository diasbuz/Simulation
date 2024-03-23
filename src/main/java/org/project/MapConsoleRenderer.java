package org.project;

import org.project.entities.Entity;

public class MapConsoleRenderer {

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_BACKGROUND = "\u001B[43m";

    public String render(WorldMap worldMap)
    {
        StringBuilder board = new StringBuilder();
        for (int i = 1; i <= worldMap.MAP_SIZE; i++) {
            for (int j = 1; j <= worldMap.MAP_SIZE; j++) {
                Coordinate coordinate = new Coordinate(i,j);
                if(worldMap.isSquareEmpty(coordinate))
                {
                    board.append(getSpriteForEmptySquare(coordinate));
                }
                else {
                    board.append(getEntitiesSprite(worldMap.getEntity(coordinate)));
                }
            }
            board.append(ANSI_RESET).append("\n");

        }
        return board.toString();
    }

    private String getEntitiesSprite(Entity entity) {
        return colorizeSprite(getEntitiesUnicode(entity) + " ");
    }


    public String colorizeSprite(String sprite)
    {
        //format = background + font + text;
        return ANSI_BACKGROUND + sprite;
    }

    private String getSpriteForEmptySquare(Coordinate coordinate)
    {
        return colorizeSprite("   ");
    }

    private String getEntitiesUnicode(Entity entity)
    {
        switch (entity.type.toString())
        {
            case "Rock":
                return "⬛";
            case "Grass":
                return "\uD83C\uDF40";
            case "Tree":
                return "\uD83C\uDF33";
            case "Herbivore":
                return "\uD83D\uDC2E";
            case "Predator":
                return "\uD83D\uDC3A";
        }
        return "";
    }
}
