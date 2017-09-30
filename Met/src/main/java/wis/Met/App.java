package wis.Met;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	Map<String, String>  map = init();
    	for (int i = 1; i <31; i++) {
			String date1 = getDate(i);
			String json = dataSource(date1+"000000", date1+"235900");
			parse(json,map);
		}
    	
    }
    
    public static  Map<String, String> init() throws Exception {
    	  List<String> list = FileUtils.readLines(new File("qq.txt"));
          Map<String, String>  map= new HashMap<String,String>();
      	for (String s : list) {
  			String[] str = s.split("\t");
  			if(str[0].trim().length()==5) {
  				map.put(str[0].trim().toLowerCase().substring(1),str[1].trim()); 
  			}else {
  				map.put(str[0].trim().toLowerCase(),str[1].trim()); 
  			}
  			
  		
      	}
      	return map;
    }
    
    
    public static String getDate(int day ) {   	
    	Calendar cal = Calendar.getInstance();
    	cal.set(Calendar.DAY_OF_MONTH, day);
    	SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
    	String time =  sf.format(cal.getTime());
    	System.out.println(time);
    	return time;   	
    }
    
    public static String dataSource(String from ,String to) {
     	String res=null;  
  	  try {  
            //创建一个url实例  
  	      String url1="http://10.20.76.31:8008/cimiss-web/api?userId=NMC_YBS_yangmeiling&pwd=123456&interfaceId=getSurfEleByTimeRange&dataCode=SURF_WEA_WAFS_META&elements=Year,Mon,Day,Hour,Min,Lat,Lon,TEM,DPT,AirTermin_ID&timeRange=["+from+","+to+"]&dataFormat=json";
           // URL url=new URL("http://10.20.76.31:8008/cimiss-web/api?userId=NMC_YBS_yangmeiling&pwd=123456&interfaceId=getSurfEleByTime&dataCode=SURF_WEA_WAFS_META&elements=Lon,TEM,DPT&times="+time+"000000&dataFormat=json");  
            //通过url的openStream获取url对象所表示资源的字节输入流  
  	      URL url = new URL(url1);
            InputStream is=url.openStream();  
            //将字节输入流转换为字符输入流  
            InputStreamReader isr=new InputStreamReader(is,"utf-8");  
            //为字符输入流添加缓冲  
            BufferedReader br=new BufferedReader(isr);  
            
            String line=null;  
            //读取数据  
            while((line=br.readLine())!=null){  
                res+=line;  
               
            }  
            br.close();  
            isr.close();  
            is.close();  
           // System.out.println(res);  
        } catch (MalformedURLException e) {  
           
            e.printStackTrace();  
        } catch (IOException e) {  
           
            e.printStackTrace();  
        }
		return res; 
    }
  
    public static void parse(String json,Map<String, String>  map) throws Exception, Exception, Exception {
    	   int i = json.lastIndexOf("[");
           int j = json.indexOf("[");
           if(i==j) {
           	System.out.println("无数据更新");
           }else {
           	json = json.substring(i).toLowerCase();
            ObjectMapper mapper = new ObjectMapper();
       	 List<METER> list = mapper.readValue(json, new TypeReference<List<METER>>() {});
   		 List<String> list1 = new ArrayList<String>();
   		
   		
   		 for (METER airport : list) {
   			// System.out.println(map.get(airport.getShortName())+map.size());
   			 if(map.get(airport.getShortName())!=null) {
   				airport.setAirportName(map.get(airport.getShortName()));   			
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
   			list1.add(airport.getAirportName()+"\t"+airport.getShortName()+"\t"+time+"\t"
   					+airport.getLat()+"\t"+airport.getLon()+"\t"+airport.getDpt()+"\t"
   					+airport.getTem());
   			 }
   		 }
       	FileUtils.writeLines(new File("d:/aaa/met.txt"), list1, true); 
           }
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
