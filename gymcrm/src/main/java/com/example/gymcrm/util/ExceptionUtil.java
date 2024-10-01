package com.example.gymcrm.util;

import org.slf4j.Logger;

public class ExceptionUtil {

    public static void error(Logger logger, String errorMessage, Class<? extends Exception> exceptionClass) throws Exception {
        logger.error(errorMessage);
        throw exceptionClass.getConstructor(String.class).newInstance(errorMessage);
    }


}
