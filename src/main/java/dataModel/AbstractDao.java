package dataModel;

import entities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sasha on 02.05.2017.
 */
public abstract class AbstractDao<T extends Entity, K extends Long> implements GenericDao<T, K>{
    private Connection connection;

    public AbstractDao(Connection connection){
        this.connection = connection;
    }

    public abstract String getSelectByIdQuery();

    public abstract String getSelectAllQuery();

    public abstract String getCreateQuery();

    public abstract String getUpdateQuery();

    public abstract String getDeleteQuery();

    protected abstract List<T> parseResultSet(ResultSet rs);

    protected abstract void preparedInsertStatement(PreparedStatement statement, T obj);

    protected abstract void preparedUpdateStatement(PreparedStatement statement, T obj);

    public Employee logIn(String email, String password){
        Employee result = null;
        String query ="SELECT * FROM BUGTRACKER.EMPLOYEE WHERE EMAIL = ? AND PASSWORD = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet != null){
                List<Employee> employees = (List<Employee>) parseResultSet(resultSet);
                result = employees.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet!=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public T persist(T object) {
        T result = null;
        String query = getCreateQuery();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            preparedInsertStatement(statement, object);
            statement.executeUpdate();
            statement.close();

            result = getById(object.getEntityId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public T getById(long id) {
        T result = null;
        String query = getSelectByIdQuery();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<T> list = parseResultSet(resultSet);
            result = list.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Map<String, Task> getTasksByUser(long id){
        Map<String, Task> taskMap = new HashMap<>();
        String query = "SELECT BUGTRACKER.TASK.*,PROJECTNAME FROM BUGTRACKER.TASK,BUGTRACKER.PROJECT WHERE BUGTRACKER.TASK.PROJECT_ID=PROJECT.ID AND BUGTRACKER.TASK.ASSIGNEE_ID="+id;
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            rs.beforeFirst();
            while (rs.next()){
                Task task = new Task(rs.getLong("ID"), rs.getString("TASKNAME"),
                        TaskType.valueOf((rs.getString("TYPE").toUpperCase())), TaskState.valueOf((rs.getString("STATE").toUpperCase())),
                        rs.getString("DESCRIPTION"), TaskPriority.valueOf((rs.getString("PRIORITY")).toUpperCase()),
                        rs.getLong("PROJECT_ID"));
                taskMap.put(rs.getString("PROJECTNAME"), task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement!=null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return taskMap;
    }

    @Override
    public void update(T object) {
        String query = getUpdateQuery();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            preparedUpdateStatement(statement, object);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(T object) {
        String query = getDeleteQuery();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, object.getEntityId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<T> getAll() {
        List<T> list = null;
        String sql = getSelectAllQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
