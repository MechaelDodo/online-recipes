package by.bsuir.recipeforum.serverpart.action;

import by.bsuir.recipeforum.database.UserDao;
import by.bsuir.recipeforum.database.dao.AbstractDao;
import by.bsuir.recipeforum.entity.User;
import by.bsuir.recipeforum.exception.ApplicationException;
import by.bsuir.recipeforum.factory.EntityFactory;
import by.bsuir.recipeforum.factory.UserFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 */
public class Authorization implements Action {

    /**
     * Logger for debug and error.
     */
    private static Logger logger;

    /**
     * Value of the admin url.
     */
    private static final String URL_ADMIN
            = "/WEB-INF/jsp/admin.jsp";

    /**
     * Value of the index url.
     */
    private static final String URL_INDEX
            = "/main.jsp";

    /**
     * Public default constructor.
     */
    public Authorization() {

        logger = LogManager.getLogger(Authorization.class);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final HttpServletRequest request,
                        final HttpServletResponse response)
            throws IOException, ServletException {

        final AbstractDao dao = new UserDao();
        final EntityFactory factory = new UserFactory();
        final User user = ((UserFactory) factory).createEntity(request);

        final String debugString;

        try {

            if (((UserDao) dao).isExist(user.getLogin(), user.getPassword())) {

                request.getSession().setAttribute("user", user);
                request.getRequestDispatcher(URL_ADMIN)
                        .forward(request, response);

                debugString = " User " + user + " has logged in.";

            } else {

                debugString = " User " + user + " doesn't exist.";
                request.getRequestDispatcher(URL_INDEX)
                        .forward(request, response);

            }

            logger.log(Level.DEBUG, debugString);

        } catch (ApplicationException e) {

            e.printStackTrace();
            logger.log(Level.ERROR, e.getMessage());

        }

    }

}
