package wis.Runtime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	 StringBuilder sb = new StringBuilder();
    	try {
			//Runtime.getRuntime().exec("cmd /C Start  http://www.hao123.net/");
    		Process process = Runtime.getRuntime().exec("jps -l ");
    		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            process.getOutputStream().close();
            reader.close();
            process.destroy();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println(sb);
      //  System.out.println( "maxMemory"+Runtime.getRuntime().maxMemory()+"\n"+"freemeory"+Runtime.getRuntime().freeMemory());
    }
}
