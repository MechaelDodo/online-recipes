package by.bsuir.recipeforum.database.dao;

import by.bsuir.recipeforum.database.pool.ConnectionPool;
import by.bsuir.recipeforum.entity.AbstractEntity;
import by.bsuir.recipeforum.exception.ApplicationException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * This abstract class stores basic methods for work with database.
 */
public abstract class AbstractDao<T extends AbstractEntity> {

    /**
     * Logger for debug and error.
     */
    private static Logger logger;

    /**
     * Private-package default constructor.
     */
    AbstractDao() {

    }

    /**
     * This method selects some elements from database.
     *
     * @return list of objects that extend AbstractEntity class
     * @throws ApplicationException throws SQLException
     */
    protected abstract List<T> select() throws ApplicationException;

    /**
     * This method inserts some element in database.
     *
     * @param object value of the object that extends AbstractEntity class
     * @throws ApplicationException throw SQLException
     */
    protected abstract void insert(T object) throws ApplicationException;

    /**
     * This method updates some element from database.
     *
     * @param object value of the object that extends AbstractEntity class
     * @throws ApplicationException throw SQLException
     */
    protected abstract void update(T object) throws ApplicationException;

    /**
     * This method close connection with database.
     *
     * @param connection value of the object Connection
     */
    protected void close(final Connection connection) {

        try {
            ConnectionPool.getInstance().freeConnection(connection);
        } catch (ApplicationException e) {

            e.printStackTrace();
            logger.log(Level.ERROR, e.getMessage());

        }

    }

    /**
     * This method closes statement.
     *
     * @param statement value of the object Statement
     * @throws ApplicationException throw SQLException
     */
    protected void close(final Statement statement)
            throws ApplicationException {

        try {
            statement.close();
        } catch (SQLException e) {
            throw new ApplicationException(e.getMessage());
        }

    }

}
