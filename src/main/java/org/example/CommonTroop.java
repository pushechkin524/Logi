package org.example;

import java.io.IOException;

public class CommonTroop extends Troop {

    static Log myLog;
    static{
        try
        {
            myLog = new Log("common.log");
        } catch(
                IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public CommonTroop(String title, int count, int strength) throws IOException {
        super(title, count, strength);
    }

    @Override
    public int getTroopStrength() {
        myLog.logger.info("Получение силы common отряда: %s".formatted(this.getTitle()));
        return count * strength;
    }


}
