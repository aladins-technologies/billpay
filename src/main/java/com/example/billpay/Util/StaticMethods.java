package com.example.billpay.Util;

import java.sql.Timestamp;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public final class StaticMethods {

	public static final String APP_MAIL_ID = "billpaycreditcard@gmail.com"; 

	public static <E extends Enum<E>> boolean isValidEnum(final Class<E> enumClass, final String enumName) {
        if (enumName == null) {
            return false;
        }
        try {
            Enum.valueOf(enumClass, enumName);
            return true;
        } catch (final IllegalArgumentException ex) {
            return false;
        }
    }
	
	public static int intValue(Boolean b) {
	    int intValue = 0;
	    if (b) {
	    	intValue = 1;
	    }
	    return intValue;
	}
	
	public static String getDate(Timestamp time) {
		String timeString = time.toString();
		String output = timeString.substring(0, 10);
		return output;
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
