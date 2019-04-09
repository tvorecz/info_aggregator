package epam.labs.dzmitry.zorych.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class AspectLogger {
    private final static Logger EXCEPTION_LOGGER = LoggerFactory.getLogger(AspectLogger.class);

    public AspectLogger() {
        EXCEPTION_LOGGER.error("Construct");
    }

    @Pointcut("execution(* epam.labs.dzmitry.zorych.dao.impl.*.*(..)) && execution(* epam.labs.dzmitry.zorych.mediator.impl.*.*(..))")
    private void logExceptions() {}


    @AfterThrowing(value = "logExceptions()", throwing = "ex")
    public void logException(Exception ex) throws Throwable {
        EXCEPTION_LOGGER.error("Repository error: ", ex);

        throw ex;
    }
}

