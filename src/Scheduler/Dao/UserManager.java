package Scheduler.Dao;

import Scheduler.Models.User;
import Scheduler.Repository.BaseManager;

import java.sql.ResultSet;
import java.text.ParseException;

import static Scheduler.Utils.Parser.StringToCalendar;

public class UserManager extends BaseManager<User> {

    @Override
    protected String instanceToInsertQuery(User instance) {
        return null;
    }

    @Override
    protected String instanceToUpdateQuery(User instance) throws Exception {
        return null;
    }

    @Override
    protected String instanceToDeleteQuery(User instance) throws ParseException, Exception {
        return null;
    }

    @Override
    protected User newInstanceOfEntity(ResultSet resultSet) throws Exception {
        return new User(
            resultSet.getInt("userId"),
            resultSet.getString("userName"),
            resultSet.getString("password"),
            resultSet.getInt("active") == 1,
            StringToCalendar(resultSet.getString("createDate")),
            resultSet.getString("createdBy"),
            StringToCalendar(resultSet.getString("lastUpdate")),
            resultSet.getString("lastUpdateBy")
        );
    }
}
