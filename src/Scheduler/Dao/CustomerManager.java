package Scheduler.Dao;

import Scheduler.Models.Appointment;
import Scheduler.Models.Customer;
import Scheduler.Repository.BaseManager;

import java.sql.ResultSet;
import java.text.ParseException;

import static Scheduler.Utils.Parser.StringToCalendar;

public class CustomerManager extends BaseManager<Customer> {
    @Override
    protected String instanceToInsertQuery(Customer instance) {
        return null;
    }

    @Override
    protected String instanceToDeleteQuery(Customer instance) throws ParseException, Exception {
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
