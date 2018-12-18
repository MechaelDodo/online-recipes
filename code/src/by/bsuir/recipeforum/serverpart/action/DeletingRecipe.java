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
 * This class implements interface "Action" for deleting rec.
 */
public class DeletingRecipe implements Action {

    /**
     * Logger for debug and error.
     */
    private static Logger logger;

    /**
     * Public default constructor.
     */
    public DeletingMedicine() {

        logger = LogManager.getLogger(DeletingRecipe.class);

    }

    /**
     * {@inheritDoc}
     */
    public void execute(final HttpServletRequest request,
                        final HttpServletResponse response)
            throws IOException, ServletException {

        final String drugName = request.getParameter("recipe_name");
        final AbstractDao dao = new RecDao();

        List<Rec> recs = new ArrayList<>();

        try {

            ((RecDao) dao).delete(recName);
            recs = ((RecDao) dao).select();

        } catch (ApplicationException e) {

            logger.log(Level.ERROR, e.getMessage());
            e.printStackTrace();

        }

        request.getSession().removeAttribute("recs");
        request.getSession().setAttribute("recs", recs);
        request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp")
                .forward(request, response);

    }

}
