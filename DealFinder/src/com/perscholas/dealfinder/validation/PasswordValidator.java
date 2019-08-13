package com.perscholas.dealfinder.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator that implements PasswordConstraint and ensures passwords are entered in the correct format
 *
 */
public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String> {
	public static final Pattern VALID_PASSWORD_ADDRESS_REGEX = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]{5,}$"); 
	public static boolean validate(String password) { 
		Matcher matcher = VALID_PASSWORD_ADDRESS_REGEX.matcher(password); 
		return matcher.find(); 
	}
	
	@Override
	public void initialize(PasswordConstraint pc) { 
		
	}
	
	@Override
	public boolean isValid(String str, ConstraintValidatorContext ctxt) {
		return (validate(str) && 
				(str.length() > 4) && (str.length() < 144));
	}

}