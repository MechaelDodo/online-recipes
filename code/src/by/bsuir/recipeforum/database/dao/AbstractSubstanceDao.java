package by.bsuir.recipeforum.database.dao;

import by.bsuir.recipeforum.entity.Substance;
import by.bsuir.recipeforum.exception.ApplicationException;

/**
 * This abstract class stores methods for work with table 'substance'.
 */
public abstract class AbstractSubstanceDao extends AbstractDao<Substance> {

    /**
     * Protected default constructor.
     */
    protected AbstractSubstanceDao() {

        super();

    }

    /**
     * This method tests the existence of a substance.
     *
     * @param name value of the substance name
     * @return boolean value
     * @throws ApplicationException throw SQLException
     */
    public abstract boolean isExistSubstance(String name)
            throws ApplicationException;

}
