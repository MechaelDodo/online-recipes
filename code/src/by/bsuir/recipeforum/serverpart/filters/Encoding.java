package by.bsuir.recipeforum.serverpart.filters;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * This filter class to set page encoding.
 */
public class Encoding implements Filter {

    /**
     * Logger for debug.
     */
    private static Logger logger;

    /**
     * Value of the code.
     */
    private String code;

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(final FilterConfig config) {

        code = config.getInitParameter("encoding");
        logger = LogManager.getLogger(Encoding.class);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain chain)
            throws ServletException, IOException {

        final String codeRequest = request.getCharacterEncoding();
        final String debugString = " Page encoding installed.";

        if (this.code != null && !this.code.equalsIgnoreCase(codeRequest)) {

            request.setCharacterEncoding(this.code);
            logger.log(Level.DEBUG, debugString);

        }

        chain.doFilter(request, response);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {

        code = null;

    }

}
