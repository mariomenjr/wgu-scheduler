package Scheduler.Utils;

import Scheduler.Main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
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

    // public static DateTime resolveLocalTimeZone() {}
    public static DateTime resolveRefTimeZone(DateTime dateTime) {
        /*
        * If I cannot make changes to the Database, how am I going to store the TimeZone the date has been saved?
        * */
        LocalDateTime ldt = LocalDateTime.ofInstant(dateTime.toInstant(), dateTime.getTimeZone().toZoneId());
        ZonedDateTime zdt = ZonedDateTime.of(ldt, dateTime.getTimeZone().toZoneId());
        ZonedDateTime gmt = zdt.withZoneSameInstant(Main.getRefTimeZone().toZoneId());

        Calendar c = Calendar.getInstance();
        c.set(gmt.getYear(), gmt.getMonthValue() - 1, gmt.getDayOfMonth(), gmt.getHour(), gmt.getMinute());

        DateTime dt = new DateTime();
        dt.setTime(c.getTime());

        return dt;
    }
}
