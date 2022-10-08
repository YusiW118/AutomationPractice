package automation.utilities;


import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.xml.XmlConfigurationFactory;

public class Log {
    
    private static Logger logger= LogManager.getLogger(Log.class.getName());

    public static Logger getLogData(String className) throws URISyntaxException{
        File f=new File("D:\\yw\\code\\java\\AutomationPractice\\log4j.xml");


        ConfigurationFactory.setConfigurationFactory(new XmlConfigurationFactory());
        Configurator.reconfigure(f.toURI());
        //context.setConfigLocation(path.toURI());
        return LogManager.getLogger(className);
       
    }

    public static void startTest(String testName){
        logger.info("Test called: "+ testName + "has started");
    }
    public static void endTest(String testName){
        logger.info("Test called: "+ testName + "has ended");
        
    }

    public static void info(String message){
        logger.info(message);
    }

    public static void warning(String message){
        logger.warn(message);
    }

    public static void error(String message){
        logger.error(message);
    }

    public static void fatal(String message){
        logger.fatal(message);
    }

    public static void debug(String message){
        logger.debug(message);
    }
}
