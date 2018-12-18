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

/**
 * This class implements "Action" for changing recipe.
 */
public class ChangingRecipe implements Action {

    /**
     * Logger for debug and error.
     */
    private static Logger logger;

    /**
     * Public default constructor.
     */
    public ChangingRecipe() {

        logger = LogManager.getLogger(ChangingRecipe.class);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final HttpServletRequest request,
                        final HttpServletResponse response)
            throws IOException, ServletException {

        final String description = request.getParameter("description");
        final String recName = request.getParameter("recipe_name");
        final Rec rec = new Rec();
        final AbstractDao dao = new RecDao();

        rec.setDescription(description);
        rec.setName(recName);

        try {
            ((RecDao) dao).update(rec);
        } catch (ApplicationException e) {

            logger.log(Level.ERROR, e.getMessage());
            e.printStackTrace();

        }

        request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp")
                .forward(request, response);

    }

}
