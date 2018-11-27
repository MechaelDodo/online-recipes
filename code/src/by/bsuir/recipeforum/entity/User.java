package by.bsuir.recipeforum.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class stores info about user.
 */
public class User extends AbstractEntity {

    /**
     * Logger for debug.
     */
    private static Logger logger;

    /**
     * Value of the user login.
     */
    private String login;

    /**
     * Value of the user password.
     */
    private String password;

    /**
     * Public default constructor.
     */
    public User() {

        super();
        logger = LogManager.getLogger(User.class);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {

        return getClass().getName() + "@entity:" + super.toString()
                + "@login:" + this.login + "@password:" + this.password;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {

        return super.hashCode()
                + (this.login != null ? this.login.hashCode() : 0)
                + (this.password != null ? this.password.hashCode() : 0);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object object) {

        final boolean result;
        final User user;

        if (object != null) {

            user = (User) object;

            if (this == object) {
                result = true;
            } else if (getClass() != object.getClass()) {
                result = false;
            } else {
                result = super.getId().equals(user.getId())
                        && (this.login == null
                        || this.login.equals(user.getLogin()))
                        && (this.password == null
                        || this.password.equals(user.getPassword()));
            }

        } else {
            result = false;
        }

        return result;

    }

    /**
     * This method returns value of the user login.
     *
     * @return value of the user login
     */
    public String getLogin() {

        final String result;

        if (this.login != null) {
            result = this.login;
        } else {
            result = "";
        }

        return result;

    }

    /**
     * This method sets new value of the user login.
     *
     * @param newLogin new value of the user login
     */
    public void setLogin(final String newLogin) {

        final String debugString
                = " Attribute is null in method setLogin(String).";

        if (newLogin != null) {
            this.login = newLogin;
        } else {
            logger.log(Level.DEBUG, debugString);
        }

    }

    /**
     * This method returns value of the user password.
     *
     * @return value of the user password
     */
    public String getPassword() {

        final String result;

        if (this.password != null) {
            result = this.password;
        } else {
            result = "";
        }

        return result;

    }

    /**
     * This method sets value of the user password.
     *
     * @param newPassword value of the user password
     */
    public void setPassword(final String newPassword) {

        final String debugString
                = " Attribute is null in method setPassword(String).";

        if (newPassword != null) {
            this.password = newPassword;
        } else {
            logger.log(Level.DEBUG, debugString);
        }

    }

}
