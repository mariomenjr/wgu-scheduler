package Scheduler.Dao;

import Scheduler.Main;
import Scheduler.Models.City;
import Scheduler.Repository.BaseManager;
import Scheduler.Utils.Parser;

import java.sql.ResultSet;
import java.text.ParseException;
import java.util.GregorianCalendar;

import static Scheduler.Utils.Parser.StringToCalendar;

public class CityManager extends BaseManager<City> {

    @Override
    protected String instanceToInsertQuery(City instance) throws ParseException {
        GregorianCalendar createdOn = new GregorianCalendar();
        GregorianCalendar updatedOn = new GregorianCalendar();

        createdOn.setTime(instance.getCreateDate().getTime());
        updatedOn.setTime(instance.getLastUpdate().getTime());

        return ""
                .concat("INSERT INTO city VALUES(NULL,")
                .concat("'" + instance.getCity() + "',")
                .concat(Integer.toString(instance.getCountryId()) + ",")
                .concat("'" + Parser.CalendarToString(createdOn) + "',")
                .concat("'" + Main.getUser().getUserName() + "',")
                .concat("'" + Parser.CalendarToString(updatedOn) + "',")
                .concat("'" + Main.getUser().getUserName() + "'")
                .concat(");");
    }

    @Override
    protected String instanceToUpdateQuery(City instance) throws Exception {
        GregorianCalendar createdOn = new GregorianCalendar();
        GregorianCalendar updatedOn = new GregorianCalendar();

        createdOn.setTime(instance.getCreateDate().getTime());
        updatedOn.setTime(instance.getLastUpdate().getTime());

        return ""
                .concat("UPDATE city SET ")
                .concat("city = '" + instance.getCity() + "',")
                .concat("countryId = " + Integer.toString(instance.getCountryId()) + ",")
                .concat("createDate = '" + Parser.CalendarToString(createdOn) + "',")
                .concat("createdBy = '" + Main.getUser().getUserName() + "',")
                .concat("lastUpdate = '" + Parser.CalendarToString(updatedOn) + "',")
                .concat("lastUpdateBy = '" + Main.getUser().getUserName() + "'")
                .concat(" WHERE cityId = ")
                .concat(Integer.toString(instance.getCityId()));
    }

    @Override
    protected String instanceToDeleteQuery(City instance) throws Exception {
        return ""
                .concat("DELETE FROM city WHERE cityId = ")
                .concat(Integer.toString(instance.getCityId()));
    }

    @Override
    protected City newInstanceOfEntity(ResultSet resultSet) throws Exception {
        return new City(
            resultSet.getInt("cityId"),
            resultSet.getString("city"),
            resultSet.getInt("countryId"),
            StringToCalendar(resultSet.getString("createDate")),
            resultSet.getString("createdBy"),
            StringToCalendar(resultSet.getString("lastUpdate")),
            resultSet.getString("lastUpdateBy")
        );
    }
}
