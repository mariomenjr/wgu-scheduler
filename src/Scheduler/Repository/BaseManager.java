package Scheduler.Repository;

import Scheduler.Dao.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class BaseManager<T> {

    protected Statement statement;
    protected ResultSet resultSet;
    protected ObservableList<T> observableList;

    abstract protected T newInstanceOfEntity(ResultSet resultSet) throws Exception;

    protected String getParameterizedTypeName() {
        ParameterizedTypeImpl parameterizedType = (ParameterizedTypeImpl) this.getClass().getGenericSuperclass();
        String[] pathType = parameterizedType.getActualTypeArguments()[0].getTypeName().split("\\.");
        return pathType[pathType.length - 1].toLowerCase();
    }

    protected ObservableList<T> _select(String where) throws SQLException, Exception {
        if (!Database.isConnected) Database.connect();

        String table = this.getParameterizedTypeName();
        String query = "select * from " + table + (where.length() > 0 ? " where " + where.trim():"");

        this.statement = Database.conn.createStatement();
        this.resultSet = this.statement.executeQuery(query);

        this.observableList = FXCollections.observableArrayList();
        while(this.resultSet.next())
            this.observableList.add(this.newInstanceOfEntity(this.resultSet));

        return this.observableList;
    }

    public ObservableList<T> select() throws SQLException, Exception {
        return this._select("");
    }

    public ObservableList<T> select(String where) throws SQLException, Exception {
        return this._select(where);
    }
}
