package dataModel;

import com.sun.rowset.CachedRowSetImpl;
import com.sun.rowset.internal.Row;
import entities.Project;
import entities.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProjectDaoImpl extends AbstractDao<Project, Long> {

    public ProjectDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public boolean validate(Project object) {
        return false;
    }

    @Override
    public String getSelectByIdQuery() {
        return "SELECT PROJECTNAME, STARTDATE, FINISHDATE, HEAD_ID FROM BUGTRACKER.PROJECT WHERE ID = ?";
    }

    @Override
    public String getSelectAllQuery() {
        return "SELECT PROJECTNAME, STARTDATE, FINISHDATE, HEAD_ID FROM BUGTRACKER.PROJECT";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO BUGTRACKER.PROJECT(PROJECTNAME, CREATED, CREATEDBY, UPDATED, UPDATEDBY, STARTDATE, FINISHDATE, HEAD_ID)" +
                "VALUES(?, CURRENT_TIMESTAMP(),?," +
                "CURRENT_TIMESTAMP(),?,?,?,?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE BUGTRACKER.PROJECT SET PROJECTNAME = ? UPDATED = CURRENT_TIMESTAMP() UPDATEDBY=? " +
                "STARTDATE=? FINISHDATE=? HEAD_ID=? WHERE ID = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM BUGTRACKER.PROJECT WHERE ID = ?";
    }

    @Override
    protected List<Project> parseRowSet(CachedRowSetImpl crs) {
        List<Project> result = new ArrayList<>();
        try {
            if (crs.next()){
                Collection<Row> rows = (Collection<Row>) crs.toCollection();
                for (Row row : rows) {
                    long pId = (long) row.getColumnObject(1);
                    String pName = (String) row.getColumnObject(2);
                    long headId = (long) row.getColumnObject(3);
                    Project project = new Project(pId, pName, headId);
                    result.add(project);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void preparedInsertStatement(PreparedStatement statement, Project obj) {
        try {
            statement.setString(1, obj.getProjectName());
            statement.setLong(2, obj.getCreatedBy());
            statement.setLong(3, obj.getUpdatedBy());
            statement.setTimestamp(4, obj.getStartDate());
            statement.setTimestamp(5, obj.getFinishDate());
            statement.setLong(6, obj.getHeadId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void preparedUpdateStatement(PreparedStatement statement, Project obj) {
        try {
            statement.setString(1, obj.getProjectName());
            statement.setLong(2, obj.getUpdatedBy());
            statement.setTimestamp(3, obj.getStartDate());
            statement.setTimestamp(4, obj.getFinishDate());
            statement.setLong(5, obj.getHeadId());
            statement.setLong(6, obj.getProjectId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
