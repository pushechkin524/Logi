package org.example;

import java.io.IOException;

public abstract class Troop {

    static Log myLog;
    static{
        try
        {
            myLog = new Log("troop.log");
        } catch(
                IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    protected String title;
    protected int count;
    protected int strength;

    public Troop(String _title, int _count, int _strength) throws IOException {
        title = _title;
        if (_count > 0 && _strength > 0) {
            count = _count;
            strength = _strength;
        } else {
            throw new IOException("Некорректные данные у количества и силы");
        }
    }

    protected String getTitle() {
        return title;
    }

    protected  int getStrength() {
        return strength;
    }

    protected int getCount() {
        myLog.logger.info("Получение количество человек в отряде: %s".formatted(this.getTitle()));
        return count;
    }

    protected void addCount(int count) {
        this.count += count;
    }

    public abstract int getTroopStrength();

}
