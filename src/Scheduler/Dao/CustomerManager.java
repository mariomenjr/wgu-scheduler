package Scheduler.Dao;

import Scheduler.Main;
import Scheduler.Models.Appointment;
import Scheduler.Models.Customer;
import Scheduler.Repository.BaseManager;
import Scheduler.Utils.DateTime;
import Scheduler.Utils.Parser;

import java.sql.ResultSet;
import java.text.ParseException;
import java.util.GregorianCalendar;

import static Scheduler.Utils.Parser.StringToCalendar;

public class CustomerManager extends BaseManager<Customer> {

    @Override
    protected String instanceToInsertQuery(Customer instance) throws ParseException {
        GregorianCalendar createdOn = new GregorianCalendar();
        GregorianCalendar updatedOn = new GregorianCalendar();

        createdOn.setTime(instance.getCreateDate().getTime());
        updatedOn.setTime(instance.getLastUpdate().getTime());

        return ""
                .concat("INSERT INTO customer VALUES(NULL,")
                .concat("'" + instance.getCustomerName() + "',")
                .concat(Integer.toString(instance.getAddressId()) + ",")
                .concat("'" + (instance.isActive() ? "1":"0") + "',")
                .concat("'" + Parser.CalendarToString(createdOn) + "',")
                .concat("'" + Main.getUser().getUserName() + "',")
                .concat("'" + Parser.CalendarToString(updatedOn) + "',")
                .concat("'" + Main.getUser().getUserName() + "'")
                .concat(");");
    }

    @Override
    protected String instanceToUpdateQuery(Customer instance) throws Exception {
        GregorianCalendar createdOn = new GregorianCalendar();
        GregorianCalendar updatedOn = new GregorianCalendar();

        createdOn.setTime(instance.getCreateDate().getTime());
        updatedOn.setTime(instance.getLastUpdate().getTime());

        return ""
                .concat("UPDATE customer SET ")
                .concat("customerName = '" + instance.getCustomerName() + "',")
                .concat("addressId = " + Integer.toString(instance.getAddressId()) + ",")
                .concat("active = '" + (instance.isActive() ? "1":"0") + "',")
                .concat("createDate = '" + Parser.CalendarToString(createdOn) + "',")
                .concat("createdBy = '" + Main.getUser().getUserName() + "',")
                .concat("lastUpdate = '" + Parser.CalendarToString(updatedOn) + "',")
                .concat("lastUpdateBy = '" + Main.getUser().getUserName() + "'")
                .concat(" WHERE customerId = ")
                .concat(Integer.toString(instance.getCustomerId()));
    }

    @Override
    protected String instanceToDeleteQuery(Customer instance) throws Exception {
        return ""
                .concat("DELETE FROM customer WHERE customerId = ")
                .concat(Integer.toString(instance.getCustomerId()));
    }

    @Override
    protected Customer newInstanceOfEntity(ResultSet resultSet) throws Exception {
        return new Customer(
            resultSet.getInt("customerId"),
            resultSet.getString("customerName"),
            resultSet.getInt("addressId"),
            resultSet.getInt("active") == 1,
            StringToCalendar(resultSet.getString("createDate")),
            resultSet.getString("createdBy"),
            StringToCalendar(resultSet.getString("lastUpdate")),
            resultSet.getString("lastUpdateBy")
        );
    }
}
