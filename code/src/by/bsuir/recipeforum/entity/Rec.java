package by.bsuir.recipeforum.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class stores info about medicine.
 */
public class Rec extends AbstractEntity {

    /**
     * Logger for debug.
     */
    private static Logger logger;

    /**
     * Value of the rec name.
     */
    private String name;

    /**
     * Value of the rec description.
     */
    private String description;

    /**
     * Value of the object Substance.
     */
    private Substance substance;

    /**
     * Public default constructor.
     */
    public Rec() {

        super();
        logger = LogManager.getLogger(Rec.class);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {

        return getClass().getName() + "@entity:"
                + super.toString() + "@name:" + this.name
                + "@description:" + this.description
                + "@substance:" + this.substance;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {

        return super.hashCode()
                + (this.name != null ? this.name.hashCode() : 0)
                + (this.description != null ? this.description.hashCode() : 0)
                + (this.substance != null ? this.substance.hashCode() : 0);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object object) {

        final boolean result;
        final Rec drug;

        if (object != null) {

            drug = (Rec) object;

            if (this == object) {
                result = true;
            } else if (getClass() != rec.getClass()) {
                result = false;
            } else {
                result = super.getId().equals(rec.getId())
                        && (this.name == null
                        || this.name.equals(rec.getName()))
                        && (this.description == null
                        || this.description.equals(rec.getDescription()))
                        && (this.substance == null
                        || this.substance.equals(rec.getSubstance()));
            }

        } else {
            result = false;
        }

        return result;

    }

    /**
     * This method returns value of the rec name.
     *
     * @return value of the rec name
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
     * This method sets new value of the rec name.
     *
     * @param newName new value of the rec name
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

    /**
     * This method returns value of the rec description.
     *
     * @return value of the rec description
     */
    public String getDescription() {

        final String result;

        if (this.description != null) {
            result = this.description;
        } else {
            result = "";
        }

        return result;

    }

    /**
     * This method sets new value of the rec description.
     *
     * @param newDescription new value of the rec description
     */
    public void setDescription(final String newDescription) {

        final String debugString
                = " Attribute is null in method setDescription(String).";

        if (newDescription != null) {
            this.description = newDescription;
        } else {
            logger.log(Level.DEBUG, debugString);
        }

    }

    /**
     * This method returns value of the object Substance.
     *
     * @return value of the object Substance
     */
    public Substance getSubstance() {

        final Substance result;

        if (this.substance != null) {
            result = this.substance;
        } else {
            result = new Substance();
        }

        return result;

    }

    /**
     * This method sets new value of the object Substance.
     *
     * @param newSubstance new value of the object Substance
     */
    public void setSubstance(final Substance newSubstance) {

        final String debugString
                = " Attribute is null in method setSubstance(Substance).";

        if (newSubstance != null) {
            this.substance = newSubstance;
        } else {
            logger.log(Level.DEBUG, debugString);
        }

    }

}
