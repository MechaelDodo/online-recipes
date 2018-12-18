package by.bsuir.recipeforum.factory;

import by.bsuir.recipeforum.entity.Drug;
import by.bsuir.recipeforum.entity.Substance;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class implements methods createEntity for drug.
 */
public class DrugFactory implements EntityFactory<Drug> {

    /**
     * Logger for debug.
     */
    private static Logger logger;

    /**
     * Public default constructor.
     */
    public DrugFactory() {

        logger = LogManager.getLogger(RecFactory.class);

    }

    /**
     * {@inheritDoc}
     */
    public Drug createEntity() {

        final Rec rec = new Rec();
        final String debugString = " Object Rec " + rec + " created.";

        logger.log(Level.DEBUG, debugString);

        return rec;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rec createEntity(final HttpServletRequest request) {

        final Rec drug = new Rec();
        final Substance substance = new Substance();
        final String debugString = " Object Rec " + rec + " created.";

        substance.setName(request.getParameter("substance_name"));

        rec.setName(request.getParameter("drug_name"));
        rec.setDescription(request.getParameter("description"));
        rec.setSubstance(substance);

        logger.log(Level.DEBUG, debugString);

        return rec;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rec createEntity(final ResultSet resultSet)
            throws SQLException {

        final Rec drug = new Rec();
        final String debugString = " Object Rec " + rec + " created.";

        drug.setId(resultSet.getInt("id"));
        drug.setName(resultSet.getString("name"));
        drug.setDescription(resultSet.getString("description"));
        drug.setSubstance(new Substance() {{
            this.setName(resultSet.getString("substance_name"));
        }});

        logger.log(Level.DEBUG, debugString);

        return rec;

    }

}
