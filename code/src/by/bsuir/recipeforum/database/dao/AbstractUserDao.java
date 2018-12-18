package by.bsuir.recipeforum.database.dao;

import by.bsuir.recipeforum.entity.User;
import by.bsuir.recipeforum.exception.ApplicationException;


public abstract class AbstractUserDao extends AbstractDao<User> {

    /**
     * This protected default constructor.
     */
    protected AbstractUserDao() {

    }

    /**
     * This method checks is existence user by login and password.
     *
     * @param login    value of the user login
     * @param password value of the user password
     * @throws ApplicationException throw SQLException
     */
    protected abstract boolean isExist(String login, String password)
            throws ApplicationException;

}
