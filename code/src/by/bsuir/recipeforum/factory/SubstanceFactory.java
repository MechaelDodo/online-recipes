package by.bsuir.recipeforum.factory;

import by.bsuir.recipeforum.entity.Substance;
import by.bsuir.recipeforum.exception.ApplicationException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;

/**
 * This class implements createEntity methods for substance.
 */
public class SubstanceFactory implements EntityFactory<Substance> {

    /**
     * Logger for debug.
     */
    private static Logger logger;

    /**
     * Public default constructor.
     */
    public SubstanceFactory() {

        logger = LogManager.getLogger(SubstanceFactory.class);

    }

    /**
     * {@inheritDoc}
     */
    public Substance createEntity() {

        final Substance substance = new Substance();
        final String debugString = " Object Substance "
                + substance + " created.";

        logger.log(Level.DEBUG, debugString);

        return substance;

    }

    /**
     * {@inheritDoc}
     */
    public Substance createEntity(final HttpServletRequest request)
            throws ApplicationException {

        final String errorString
                = " Object Substance can't be create from request.";

        throw new ApplicationException(errorString);

    }

    /**
     * {@inheritDoc}
     */
    public Substance createEntity(final ResultSet resultSet) {

        final Substance substance = new Substance();
        final String debugString = " Object Substance "
                + substance + " created.";

        logger.log(Level.DEBUG, debugString);

        return substance;

    }
}
