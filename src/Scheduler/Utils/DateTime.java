package Scheduler.Utils;

import java.util.GregorianCalendar;

public class DateTime extends GregorianCalendar {
    @Override
    public String toString() {
        return this.getTime().toLocaleString();
    }
}
