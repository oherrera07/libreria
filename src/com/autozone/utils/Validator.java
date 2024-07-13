package com.autozone.utils;

import java.lang.reflect.Field;

import com.autozone.annotations.Email;
import com.autozone.annotations.NotNull;

public class Validator {

	public static void validate(Object obj) throws IllegalArgumentException, IllegalAccessException {
		Field[] fields = obj.getClass().getDeclaredFields();
		for(Field field : fields) {
			field.setAccessible(true);
			
			//NotNull validation
			if(field.isAnnotationPresent(NotNull.class)) {
				Object value = field.get(obj);
				
				if(value == null) {
					throw new IllegalArgumentException(field.getName()+ " no puede ser nulo");
				}
			}
			
			//Email validation
			if(field.isAnnotationPresent(Email.class)) {
				
				String value = (String) field.get(obj);
				
				if(value != null && !value.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
					throw new IllegalArgumentException(field.getName()+" no es un correo valido");
				}
			}
		}
	}
	
}
