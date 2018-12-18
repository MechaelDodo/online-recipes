package by.bsuir.recipeforum.database;

import by.bsuir.recipeforum.database.dao.AbstractSubstanceDao;
import by.bsuir.recipeforum.database.pool.ConnectionPool;
import by.bsuir.recipeforum.entity.Substance;
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
 * This class implements method for work with table 'substance'.
 */
public class SubstanceDao extends AbstractSubstanceDao {

    /**
     * Logger for debug and errors.
     */
    private static Logger logger;

    /**
     * Value of the sql query that select substance by name.
     */
    private static final String SELECT_SUBSTANCE_BY_NAME;

    /**
     * Value of the sql query for insert substance in database.
     */
    private static final String INSERT_SUBSTANCE;

    static {

        SELECT_SUBSTANCE_BY_NAME = "SELECT * FROM `substance` "
                + "WHERE `name` = ?";
        INSERT_SUBSTANCE = "INSERT INTO `substance` (`name`) VALUES(?)";

    }

    /**
     * Public default constructor.
     */
    public SubstanceDao() {

        super();
        logger = LogManager.getLogger(SubstanceDao.class);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Substance> select() {

        final List<Substance> substances = new ArrayList<>();

        return substances;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert(final Substance substance)
            throws ApplicationException {

        final String debugString;

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(INSERT_SUBSTANCE);
            statement.setString(1, substance.getName());

            if (statement.executeUpdate() != 0) {

                debugString = " Object " + substance
                        + " inserted in table 'substance'.";
                logger.log(Level.DEBUG, debugString);

            } else {

                debugString = " Object " + substance
                        + " didn't insert in table 'substance'.";
                logger.log(Level.DEBUG, debugString);

            }

        } catch (SQLException e) {
            throw new ApplicationException(e.getMessage());
        } finally {

            super.close(statement);
            super.close(connection);

        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Substance substance) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExistSubstance(final String name)
            throws ApplicationException {

        final boolean result;
        final ResultSet resultSet;
        final String debugString;

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SELECT_SUBSTANCE_BY_NAME);
            statement.setString(1, name);
            resultSet = statement.executeQuery();

            result = resultSet.next();

        } catch (SQLException e) {
            throw new ApplicationException(e.getMessage());
        } finally {

            super.close(statement);
            super.close(connection);

        }

        if (result) {
            debugString = " Substance with name " + name + " already exist.";
        } else {
            debugString = " Substance with name "
                    + name + " else doesn't exist.";
        }

        logger.log(Level.DEBUG, debugString);

        return result;

    }

}
