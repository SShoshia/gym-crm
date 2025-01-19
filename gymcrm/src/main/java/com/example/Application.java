package com.example;

import com.example.gymcrm.config.AppConfig;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;

public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        Tomcat tomcat = new Tomcat();

        tomcat.setPort(8080);
        tomcat.setHostname("localhost");
        String appBase = ".";
        tomcat.getHost().setAppBase(appBase);

        File docBase = new File(System.getProperty("java.io.tmpdir"));
        Context context = tomcat.addContext("", docBase.getAbsolutePath());

        GenericWebApplicationContext webApplicationContext = new GenericWebApplicationContext();
        ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
        webApplicationContext.setParent(appContext);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(webApplicationContext);
        tomcat.addServlet("/", "dispatcherServlet", dispatcherServlet);

        try {
            tomcat.start();
            tomcat.getServer().await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}