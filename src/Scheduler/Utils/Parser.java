package Scheduler.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Parser {
    public static Calendar stringToCalendar(String str) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(str));
        return calendar;
    }
}
