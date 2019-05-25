package com.security.authorization.util;

import com.security.authorization.servicesimpl.UserRolesServiceImpl;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Interceptor for methods annotated with {@link securityScfLog}
 */
public class MethodInterceptor {

    private static final String TIME = "TIME";
    private static final String METHOD = "METHOD";
	private static final Logger LOG = LoggerFactory
			.getLogger(UserRolesServiceImpl.class);

    public Object logTime(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Logger logger = LoggerFactory.getLogger(pjp.getTarget().getClass());
        String methodSignature = pjp.getSignature().toShortString();
        logger.trace("Entering {}", methodSignature);
        logger.debug("Entering {}", methodSignature.toString());
        try {
            return pjp.proceed();
        } finally {
            stopWatch.stop();
            if(logger.isDebugEnabled()) {
                StringBuilder buffer = new StringBuilder();
                buffer.append(METHOD).append('=').append(methodSignature);
                buffer.append(", ").append(TIME).append('=').append(stopWatch.getTime());
                logger.debug(buffer.toString());
            }
        }
    }

    /**
     * Interceptor method for 
     * @param pjp
     * @param agitLog
     * @return return value of the method intercepted
     * @throws Throwable
     */
    public Object invoke(ProceedingJoinPoint pjp, securityScfLog securityScfLog) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object returnVal = null;
        try {
            returnVal = pjp.proceed();
            return returnVal;
        } catch (Exception e) {
            returnVal = e.getClass().getName() + ": " + e.getMessage();
            throw e;
        } finally {
            stopWatch.stop();
			long time = stopWatch.getTime();
            Logger logger = LoggerFactory.getLogger(pjp.getTarget().getClass());

            boolean debug = securityScfLog.debug();
			boolean exceededThreshold = securityScfLog.threshold() < time;
			if ((debug && logger.isDebugEnabled()) || (!debug && logger.isInfoEnabled()) || exceededThreshold) {
                String methodSignature = pjp.getSignature().toShortString();
                Object[] args = pjp.getArgs();

                StringBuilder buffer = new StringBuilder();
                buffer.append(METHOD).append('=').append(methodSignature);

                String value = securityScfLog.value();
                if (!value.isEmpty()) {
                    buffer.append(", ").append(" API=").append(value);
                }

				if (securityScfLog.time() || exceededThreshold) {
					buffer.append(", ").append(TIME).append('=').append(time);
                }
                boolean agitLogArgs = securityScfLog.args();
                if (agitLogArgs && args.length > 0) {
                    for (int argIndex = 0; argIndex < args.length; argIndex++) {
                        buffer.append(", ").append('a').append(argIndex).append('=').append(args[argIndex]);
                    }
                }
                if (securityScfLog.returnValue()) {
                    buffer.append(", ").append("RETURN").append('=').append(returnVal);
                }

				if (exceededThreshold) {
					logger.warn(buffer.toString());
				} else if (debug) {
                    logger.debug(buffer.toString());
                } else {
                    logger.info(buffer.toString());
                }
            }
        }
    }

}
