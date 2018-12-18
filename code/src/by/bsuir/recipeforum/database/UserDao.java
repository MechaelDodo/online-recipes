package by.bsuir.recipeforum.database;

import by.bsuir.recipeforum.database.dao.AbstractUserDao;
import by.bsuir.recipeforum.database.pool.ConnectionPool;
import by.bsuir.recipeforum.entity.User;
import by.bsuir.recipeforum.exception.ApplicationException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements methods for work with table 'user'.
 */
public class UserDao extends AbstractUserDao {

    /**
     * Logger for debug and error.
     */
    private static Logger logger;

    /**
     * Value of the query that checks user.
     */
    private static final String EXIST_USER;

    static {

        EXIST_USER = "SELECT * FROM `user` WHERE `login` = ? "
                + "AND `password` = sha2(?, 256)";

    }

    /**
     * Public default constructor.
     */
    public UserDao() {

        super();
        logger = LogManager.getLogger(UserDao.class);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> select() {

        final List<User> users = new ArrayList<>();

        return users;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert(final User user) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final User user) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExist(final String login, final String password)
            throws ApplicationException {

        final String debugString;
        final ResultSet resultSet;

        boolean result = false;
        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(EXIST_USER);

            statement.setString(1, login);
            statement.setString(2, password);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {

                result = true;
                debugString = " User with login " + login + " and password"
                        + password + " is existence ";

            } else {

                debugString = " User with login " + login + " and password"
                        + password + " doesn't exist.";

            }

            logger.log(Level.DEBUG, debugString);

        } catch (ApplicationException e) {

            e.printStackTrace();
            logger.log(Level.ERROR, e.getMessage());

        } catch (SQLException e) {
            throw new ApplicationException(e.getMessage());
        } finally {

            try {
                super.close(statement);
            } catch (ApplicationException e) {

                e.printStackTrace();
                logger.log(Level.ERROR, e.getMessage());

            }

            super.close(connection);

        }

        return result;

    }

}
