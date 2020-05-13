package Scheduler.Dao;

import Scheduler.Main;
import Scheduler.Models.Address;
import Scheduler.Repository.BaseManager;
import Scheduler.Utils.DateTime;
import Scheduler.Utils.Parser;

import java.sql.ResultSet;
import java.text.ParseException;
import java.util.GregorianCalendar;

import static Scheduler.Utils.Parser.StringToCalendar;

public class AddressManager extends BaseManager<Address> {

    @Override
    protected String instanceToInsertQuery(Address instance) throws ParseException {
        GregorianCalendar createdOn = new GregorianCalendar();
        GregorianCalendar updatedOn = new GregorianCalendar();

        createdOn.setTime(instance.getCreateDate().getTime());
        updatedOn.setTime(instance.getLastUpdate().getTime());

        return ""
                .concat("INSERT INTO address VALUES(NULL,")
                .concat("'" + instance.getAddress() + "',")
                .concat("'" + instance.getAddress2() + "',")
                .concat(Integer.toString(instance.getCityId()) + ",")
                .concat("'" + instance.getPostalCode() + "',")
                .concat("'" + instance.getPhone() + "',")
                .concat("'" + Parser.CalendarToString(createdOn) + "',")
                .concat("'" + Main.getUser().getUserName() + "',")
                .concat("'" + Parser.CalendarToString(updatedOn) + "',")
                .concat("'" + Main.getUser().getUserName() + "'")
                .concat(");");
    }

    @Override
    protected String instanceToUpdateQuery(Address instance) throws Exception {
        GregorianCalendar createdOn = new GregorianCalendar();
        GregorianCalendar updatedOn = new GregorianCalendar();

        createdOn.setTime(instance.getCreateDate().getTime());
        updatedOn.setTime(instance.getLastUpdate().getTime());

        return ""
                .concat("UPDATE address SET ")
                .concat("address = '" + instance.getAddress() + "',")
                .concat("address2 = '" + instance.getAddress2() + "',")
                .concat("cityId = " + Integer.toString(instance.getCityId()) + ",")
                .concat("postalCode = '" + instance.getPostalCode() + "',")
                .concat("phone = '" + instance.getPhone() + "',")
                .concat("createDate = '" + Parser.CalendarToString(createdOn) + "',")
                .concat("createdBy = '" + Main.getUser().getUserName() + "',")
                .concat("lastUpdate = '" + Parser.CalendarToString(updatedOn) + "',")
                .concat("lastUpdateBy = '" + Main.getUser().getUserName() + "'")
                .concat(" WHERE addressId = ")
                .concat(Integer.toString(instance.getAddressId()));
    }

    @Override
    protected String instanceToDeleteQuery(Address instance) throws Exception {
        return ""
            .concat("DELETE FROM address WHERE addressId = ")
            .concat(Integer.toString(instance.getAddressId()));
    }

    @Override
    protected Address newInstanceOfEntity(ResultSet resultSet) throws Exception {
        return new Address(
            resultSet.getInt("addressId"),
            resultSet.getString("address"),
            resultSet.getString("address2"),
            resultSet.getInt("cityId"),
            resultSet.getString("postalCode"),
            resultSet.getString("phone"),
            StringToCalendar(resultSet.getString("createDate")),
            resultSet.getString("createdBy"),
            StringToCalendar(resultSet.getString("lastUpdate")),
            resultSet.getString("lastUpdateBy")
        );
    }
}
