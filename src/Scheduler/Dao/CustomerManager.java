package Scheduler.Dao;

import Scheduler.Models.Customer;
import Scheduler.Repository.BaseManager;

import java.sql.ResultSet;

import static Scheduler.Utils.Parser.StringToCalendar;

public class CustomerManager extends BaseManager<Customer> {
    @Override
    protected String instanceToInsertQuery(Customer instance) {
        return null;
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
