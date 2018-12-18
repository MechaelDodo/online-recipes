package by.bsuir.recipeforum.serverpart.action;

import by.bsuir.recipeforum.database.DrugDao;
import by.bsuir.recipeforum.database.SubstanceDao;
import by.bsuir.recipeforum.database.dao.AbstractDao;
import by.bsuir.recipeforum.entity.Drug;
import by.bsuir.recipeforum.exception.ApplicationException;
import by.bsuir.recipeforum.factory.DrugFactory;
import by.bsuir.recipeforum.factory.EntityFactory;
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
 * This class implements interface "Action" for adding recipe.
 */
public class AddingRecipe implements Action {

    /**
     * Logger for debug and error.
     */
    private static Logger logger;

    /**
     * Public default constructor.
     */
    public AddingRecipe() {

        logger = LogManager.getLogger(AddingRecipe.class);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final HttpServletRequest request,
                        final HttpServletResponse response)
            throws IOException, ServletException {

        final EntityFactory factory = new DrugFactory();
        final Rec rec = ((RecFactory) factory).createEntity(request);

        AbstractDao dao;
        List<Rec> recs = new ArrayList<>();

        dao = new SubstanceDao();

        try {

            if (!((SubstanceDao) dao).isExistSubstance(
                    rec.getSubstance().getName())) {
                ((SubstanceDao) dao).insert(rec.getSubstance());
            }

            dao = new RecDao();
            ((RecDao) dao).insert(rec);
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
