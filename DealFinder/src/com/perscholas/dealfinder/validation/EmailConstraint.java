package com.perscholas.dealfinder.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = EmailValidator.class)
@Target( {ElementType.METHOD, ElementType.FIELD } )
@Retention(RetentionPolicy.RUNTIME)
/**
 * Constraint that ensures Email addresses are formatted correctly
 *
 */
public @interface EmailConstraint {
	String message() default "Invalid email format"; 
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {}; 
}
