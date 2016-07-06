package cc.pizzr.validation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 
 * @author lijinting01
 * 
 */
@Target(TYPE)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { FieldEqualsValidator.class })
public @interface FieldEqualsConstraint {

	/**
	 * 
	 * @return
	 */
	String targetField() default "";

	/**
	 * Compare source
	 * 
	 * @return
	 */
	String[] compareTo() default {};

	/**
	 * Default Message
	 */
	String message() default "";

	/**
	 * 
	 * @return
	 */
	Class<?>[] groups() default {};

	/**
	 * 
	 * @return
	 */
	Class<? extends Payload>[] payload() default {};
}
