package by.bsuir.recipeforum.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class stores info about medicine substance.
 */
public class Substance extends AbstractEntity {

    /**
     * Logger for debug.
     */
    private static Logger logger;

    /**
     * Value of the substance name.
     */
    private String name;

    /**
     * Public default constructor.
     */
    public Substance() {

        super();
        logger = LogManager.getLogger(Substance.class);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {

        return getClass().getName() + "@entity:"
                + super.toString() + "@name:" + this.name;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {

        return super.hashCode()
                + (this.name != null ? this.name.hashCode() : 0);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object object) {

        final boolean result;
        final Substance substance;

        if (object != null) {

            substance = (Substance) object;

            if (this == object) {
                result = true;
            } else if (getClass() != object.getClass()) {
                result = false;
            } else {
                result = super.getId().equals(substance.getId())
                        && (this.name == null
                        || this.name.equals(substance.getName()));
            }

        } else {
            result = false;
        }

        return result;

    }

    /**
     * This method returns value of the substance name.
     *
     * @return value of the substance name
     */
    public String getName() {

        final String result;

        if (this.name != null) {
            result = this.name;
        } else {
            result = "";
        }

        return result;

    }

    /**
     * This method sets new value of the substance name.
     *
     * @param newName new value of the substance name
     */
    public void setName(final String newName) {

        final String debugString
                = " Attribute is null in method setName(String).";

        if (newName != null) {
            this.name = newName;
        } else {
            logger.log(Level.DEBUG, debugString);
        }

    }

}
