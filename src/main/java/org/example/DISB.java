package org.example;

import java.io.IOException;

public class DISB extends Troop{

    static Log myLog;
    static{
        try
        {
            myLog = new Log("disb.log");
        } catch(
                IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public DISB(String title, int count, int strength) throws IOException {
        super(title, count, strength);
    }

    @Override
    public int getTroopStrength() {
        int weapon = 10;
        myLog.logger.info("Получение силы disb отряда: %s".formatted(this.getTitle()));
        return count * (strength + weapon);
    }


}
