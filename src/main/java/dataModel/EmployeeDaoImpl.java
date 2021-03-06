package dataModel;

import com.sun.rowset.CachedRowSetImpl;
import com.sun.rowset.internal.Row;
import entities.Employee;
import entities.Task;

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
public class EmployeeDaoImpl extends AbstractDao<Employee, Long> {

    public EmployeeDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public boolean validate(Employee object) {
        if (readByEmail(object.getEmail())==null) return true;
        else return false;
    }

    @Override
    public String getSelectByIdQuery() {
        return "SELECT * FROM BUGTRACKER.EMPLOYEE WHERE ID = ?";
    }

    @Override
    public String getSelectAllQuery() {
        return "SELECT * FROM BUGTRACKER.EMPLOYEE";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO BUGTRACKER.EMPLOYEE(EMPLOYEENAME, EMAIL, PASSWORD, ISADMIN, CREATED, CREATEDBY, UPDATED, UPDATEDBY)" +
                "VALUES(?,?,?,?, CURRENT_TIMESTAMP(),?," +
                "CURRENT_TIMESTAMP(),?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE BUGTRACKER.EMPLOYEE SET EMPLOYEENAME = ? EMAIL = ? PASSWORD = ? ISADMIN = ? " +
                "UPDATED = CURRENT_TIMESTAMP() UPDATEDBY=? WHERE ID = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM BUGTRACKER.EMPLOYEE WHERE ID = ?";
    }

    @Override
    protected List<Employee> parseRowSet(CachedRowSetImpl crs) {
        List<Employee> result = new ArrayList<>();
        try {
            if (crs.next()){
                Collection<Row> rows = (Collection<Row>) crs.toCollection();
                for (Row row : rows) {
                    long eId = (long) row.getColumnObject(1);
                    String eName = (String) row.getColumnObject(2);
                    String email = (String) row.getColumnObject(3);
                    String passw = (String) row.getColumnObject(4);
                    boolean isAdmin = ((int)row.getColumnObject(5) == 1);
                    Employee employee = new Employee(eId, eName,email, passw, isAdmin);
                    result.add(employee);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void preparedInsertStatement(PreparedStatement statement, Employee obj) {
        try {
            statement.setString(1, obj.getEmployeeName());
            statement.setString(2, obj.getEmail());
            statement.setString(3,obj.getPassword());
            statement.setInt(4, obj.isAdmin() ? 1:0);
            statement.setLong(5, obj.getCreatedBy());
            statement.setLong(6,obj.getUpdatedBy());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void preparedUpdateStatement(PreparedStatement statement, Employee obj) {
        try {
            statement.setString(1, obj.getEmployeeName());
            statement.setString(2, obj.getEmail());
            statement.setString(3,obj.getPassword());
            statement.setInt(4, obj.isAdmin() ? 1:0);
            statement.setLong(5,obj.getUpdatedBy());
            statement.setLong(6,obj.getEntityId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void preparedLoginStatement(PreparedStatement statement, Employee obj){
        try {
            statement.setString(1, obj.getEmail());
            statement.setString(2, obj.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
