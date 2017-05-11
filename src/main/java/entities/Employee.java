package entities;

import java.sql.Timestamp;

/**
 * Created by Sasha on 28.04.2017.
 */
public class Employee implements Entity{
    private long employeeId;
    private String employeeName;
    private String email;
    private String password;
    private boolean isAdmin;
    private Timestamp created;
    private long createdBy;
    private Timestamp updated;
    private long updatedBy;

    public Employee(){

    }

    public Employee(long employeeId, String employeeName, String email, String password, boolean isAdmin){
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
//        created
//        createdBy
//        updated
//        updatedBy
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getEmail() {
        return email;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public String getPassword() {
        return password;
    }

    public long getUpdatedBy() {
        return updatedBy;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(long updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public Long getEntityId() {
        return employeeId;
    }
}
