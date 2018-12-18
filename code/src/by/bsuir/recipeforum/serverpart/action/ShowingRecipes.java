package by.bsuir.recipeforum.serverpart.action;

import by.bsuir.recipeforum.database.DrugDao;
import by.bsuir.recipeforum.database.dao.AbstractDao;
import by.bsuir.recipeforum.exception.ApplicationException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class implements interface "Action" for showing recipes.
 */
public class ShowingRecipes implements Action {

    /**
     * Logger for debug and error.
     */
    private Logger logger;

    /**
     * Public default constructor.
     */
    public ShowingRecipes() {

        logger = LogManager.getLogger(ShowingRecipes.class);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final HttpServletRequest request,
                        final HttpServletResponse response)
            throws ServletException, IOException {

        final AbstractDao dao = new RecDao();

        try {

            request.getSession().setAttribute("recs",
                    ((RecDao) dao).select());
            request.getRequestDispatcher(
                    "/WEB-INF/jsp/drugs.jsp")
                    .forward(request, response);

        } catch (ApplicationException e) {

            logger.log(Level.ERROR, e.getMessage());
            e.printStackTrace();

        }

    }

}
