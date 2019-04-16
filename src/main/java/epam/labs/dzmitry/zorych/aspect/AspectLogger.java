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

    @Pointcut("execution(public * epam.labs.dzmitry.zorych.dal.dao.impl.JsonDaoImpl.*(..)) || execution(public * epam.labs.dzmitry.zorych.service.mediator.impl.ServiceMediator.*(..))")
    private void logExceptions() {}


    /**
     * Logging errors using Spring Aspect
     * @param ex Exception for logging
     * @throws Throwable
     */
    @AfterThrowing(value = "logExceptions()", throwing = "ex")
    public void logException(Exception ex) throws Throwable {
        EXCEPTION_LOGGER.error("Exception: ", ex);

        throw ex;
    }
}

