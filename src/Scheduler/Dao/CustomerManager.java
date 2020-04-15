package Scheduler.Dao;

import Scheduler.Models.Customer;
import Scheduler.Models.User;
import Scheduler.Repository.BaseManager;

import java.sql.ResultSet;

import static Scheduler.Utils.Parser.stringToCalendar;

public class CustomerManager extends BaseManager<Customer> {
    @Override
    protected Customer newInstanceOfEntity(ResultSet resultSet) throws Exception {
        return new Customer(
            resultSet.getInt("customerId"),
            resultSet.getString("customerName"),
            resultSet.getInt("addressId"),
            resultSet.getInt("active") == 1,
            stringToCalendar(resultSet.getString("createDate")),
            resultSet.getString("createdBy"),
            stringToCalendar(resultSet.getString("lastUpdate")),
            resultSet.getString("lastUpdateBy")
        );
    }
}
