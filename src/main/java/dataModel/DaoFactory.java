package dataModel;


import java.sql.SQLException;

/**
 * Created by Sasha on 02.05.2017.
 */
public interface DaoFactory<T> {
    T getConnection();
    GenericDao getDao(String className);
    Object executeAndClose(DaoCommand command);
    Object transaction(DaoCommand command) throws SQLException;
}
