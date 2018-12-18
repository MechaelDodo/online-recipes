package by.bsuir.recipeforum.database.dao;

import by.bsuir.recipeforum.entity.Drug;
import by.bsuir.recipeforum.exception.ApplicationException;

import java.util.List;

/**
 * This abstract class stores methods for work with table 'drug'.
 */
public abstract class AbstractRecDao extends AbstractDao<Drug> {

    /**
     * Protected default constructor.
     */
    protected AbstractRecDao() {

        super();

    }

    /**
     * Method declaration for delete rec from database.
     *
     * @param name value of the drug name
     * @return boolean value
     * @throws ApplicationException throw SQLException
     */
    protected abstract boolean delete(String name)
            throws ApplicationException;

    /**
     * Method declaration for select drug by name from database.
     *
     * @param name value of the drug name
     * @return list of drugs
     * @throws ApplicationException throw SQLException
     */
    protected abstract List<Rec> select(String name)
            throws ApplicationException;

}
