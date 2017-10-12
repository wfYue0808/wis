package wis.airport;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;



@Repository
public class AirportMapper {
  
	// private Logger log = LoggerFactory.getLogger(this.getClass());
	//存储近一周数据的map
	public static Map<String, Map<String,Airport>> map = new HashMap<String, Map<String,Airport>>();
	//识别机场map
	public static Map<String, String> identifyMap = new HashMap<String, String>();
	
	 
	public static void init() {
		List<String> airportList = null;
		try {
			airportList = FileUtils.readLines(new File("airport.txt"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		     for (String str: airportList) {
			  String[] airport  = str.split("\t");
			  if(airport[0].length()==5) {
				 airport[0]=airport[0].substring(1); 
			  }
			  identifyMap.put(airport[0],airport[1]);		
			  map.put(airport[0].trim(), null);
		}
		   
	     System.out.println(map.size()+"-----------------"+identifyMap.size());
	}
	
    public Map<String, Map<String,Airport>> dataSouce() throws Exception {
    	Map<String, Map<String,Airport>> map1 = null;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Calendar.HOUR_OF_DAY-1);
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
       // sf.format(calendar.getTime())
        String json=util.getTms(sf.format(calendar.getTime()),sf.format(new Date()));
        int i = json.lastIndexOf("[");
        int j = json.indexOf("[");
        if(i==j) {
        	System.out.println("过去一小时内无数据更新");
        }else {
        	json = json.substring(i).toLowerCase();
        	map1=parseJson(json,false,util.getLastMonday(0));
        }
    	return map1;
    }

    /**
     * 解析json串
     * @param json 要解析的json串
     * @param  flag true解析完毕的数据不写文件，false写文件
     * @throws Exception
     */
    public  Map<String, Map<String,Airport>> parseJson(String json,Boolean flag,String writeDes) throws Exception, Exception, Exception {
    	
    	ObjectMapper mapper = new ObjectMapper();
      
        List<Airport> list   = mapper.readValue(json, new TypeReference<List<Airport>>() {});
        Map<String, Map<String,Airport>> map1 =new HashMap<String, Map<String,Airport>>();;
        System.out.println(list.size()+"-----json中的airpor个数 ------------------------");
        for (Airport airport : list) {
        	
			if(identifyMap.containsKey(airport.getShortname().toUpperCase() )) {
				
				String mon = airport.getMon()+"";
				if(airport.getMon()<10) {
					mon = "0"+mon;
				}
				String day = airport.getDay()+"";
				if(airport.getDay()<10) {
					day = "0"+day;
				}
				String hour = airport.getHour()+"";
				if(airport.getHour()<10) {
					hour = "0"+hour;
				}
				String min = airport.getMin()+"";
				if(airport.getMin()<10) {
					min ="0"+min;
				}
				String time = airport.getYear()+""+mon+""+day+""+hour+""+min+"00";
			    String name = identifyMap.get(airport.getShortname().toUpperCase());
			    airport.setAirportName(name);
			   
			   
			    if(map.get(airport.getShortname().toUpperCase())!=null) {
			    	map.get(airport.getShortname().toUpperCase()).put(time, airport);
			    }else {
			    	 Map<String, Airport> temp = new HashMap<String, Airport>();
					    temp.put(time, airport);
					    map.put(airport.getShortname().toUpperCase(), temp);
			    }
			    //写入文件
			    if(!flag) {
			    	//System.out.println("写入文件"+airport);
			    	String str = mapper.writeValueAsString(airport);
				    new Cache().saveJsonToDocument(","+str, "airport",writeDes);
			    }
			    
			}
		}
          return map1;
    }
	
	
}
