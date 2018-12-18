package by.bsuir.recipeforum.serverpart.action;

import by.bsuir.recipeforum.database.DrugDao;
import by.bsuir.recipeforum.database.dao.AbstractDao;
import by.bsuir.recipeforum.entity.Drug;
import by.bsuir.recipeforum.exception.ApplicationException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements  interface "Action" for searching drugs.
 */
public class Searching implements Action {

    /**
     * Logger for debug and error.
     */
    private static Logger logger;

    /**
     * Public default constructor.
     */
    public Searching() {

        logger = LogManager.getLogger(Searching.class);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final HttpServletRequest request,
                        final HttpServletResponse response)
            throws IOException, ServletException {

        final AbstractDao dao = new RecDao();
        final String debugString;

        List<Rec> recs = new ArrayList<>();

        try {

            drugs = ((RecDao) dao).select(
                    request.getParameter("rec_name"));
            debugString = " Search by name is completed.";


            logger.log(Level.DEBUG, debugString);

        } catch (ApplicationException e) {

            logger.log(Level.ERROR, e.getMessage());
            e.printStackTrace();

        }

        request.getSession().setAttribute("drugs", recs);
        request.getRequestDispatcher("/main.jsp")
                .forward(request, response);

    }

}
