package dataModel;

import entities.EmployeeComment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by Sasha on 03.05.2017.
 */
public class EmployeeCommentImpl extends AbstractDao<EmployeeComment,Long> {

    public EmployeeCommentImpl(Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectByIdQuery() {
        return null;
    }

    @Override
    public String getSelectAllQuery() {
        return null;
    }

    @Override
    public String getCreateQuery() {
        return null;
    }

    @Override
    public String getUpdateQuery() {
        return null;
    }

    @Override
    public String getDeleteQuery() {
        return null;
    }

    @Override
    protected List<EmployeeComment> parseResultSet(ResultSet rs) {
        return null;
    }

    @Override
    protected void preparedInsertStatement(PreparedStatement statement, EmployeeComment obj) {

    }

    @Override
    protected void preparedUpdateStatement(PreparedStatement statement, EmployeeComment obj) {

    }

    @Override
    public EmployeeComment create() {
        return null;
    }
}
