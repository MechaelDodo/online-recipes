package by.bsuir.recipeforum.database;

import by.bsuir.recipeforum.database.dao.AbstractDrugDao;
import by.bsuir.recipeforum.database.pool.ConnectionPool;
import by.bsuir.recipeforum.entity.Drug;
import by.bsuir.recipeforum.exception.ApplicationException;
import by.bsuir.recipeforum.factory.DrugFactory;
import by.bsuir.recipeforum.factory.EntityFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements methods for work with table 'drug'.
 */
public class RecDao extends AbstractDrugDao {

    /**
     * Logger for debug and error.
     */
    private static Logger logger;

    /**
     * Value of the sql query for select all recs from database.
     */
    private static final String SELECT_ALL_RECS;

    /**
     * Value of the sql query for select recs by name from database.
     */
    private static final String SELECT_BY_NAME;

    /**
     * Value of the sql query that inserts rec.
     */
    private static final String INSERT_REC;

    /**
     * Value of the sql query that deletes rec from database.
     */
    private static final String DELETE_REC;

    /**
     * Value of the sql query that updates rec in database.
     */
    private static final String UPDATE_REC;

    static {

        SELECT_ALL_RECS = "SELECT d.`id`, d.`name`, d.`description`, s.`name`"
                + " `substance_name` FROM `drug` d INNER JOIN `substance` s "
                + "ON s.`id` = d.`substance_id`";
        SELECT_BY_NAME = "SELECT d.`id`, d.`name`, d.`description`, s.`name`"
                + " `substance_name` FROM `drug` d INNER JOIN `substance` s "
                + "ON s.`id` = d.`substance_id` WHERE d.`name` = ?";
        INSERT_REC = "INSERT INTO `rec` (`substance_id`, `name`, "
                + "`description`) VALUES((SELECT `id` "
                + "FROM `substance` s WHERE s.`name` = ?), ?, ?)";
        DELETE_REC = "DELETE FROM `rec` WHERE name = ?";
        UPDATE_REC = "UPDATE `drug` SET `description` = ? WHERE `name` = ?";

    }

    /**
     * Public default constructor.
     */
    public RecDao() {

        logger = LogManager.getLogger();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Rec> select() throws ApplicationException {

        final List<Rec> recs = new ArrayList<>();
        final EntityFactory factory = new RecFactory();
        final ResultSet resultSet;
        final String debugString = " All recs selected from table `rec`.";

        Connection connection = null;
        Statement statement = null;

        try {

            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_DRUGS);

            while (resultSet.next()) {
                recs.add((Rec) factory.createEntity(resultSet));
            }

        } catch (SQLException e) {
            throw new ApplicationException(e.getMessage());
        } finally {

            super.close(statement);
            super.close(connection);

        }

        logger.log(Level.DEBUG, debugString);

        return recs;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Rec> select(final String name)
            throws ApplicationException {

        final List<Rec> recs = new ArrayList<>();
        final EntityFactory factory = new DrugFactory();
        final ResultSet resultSet;
        final String debugString = " All drugs selected from table `rec`.";

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SELECT_BY_NAME);

            statement.setString(1, name);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                drugs.add((Rec) factory.createEntity(resultSet));
            }

        } catch (SQLException e) {
            throw new ApplicationException(e.getMessage());
        } finally {

            super.close(statement);
            super.close(connection);

        }

        logger.log(Level.DEBUG, debugString);

        return recs;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert(final Rec rec) throws ApplicationException {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(INSERT_Rec);

            statement.setString(1, rec.getSubstance().getName());
            statement.setString(2, rec.getName());
            statement.setString(3, rec.getDescription());

            statement.executeUpdate();

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
    public void update(final Drug drug) throws ApplicationException {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE_REC);

            statement.setString(1, drug.getDescription());
            statement.setString(2, drug.getName());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new ApplicationException(e);
        } finally {

            super.close(statement);
            super.close(connection);

        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(final String name) throws ApplicationException {

        final int countRows;

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(DELETE_REC);

            statement.setString(1, name);

            countRows = statement.executeUpdate();

        } catch (SQLException e) {
            throw new ApplicationException(e.getMessage());
        } finally {

            super.close(statement);
            super.close(connection);

        }

        return countRows != 0;

    }

}
