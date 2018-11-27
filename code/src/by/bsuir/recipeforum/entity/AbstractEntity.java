package by.bsuir.recipeforum.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This abstract class stores information about entity id.
 */
public abstract class AbstractEntity {

    /**
     * Logger for debug.
     */
    private static Logger logger;

    /**
     * Value of the entity id.
     */
    private Integer id;

    /**
     * Protected default class.
     */
    protected AbstractEntity() {

        logger = LogManager.getLogger(AbstractEntity.class);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {

        return getClass().getName() + "@id:" + this.id;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {

        return this.id != null ? this.id.hashCode() : 0;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object object) {

        final boolean result;
        final AbstractEntity entity;

        if (object != null) {

            entity = (AbstractEntity) object;

            if (this == object) {
                result = true;
            } else if (getClass() != object.getClass()) {
                result = false;
            } else {
                result = this.id == null || this.id.equals(entity.getId());
            }

        } else {
            result = false;
        }

        return result;

    }

    /**
     * This method returns value of the entity id.
     *
     * @return value of the entity id
     */
    public Integer getId() {

        final Integer result;

        if (this.id != null) {
            result = this.id;
        } else {
            result = 0;
        }

        return result;

    }

    /**
     * This method sets new value of the entity id.
     *
     * @param newId new value of th e entity id
     */
    public void setId(final Integer newId) {

        final String debugString
                = " Attribute is null in method setId(Integer).";

        if (newId != null) {
            this.id = newId;
        } else {
            logger.log(Level.DEBUG, debugString);
        }

    }

}
