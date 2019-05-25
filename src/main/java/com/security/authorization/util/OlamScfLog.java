package com.security.authorization.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to enable logging at method level.
 * @AgitLog(args = true, returnValue = true, debug = true, time = false)
 */
@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface securityScfLog {

		/**
	     * @return Method description
	     */
	    String value() default "";

	    /**
	     * @return Should the arguments be logged?
	     */
	    boolean args() default true;

	    /**
	     * @return Should the time taken be logged?
	     */
	    boolean time() default true;

	    /**
	     * @return Should the return value be logged?
	     */
	    boolean returnValue() default false;

	    /**
	     * @return Should the logging be done in debug level?
	     */
	    boolean debug() default true;

		/**
		 * @return Threshold time for the method called
		 */
		int threshold() default 200;

	}
