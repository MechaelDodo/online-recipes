package by.bsuir.recipeforum.serverpart.servlets;

import by.bsuir.recipeforum.database.DrugDao;
import by.bsuir.recipeforum.exception.ApplicationException;
import by.bsuir.recipeforum.serverpart.action.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet controller.
 */
public class Controller extends HttpServlet {

    /**
     * Logger for debug and error.
     */
    private static Logger logger;

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {

        logger = LogManager.getLogger(Controller.class);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doPost(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws IOException, ServletException {

        try {
            this.process(request, response);
        } catch (ApplicationException e) {

            logger.log(Level.ERROR, " " + e.getMessage());
            e.printStackTrace();

        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doGet(final HttpServletRequest request,
                      final HttpServletResponse response)
            throws IOException, ServletException {

        try {
            this.process(request, response);
        } catch (ApplicationException e) {

            logger.log(Level.ERROR, " " + e.getMessage());
            e.printStackTrace();

        }

    }

    private void process(final HttpServletRequest request,
                         final HttpServletResponse response)
            throws ApplicationException, IOException, ServletException {

        final Action action;
        final String clientAction = request.getParameter("action");
        final String errorString = "Action type is incorrect.";
        final String debugString = "Attribute is null.";

        if (clientAction != null) {

            switch (clientAction) {

                case "signin":
                    action = new Authorization();
                    action.execute(request, response);
                    break;
                case "logout":
                    request.getSession().removeAttribute("user");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                    break;
                case "delete_recipe":
                    action = new DeletingRecipe();
                    action.execute(request, response);
                    break;
                case "show_recipes":
                    action = new ShowingRecipes();
                    action.execute(request, response);
                    break;
                case "add_recipe":
                    action = new AddingRecipe();
                    action.execute(request, response);
                    break;
                case "change_recipe":
                    action = new ChangingRecipe();
                    action.execute(request, response);
                    break;
                case "search_recipe":
                    action = new Searching();
                    action.execute(request, response);
                    break;
                case "back_admin":
                    request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp")
                            .forward(request, response);
                    break;
                case "back_user":
                    request.getRequestDispatcher("/index.jsp")
                            .forward(request, response);
                    break;
                case "all":
                    request.getSession().removeAttribute("recs");
                    request.getSession()
                            .setAttribute("recs", new RecDao().select());
                    request.getRequestDispatcher("/main.jsp")
                            .forward(request, response);
                    break;
                default:
                    throw new ApplicationException(errorString);

            }

        } else {
            logger.log(Level.DEBUG, debugString);
        }

    }

}
