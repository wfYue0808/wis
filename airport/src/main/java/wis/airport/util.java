package wis.airport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class util {

	
	
    /**
     * 从接口获取数据
     * @param time
     * @return
     */

   public static  String getTms(String from,String to) {
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
	/**
	 * 获取每周的具体星期一日期 例：num=0 本周星期一  num =-7上周星期一
	 * @return
	 */
    public static String getLastMonday(int num) {
    	SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");   	
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.DATE, num);
    	cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); 
    	String str = sf.format(cal.getTime());
    	return str;
    }
	
 
    public static long dateFormat(String time) {
    	if(time.trim().length()==8) {
    		time = time +"000000";
    	}
    	SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
    	Long s = null;
		try {
			s = sf.parse(time).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return s;
    }
    
    
    
}
