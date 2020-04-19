package Scheduler.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Parser {
    public static Calendar StringToCalendar(String str) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(str));
        return calendar;
    }

    public static String CalendarToString(Calendar dt) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(dt.getTime());
    }
}
