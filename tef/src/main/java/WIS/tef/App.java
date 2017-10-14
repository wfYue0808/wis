package WIS.tef;

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
import java.util.List;

import javax.swing.text.SimpleAttributeSet;

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
    	 List<String> list =  FileUtils.readLines(new File("11.txt"));
    	
    	 String latlon = "";
    	 for (String string : list) {
			String[] str = string.split("\t");
			String s ="," +str[0]+"/"+str[1];
			latlon =latlon+ s;
		}
    	for(int i =1;i<31;i++) {
    	 String time = getDate(i);
    	 String json = getTms(time+"000000", latlon.substring(1));     	 
    	   parse(json);
    	   System.out.println(time+"000000");
    	 String json1 = getTms(time+"120000", latlon.substring(1));     	 
    	   parse(json1); 
    	   System.out.println(time+"120000");
    	} 
    	
    	System.out.println("-------------------");
    
    }
    
    
    /**
     * 解析json成对象，写入文件
     * @param json
     * @throws Exception
     * @throws Exception
     * @throws Exception
     */
    public static void  parse(String json) throws Exception, Exception, Exception {
    	
    	ObjectMapper mapper = new ObjectMapper();
    	
        int i = json.lastIndexOf("[");
     
        	json = json.substring(i);
        	
        
       List<TEF> list   = mapper.readValue(json, new TypeReference<List<TEF>>() {});
       System.out.println(list.size()+"----------------");
       List<String> list1 = new ArrayList<String>();
       for (TEF tef : list) {
			list1.add(tef.getLat()+"\t"+tef.getLon()+"\t"+tef.getValidtime()+"\t"+tef.getTef0());
		}
       System.out.println("开始写入");
       FileUtils.writeLines(new File("d:/aaa/test.txt"), list1, true);
       System.out.println("xieru 完成");
    }
    
    
    /**
     * 获取当月第i 天的日期
     * @param i
     * @return
     */
    public static String getDate(int i ) {
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.MONTH, -1);
    	cal.set(Calendar.DAY_OF_MONTH, i);
    	System.out.println(cal.getTime());    	
    	SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
    	   String time =  sf.format(cal.getTime());
    	System.out.println(time);
    	return time;
    }
    
    /***
     * 从接口取出指定时间指定经纬度的json 串    
     * @param time
     * @param latlon
     * @return
     */
    public static  String getTms(String time,String latlon) {
    	String res=null;  
    	  try {  
              //创建一个url实例  
    		  String url1="http://10.20.76.31:8008/cimiss-web/api?userId=NMC_YBS_yangmeiling&pwd=123456&interfaceId=getNafpEleAtPointByTimeAndLevelAndValidtimeRange&dataCode=NAFP_FOR_FTM_HIGH_EC_GLB&time="+time+"&fcstLevel=0&minVT=0&maxVT=96&latLons="+latlon+"&fcstEle=TEF0&dataFormat=json";
    		 //通过url的openStream获取url对象所表示资源的字节输入流  
    		  System.out.println(url1);
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
                 System.out.println("================");
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
}
