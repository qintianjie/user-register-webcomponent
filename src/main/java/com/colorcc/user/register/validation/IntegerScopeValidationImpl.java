package com.colorcc.user.register.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IntegerScopeValidationImpl implements ConstraintValidator<IntegerScopeValidation, Integer> {

	private int value;
	private int min;
	private int max;

	@Override
	public void initialize(IntegerScopeValidation constraintAnnotation) {
		this.min = constraintAnnotation.min();
		this.max = constraintAnnotation.max();
	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		this.value = value;
		return (min <= this.value) && (this.value <= max);
	}

}
