package com.colorcc.user.register.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented  
@Retention(RetentionPolicy.RUNTIME)  
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.METHOD})  
@Constraint(validatedBy = {IntegerScopeValidationImpl.class}) 
public @interface IntegerScopeValidation {
	public int min();
	
	public int max();
	
	public String message();
	
	public Class<?>[] groups() default {};  
	  
    Class<? extends Payload>[] payload() default {};  
    
    public int value() default 0;
}
