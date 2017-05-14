package dataModel;

import com.sun.rowset.CachedRowSetImpl;
import com.sun.rowset.internal.Row;
import entities.*;

import javax.sql.RowSetMetaData;
import javax.sql.rowset.RowSetMetaDataImpl;
import java.sql.*;
import java.util.*;

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

    public Map<String, List<Task>> getTasksByUser(long id){
        Map<String, List<Task>> taskMap = new HashMap<>();
        String query = "SELECT BUGTRACKER.TASK.*,PROJECTNAME FROM BUGTRACKER.TASK,BUGTRACKER.PROJECT WHERE BUGTRACKER.TASK.PROJECT_ID=PROJECT.ID AND BUGTRACKER.TASK.ASSIGNEE_ID=?";
        PreparedStatement statement = null;
        ResultSet rs = null;
        CachedRowSetImpl crs = null;
        try {
            crs = new CachedRowSetImpl();
            statement = connection.prepareStatement(query);
            statement.setLong(1,id);
            rs = statement.executeQuery();
            if (rs != null) {
                crs.populate(rs);
                if (crs.next()) {
                    Collection<Row> rows = (Collection<Row>) crs.toCollection();
                    for (Row row : rows) {
                        long tId = (long) row.getColumnObject(1);
                        String tName = (String) row.getColumnObject(2);
                        String type = (String) row.getColumnObject(3);
                        String state = (String) row.getColumnObject(4);
                        String descr = (String) row.getColumnObject(5);
                        String priority = (String) row.getColumnObject(6);
                        long pId = (long) row.getColumnObject(13);
                        String pName = (String) row.getColumnObject(14);
                        Task task = new Task(tId, tName, type, state, descr, priority, pId);
                        if(taskMap.containsKey(pName)){
                            taskMap.get(pName).add(task);
                        } else {
                            List<Task> tasks = new ArrayList<>();
                            tasks.add(task);
                            taskMap.put(pName, tasks);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs!=null){
                try {
                    crs.close();
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
