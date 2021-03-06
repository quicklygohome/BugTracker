package dataModel;

import com.sun.rowset.CachedRowSetImpl;
import com.sun.rowset.internal.Row;
import entities.Task;
import entities.TaskPriority;
import entities.TaskState;
import entities.TaskType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Sasha on 02.05.2017.
 */
public class TaskDaoImpl extends AbstractDao<Task, Long>{

    public TaskDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Task create() {
        Task task = new Task();
        return persist(task);
    }

    @Override
    public String getSelectByIdQuery() {
        return "SELECT TASKNAME, TYPE, STATE, PRIORITY, ASSIGNEE_ID, REPORTER_ID, CREATED, UPDATED FROM BUGTRACKER.TASK WHERE ID = ?";
    }

    @Override
    public String getSelectAllQuery() {
        return "SELECT TASKNAME, TYPE, STATE, PRIORITY, ASSIGNEE_ID, REPORTER_ID, CREATED, UPDATED FROM BUGTRACKER.TASK";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO BUGTRACKER.TASK(TASKNAME, TYPE, STATE, DESCRIPTION, PRIORITY, CREATED, CREATEDBY, UPDATED, UPDATEDBY," +
                "ASSIGNEE_ID, REPORTER_ID, PROJECT_ID)" +
                "VALUES(?,?,?,?,?, CURRENT_TIMESTAMP(),?," +
                "CURRENT_TIMESTAMP(),?,?,?,?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE BUGTRACKER.TASK SET TASKNAME = ? TYPE = ? STATE = ? DESCRIPTION = ? PRIORITY= ?" +
                "UPDATED = CURRENT_TIMESTAMP() UPDATEDBY=? " +
                "ASSIGNEE_ID=? REPORTER_ID=? PROJECT_ID=? WHERE ID = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM BUGTRACKER.TASK WHERE ID = ?";
    }

    @Override
    protected List<Task> parseRowSet(CachedRowSetImpl crs) {
        List<Task> result = new ArrayList<>();
        try {
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
                    Task task = new Task(tId, tName, type, state, descr, priority, pId);
                    result.add(task);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void preparedInsertStatement(PreparedStatement statement, Task obj) {
        try {
            statement.setString(1, obj.getTaskName());
            statement.setString(2, obj.getType().toString());
            statement.setString(3, obj.getState().toString());
            statement.setString(4, obj.getDescription());
            statement.setString(5, obj.getPriority().toString());
            statement.setLong(6, obj.getCreatedBy());
            statement.setLong(7, obj.getUpdatedBy());
            statement.setLong(8, obj.getAssigneeId());
            statement.setLong(9, obj.getReporterId());
            statement.setLong(10, obj.getProjectId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void preparedUpdateStatement(PreparedStatement statement, Task obj) {
        try {
            statement.setString(1, obj.getTaskName());
            statement.setString(2, obj.getType().toString());
            statement.setString(3, obj.getState().toString());
            statement.setString(4, obj.getDescription());
            statement.setString(5, obj.getPriority().toString());
            statement.setLong(6, obj.getUpdatedBy());
            statement.setLong(7, obj.getAssigneeId());
            statement.setLong(8, obj.getReporterId());
            statement.setLong(9, obj.getProjectId());
            statement.setLong(10, obj.getEntityId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
