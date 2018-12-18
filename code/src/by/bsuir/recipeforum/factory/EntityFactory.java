package by.bsuir.recipeforum.factory;

import by.bsuir.recipeforum.entity.AbstractEntity;
import by.bsuir.recipeforum.exception.ApplicationException;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * */
public interface EntityFactory<T extends AbstractEntity> {

    /**
     *
     * */
    T createEntity();

    /**
     *
     * */
    T createEntity(HttpServletRequest request) throws ApplicationException;

    /**
     *
     * */
    T createEntity(ResultSet resultSet) throws SQLException;

}
