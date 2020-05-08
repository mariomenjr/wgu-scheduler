package Scheduler.Dao;

import Scheduler.Models.City;
import Scheduler.Models.Country;
import Scheduler.Repository.BaseManager;
import Scheduler.Utils.Parser;

import java.sql.ResultSet;
import java.text.ParseException;
import java.util.GregorianCalendar;

import static Scheduler.Utils.Parser.StringToCalendar;

public class CountryManager extends BaseManager<Country> {

    @Override
    protected String instanceToInsertQuery(Country instance) throws ParseException {
        GregorianCalendar createdOn = new GregorianCalendar();
        GregorianCalendar updatedOn = new GregorianCalendar();

        createdOn.setTime(instance.getCreateDate().getTime());
        updatedOn.setTime(instance.getLastUpdate().getTime());

        return ""
                .concat("INSERT INTO country VALUES(NULL,")
                .concat("'" + instance.getCountry() + "',")
                .concat("'" + Parser.CalendarToString(createdOn) + "',")
                .concat("'" + "mariomenjr" + "',")
                .concat("'" + Parser.CalendarToString(updatedOn) + "',")
                .concat("'" + "mariomenjr" + "'")
                .concat(");");
    }

    @Override
    protected String instanceToUpdateQuery(Country instance) throws Exception {
        GregorianCalendar createdOn = new GregorianCalendar();
        GregorianCalendar updatedOn = new GregorianCalendar();

        createdOn.setTime(instance.getCreateDate().getTime());
        updatedOn.setTime(instance.getLastUpdate().getTime());

        return ""
                .concat("UPDATE country SET ")
                .concat("country = '" + instance.getCountry() + "',")
                .concat("createDate = '" + Parser.CalendarToString(createdOn) + "',")
                .concat("createdBy = '" + "mariomenjr" + "',")
                .concat("lastUpdate = '" + Parser.CalendarToString(updatedOn) + "',")
                .concat("lastUpdateBy = '" + "mariomenjr" + "'")
                .concat(" WHERE countryId = ")
                .concat(Integer.toString(instance.getCountryId()));
    }

    @Override
    protected String instanceToDeleteQuery(Country instance) throws Exception {
        return ""
                .concat("DELETE FROM country WHERE countryId = ")
                .concat(Integer.toString(instance.getCountryId()));
    }

    @Override
    protected Country newInstanceOfEntity(ResultSet resultSet) throws Exception {
        return new Country(
                resultSet.getInt("countryId"),
                resultSet.getString("country"),
                StringToCalendar(resultSet.getString("createDate")),
                resultSet.getString("createdBy"),
                StringToCalendar(resultSet.getString("lastUpdate")),
                resultSet.getString("lastUpdateBy")
        );
    }
}
