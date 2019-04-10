package epam.labs.dzmitry.zorych.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Aspect for logging errors
 */
@Aspect
@Component
public class AspectLogger {
    private final static Logger EXCEPTION_LOGGER = LoggerFactory.getLogger(AspectLogger.class);
    private static int iter = 0;

    public AspectLogger() {
        EXCEPTION_LOGGER.error("Construct");
        ++iter;
        EXCEPTION_LOGGER.error(Integer.toString(iter));
    }

    @Pointcut("execution(* epam.labs.dzmitry.zorych.dao.*.*.*(..)) && execution(* epam.labs.dzmitry.zorych.mediator.*.*.*(..))")
    private void logExceptions() {}


    /**
     * Logging errors using Spring Aspect
     * @param ex Exception for logging
     * @throws Throwable
     */
    @AfterThrowing(value = "logExceptions()", throwing = "ex")
    public void logException(Exception ex) throws Throwable {
        EXCEPTION_LOGGER.error("Repository error: ", ex);

        throw ex;
    }
}

