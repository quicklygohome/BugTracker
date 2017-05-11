package dataModel;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Sasha on 02.05.2017.
 */
public class DaoFactoryImpl implements DaoFactory<Connection> {

    private DataSource dataSource;
    private Connection connection;

    public DaoFactoryImpl(){
        try {
            InitialContext initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("jdbc/bugtrackerMySql");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() {
        try {
            if (this.connection == null || this.connection.isClosed()) {
                this.connection = dataSource.getConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.connection;
    }

    @Override
    public GenericDao getDao(String className) {
        switch (className){
            case "EmployeeDaoImpl":{
                return new EmployeeDaoImpl(getConnection());
            }
            case "TaskDaoImpl":{
                return new TaskDaoImpl(getConnection());
            }
            case "ProjectDaoImpl":{
                return new ProjectDaoImpl(getConnection());
            }
            default: return null;
        }
    }

    @Override
    public Object executeAndClose(DaoCommand command) {
        try {
            return command.execute(this);
        } finally {
            try {
                this.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Object transaction(DaoCommand command) throws SQLException {
        Object returnValue = null;
        try {
            getConnection().setAutoCommit(false);
            returnValue = command.execute(this);
            getConnection().commit();
        } catch (SQLException e) {
            getConnection().rollback();
            e.printStackTrace();
        } finally {
            getConnection().setAutoCommit(true);
            getConnection().close();
        }
        return returnValue;
    }

}
