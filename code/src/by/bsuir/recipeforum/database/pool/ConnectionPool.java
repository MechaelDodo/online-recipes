package by.bsuir.recipeforum.database.pool;

import by.bsuir.recipeforum.exception.ApplicationException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class stores and complete connections to the DataBase.
 */
public class ConnectionPool {

    /**
     * Logger for debug and errors.
     */
    private static final Logger LOGGER;

    /**
     * Value of the object ConnectionPool.
     */
    private static ConnectionPool instance;

    /**
     * Value of the object ReentrantLocker.
     */
    private static ReentrantLock locker;

    /**
     * Queue of the connections.
     */
    private BlockingQueue<Connection> connections;

    /**
     * Value of the driver class.
     */
    private static final String DRIVER_CLASS;

    /**
     * Value of the database url.
     */
    private static final String DB_URL;

    /**
     * Value of the database user.
     */
    private static final String DB_USER;

    /**
     * Value of the user password.
     */
    private static final String DB_PASSWORD;

    static {

        DRIVER_CLASS = "com.mysql.jdbc.Driver";
        DB_URL = "jdbc:mysql://localhost:3306/recipe-forum";
        DB_USER = "root";
        DB_PASSWORD = "upadaf03";

        LOGGER = LogManager.getLogger(ConnectionPool.class);
        locker = new ReentrantLock();
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            LOGGER.log(Level.ERROR, e.getMessage());
        }
    }

    /**
     * Private default constructor.
     */
    private ConnectionPool() {
        this.connections = new LinkedBlockingQueue<>();
    }

    /**
     * This method return value of the single object ConnectionPool.
     *
     * @return value of the object ConnectionPool
     */
    public static ConnectionPool getInstance() {

        locker.lock();

        try {
            if (instance == null) {
                instance = new ConnectionPool();
            }
        } finally {
            locker.unlock();
        }

        return instance;

    }

    /**
     * This method return value of the object Connection.
     *
     * @return value of the object Connection
     * @throws ApplicationException if we have exceptions with connections
     */
    public Connection getConnection() throws ApplicationException {

        Connection connection = null;

        locker.lock();

        try {

            while (connection == null) {

                try {

                    if (this.connections.isEmpty()) {
                        connection = DriverManager.getConnection(
                                DB_URL,
                                DB_USER,
                                DB_PASSWORD);
                    } else {
                        connection = this.connections.take();
                        if (!connection.isValid(0)) {
                            connection = null;
                        }
                    }

                } catch (InterruptedException e) {

                    e.printStackTrace();
                    throw new ApplicationException(e.getMessage());

                } catch (SQLException e) {
                    throw new ApplicationException(e.getMessage());
                }

            }

        } finally {
            locker.unlock();
        }

        return connection;

    }

    /**
     * Add connection in pool.
     *
     * @param connection value of the object Connection
     * @throws ApplicationException if problem with threads
     */
    public void freeConnection(final Connection connection)
            throws ApplicationException {

        try {
            this.connections.put(connection);
        } catch (InterruptedException e) {
            throw new ApplicationException(e.getMessage());
        }

    }

}
