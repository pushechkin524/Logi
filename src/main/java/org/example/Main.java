package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Main {

    static Log myLog;
    static{
        try
        {
            myLog = new Log("main.log");
        } catch(
                IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    private int treasury = 0;
    private ArrayList<Tower> towers = new ArrayList<>() {{
        add(new Tower("West", 100));
        add(new Tower("North",100));
        add(new Tower("East", 100));
        add(new Tower("South", 100));
    }};

    private static Army army = new Army("Main Army", 1);

    public static void main(String[] args) {
        try {
            Main main = new Main();
            main.manageTreasury("добавить", 200);
            main.manageTreasury("изъять", 50);

            main.addTower("North-East", 100);
            main.attackTower("East", 20);
            main.repair();
            main.getTotalDefenseStatus();

            System.out.println("Общая численность армии: " + army.getAllArmySize());
            army.addNewTroop(new CommonTroop("Infantry", 100, 10));
            army.addNewTroop(new DISB("Archers", 50, 15));
            System.out.println("Общая численность армии после добавления: " + army.getAllArmySize());
            System.out.println("Общая сила армии: " + army.getAllArmyStrength());
            // вызываем ошибку
            army.addNewTroop(new CommonTroop("AAA", -10,  -10));
        } catch (IOException e) {
            myLog.logger.severe("Произошла ошибка входе выполнения Main.main: %s".formatted(e.toString()));
        }
    }

    public void manageTreasury(String operation, int gold) {
        if (operation.equals("добавить")) {
            treasury += gold;
            myLog.logger.info("Успешное прибавление в казну: %d".formatted(gold));
            System.out.println("В казну добавлено " + gold + " золота. Текущее количество золота в казне: " + treasury);
        } else if (operation.equals("изъять")) {
            if (gold <= treasury) {
                treasury -= gold;
                myLog.logger.info("Успешное изъятия из казны: %d\nОстаток: %d".formatted(gold, treasury));
                System.out.println("Из казны изъято " + gold + " золота. Текущее количество золота в казне: " + treasury);
            } else {
                myLog.logger.info("Недостаточное количество золота для изъятия: %d".formatted(treasury));
                System.out.println("Недостаточно золота в казне");
            }
        } else {
            System.out.println("Неверная операция");
        }
    }

    public void getTotalDefenseStatus() {
        int total = 0;
        for (Tower t : towers) {
            total += t.getHealth();
        }
        myLog.logger.info("Общее здоровье башен %s: %d".formatted(this.getClass().getCanonicalName(), total));
        System.out.printf("Общее здоровье башен: %d\n", total);
    }

    public void addTower(String direction, int defense) {
        if (treasury >= 100) {
            if (defense >= 0 && defense <= 100) {
                towers.add(new Tower(direction, defense));
                treasury -= 100;
                myLog.logger.info("Успешное добавление башни на направлении: %s, с защитой: %d"
                        .formatted(direction, defense));
                System.out.printf("Башня успешно добавлена, в казне: %d\n", treasury);
            } else {
                System.out.println("Значение защиты должно быть больше или равно 0 и меньше или равно 100");
            }
        } else {
            myLog.logger.info("Недостаточное количество денег в казне для постройки башни\n" +
                    "Необходимо: %d\nВ казне: %d".formatted(100, treasury));
            System.out.printf("В казне: %d, стоимость башни: 100\n", treasury);
        }
    }

    public void repair() {
        for (Tower t: towers) {
            t.setHealth(100);
        }
        myLog.logger.info("Успешная починка всех башен %s".formatted(this.getClass().getCanonicalName()));
        System.out.println("Все башни починены!");
    }

    public void attackTower(String direction, int total) {
        for (Tower t: towers) {
            if (Objects.equals(t.getDirection(), direction)) {
                if (total >= t.getHealth()) {
                    myLog.logger.info("Была снесена башня на направлении: %s".formatted(t.getDirection()));
                    towers.remove(t);
                    System.out.println("Башня была снесена");
                } else {
                    t.setHealth(t.getHealth() - total);
                    myLog.logger.info("Атакована бащня на направлении: %s\nСостояние на данный момент: %d"
                            .formatted(t.getDirection(), t.getHealth()));
                    System.out.printf("Остаток здоровья башни: %d\n", t.getHealth());
                }
            }
        }
    }

}