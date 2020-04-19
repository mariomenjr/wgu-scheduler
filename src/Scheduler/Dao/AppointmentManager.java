package Scheduler.Dao;

import Scheduler.Models.Appointment;
import Scheduler.Repository.BaseManager;
import Scheduler.Utils.Parser;

import java.sql.ResultSet;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static Scheduler.Utils.Parser.StringToCalendar;

public class AppointmentManager extends BaseManager<Appointment> {
    @Override
    protected String instanceToInsertQuery(Appointment instance) throws Exception {
        GregorianCalendar start = new GregorianCalendar();
        GregorianCalendar end = new GregorianCalendar();
        GregorianCalendar createdOn = new GregorianCalendar();
        GregorianCalendar updatedOn = new GregorianCalendar();

        start.setTime(instance.getStart().getTime());
        end.setTime(instance.getEnd().getTime());
        createdOn.setTime(instance.getCreateDate().getTime());
        updatedOn.setTime(instance.getLastUpdate().getTime());

        return ""
                .concat("INSERT INTO appointment VALUES(NULL,")
                .concat(Integer.toString(instance.getCustomerId()) + ",")
                .concat(Integer.toString(instance.getUserId()) + ",")
                .concat("'" + instance.getTitle() + "',")
                .concat("'" + instance.getDescription() + "',")
                .concat("'" + instance.getLocation() + "',")
                .concat("'" + instance.getContact() + "',")
                .concat("'" + instance.getType() + "',")
                .concat("'" + instance.getUrl() + "',")
                .concat("'" + Parser.CalendarToString(start) + "',")
                .concat("'" + Parser.CalendarToString(end) + "',")
                .concat("'" + Parser.CalendarToString(createdOn) + "',")
                .concat("'" + "mariomenjr" + "',")
                .concat("'" + Parser.CalendarToString(updatedOn) + "',")
                .concat("'" + "mariomenjr" + "'")
                .concat(");");
    }

    @Override
    protected Appointment newInstanceOfEntity(ResultSet resultSet) throws Exception {
        return new Appointment(
            resultSet.getInt("appointmentId"),
            resultSet.getInt("customerId"),
            resultSet.getInt("userId"),
            resultSet.getString("title"),
            resultSet.getString("description"),
            resultSet.getString("location"),
            resultSet.getString("contact"),
            resultSet.getString("type"),
            resultSet.getString("url"),
            StringToCalendar(resultSet.getString("start")),
            StringToCalendar(resultSet.getString("end")),
            StringToCalendar(resultSet.getString("createDate")),
            resultSet.getString("createdBy"),
            StringToCalendar(resultSet.getString("lastUpdate")),
            resultSet.getString("lastUpdateBy")
        );
    }
}
