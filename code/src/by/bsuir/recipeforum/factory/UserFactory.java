package by.bsuir.recipeforum.factory;

import by.bsuir.recipeforum.entity.User;
import by.bsuir.recipeforum.exception.ApplicationException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;

/**
 * This class implements createEntity methods for user.
 */
public class UserFactory implements EntityFactory<User> {

    /**
     * Logger for debug.
     */
    private static Logger logger;

    /**
     * Public default constructor.
     */
    public UserFactory() {

        logger = LogManager.getLogger(UserFactory.class);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User createEntity() {

        final User user = new User();
        final String debugString = " Object User " + user + " created.";

        logger.log(Level.DEBUG, debugString);

        return user;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User createEntity(final HttpServletRequest request) {

        final User user = new User();
        final String debugString;

        if (request != null) {

            user.setLogin((request.getParameter("login")));
            user.setPassword(request.getParameter("pass"));

            debugString = " Object User " + user + " created from request.";

        } else {
            debugString = " Attribute is null in method "
                    + "createEntity(HttpServletRequest).";
        }

        logger.log(Level.DEBUG, debugString);

        return user;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User createEntity(final ResultSet resultSet) {

        final User user = new User();
        final String debugString = " Object User " + user + " created.";

        logger.log(Level.DEBUG, debugString);

        return user;

    }

}
