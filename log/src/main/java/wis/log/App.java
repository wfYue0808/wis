package wis.log;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	PropertyConfigurator.configure("application.properties");
    	 Logger log = Logger.getLogger(App.class.getClass());  
         log.info("测试info");  
         log.debug("测试debug");  
         log.error("测试error");  
         System.out.println("---");
    }
}
