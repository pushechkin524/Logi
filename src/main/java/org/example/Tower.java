package org.example;

import java.io.IOException;

public class Tower {

    static Log myLog;
    static{
        try
        {
            myLog = new Log("tower.log");
        } catch(
                IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    private String direction;
    private int health;

    public int getHealth() {
        myLog.logger.info("Получение состояния башни: %s".formatted(this.getDirection()));
        return health;
    }

    public String getDirection() {
        return direction;
    }

    public Tower(String direction, int health) {
        this.direction = direction;
        this.health = health;
    }

    public void setHealth(int health) {
        myLog.logger.info("Установка состояния башни с: %d  до: %d".formatted(this.health, health));
        this.health = health;
    }
}
