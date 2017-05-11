package dataModel;

import entities.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sasha on 02.05.2017.
 */
public class EmployeeDaoImpl extends AbstractDao<Employee, Long> {

    public EmployeeDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Employee create() {
        Employee employee= new Employee();
        return persist(employee);
    }

    @Override
    public String getSelectByIdQuery() {
        return "SELECT EMPLOYEE_NAME, EMAIL, PASSWORD, ISADMIN FROM BUGTRACKER.EMPLOYEE WHERE ID = ?";
    }

    @Override
    public String getSelectAllQuery() {
        return "SELECT EMPLOYEE_NAME, EMAIL, PASSWORD, ISADMIN FROM BUGTRACKER.EMPLOYEE";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO BUGTRACKER.EMPLOYEE(EMPLOYEE_NAME, EMAIL, PASSWORD, ISADMIN, CREATED, CREATEDBY, UPDATED, UPDATEDBY)" +
                "VALUES(?,?,?,?, CURRENT_TIMESTAMP(),?," +
                "CURRENT_TIMESTAMP(),?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE BUGTRACKER.EMPLOYEE SET EMPLOYEE_NAME = ? EMAIL = ? PASSWORD = ? ISADMIN = ? " +
                "UPDATED = CURRENT_TIMESTAMP() UPDATEDBY=? WHERE ID = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM BUGTRACKER.EMPLOYEE WHERE ID = ?";
    }

    @Override
    protected List<Employee> parseResultSet(ResultSet rs) {
        List<Employee> result = new ArrayList<>();
        try {
            while (rs.next()){
                boolean isAdmin = (rs.getInt("ISADMIN") == 1);
                Employee employee = new Employee(rs.getLong("ID"), rs.getString("EMPLOYEENAME"),
                        rs.getString("EMAIL"), rs.getString("PASSWORD"), isAdmin);
                result.add(employee);
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
