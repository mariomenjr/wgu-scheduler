package Scheduler.Dao;

import Scheduler.Models.User;
import Scheduler.Repository.BaseManager;

import java.sql.ResultSet;

import static Scheduler.Utils.Parser.stringToCalendar;

public class UserManager extends BaseManager<User> {

    @Override
    protected User newInstanceOfEntity(ResultSet resultSet) throws Exception {
        return new User(
            resultSet.getInt("userId"),
            resultSet.getString("userName"),
            resultSet.getString("password"),
            resultSet.getInt("active") == 1,
            stringToCalendar(resultSet.getString("createDate")),
            resultSet.getString("createdBy"),
            stringToCalendar(resultSet.getString("lastUpdate")),
            resultSet.getString("lastUpdateBy")
        );
    }
}
