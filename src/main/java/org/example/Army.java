package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Army {

    static Log myLog;
    static{
        try
        {
            myLog = new Log("army.log");
        } catch(
                IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    private String title;
    private int count;
    private ArrayList<Troop> troops = new ArrayList<>() {{
        try {
            add(new CommonTroop("CCC", 810, 5));
            add(new DISB("AAA", 101, 12));
            add(new DISB("BBB", 122, 13));
        } catch (IOException e) {
            myLog.logger.severe("Ошибка при создании отрядов: %s".formatted(e.toString()));
        }
    }};

    public Army(String title, int count) {
        this.title = title;
        this.count = count;
    }

    public void addNewTroop(Troop troop) {
        boolean found = false;

        ArrayList<Troop> newTroops = new ArrayList<>();
        for (Troop t : troops) {
            if (Objects.equals(troop.getTitle(), t.getTitle()) && t.getStrength() == troop.getStrength()) {
                t.addCount(troop.getCount());
                found = true;
                break;
            }
        }
        if (!found) {
            newTroops.add(troop);
        }
        myLog.logger.info("Успешное добавление нового отряда в армию %s".formatted(troop.getTitle()));
        troops.addAll(newTroops);
    }

    public int getAllArmySize() {
        int total = 0;
        for (Troop t: troops) {
            total += t.getCount();
        }
        myLog.logger.info("Успешное получение размера армии");
        return total * count;
    }

    public int getAllArmyStrength() {
        int total = 0;
        for (Troop t: troops) {
            total += t.getCount() * t.getStrength();
        }
        myLog.logger.info("Успешное получение силы армии");
        return total * count;
    }

}
