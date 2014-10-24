package com.example.contracts.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtil {

    private static ClassPathXmlApplicationContext ac = null;

    static {
	try {
	    ac = new ClassPathXmlApplicationContext(new String[] { "spring-config.xml" });
	} catch (Throwable ex) {
	    System.err.println("Initial ClassPathXmlApplicationContext creation failed." + ex);
	    throw new ExceptionInInitializerError(ex);
	}
    }

    public static ApplicationContext getApplicationContext() {
	return ac;
    }

}
