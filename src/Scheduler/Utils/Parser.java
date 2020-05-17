package Scheduler.Utils;

import Scheduler.Main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;  

public class Parser {
    // From DB to UI
    public static Calendar StringToCalendar(String str) throws ParseException {
        Calendar calendar = Calendar.getInstance();

        // Before presentation
        LocalDateTime ldt = LocalDateTime.ofInstant(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str).toInstant(), ZoneId.systemDefault());
        ZonedDateTime utc = ldt.atZone(Main.getRefTimeZone().toZoneId());
        ZonedDateTime zdt = utc.withZoneSameInstant(ZoneId.systemDefault());

        calendar.set(zdt.getYear(), zdt.getMonthValue() - 1, zdt.getDayOfMonth(), zdt.getHour(), zdt.getMinute());
        return calendar;
    }
    // From UI to DB
    public static String CalendarToString(Calendar dt) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(resolveRefTimeZoneForDb(dt).getTime());
    }

    // public static DateTime resolveLocalTimeZone() {}
    public static Calendar resolveRefTimeZoneForDb(Calendar dateTime) {
        /*
        * If I cannot make changes to the Database, how am I going to store the TimeZone the date has been saved?
        * */
        LocalDateTime ldt = LocalDateTime.ofInstant(dateTime.toInstant(), ZoneId.systemDefault());
        ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
        ZonedDateTime utc = zdt.withZoneSameInstant(Main.getRefTimeZone().toZoneId());

        Calendar c = Calendar.getInstance();
        c.set(utc.getYear(), utc.getMonthValue() - 1, utc.getDayOfMonth(), utc.getHour(), utc.getMinute());

        return c;
    }
}
