package wis.airport;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class Cache {

	 private Logger log =LoggerFactory.getLogger(this.getClass());
	 @Autowired
	 private AirportMapper airportMapper;
	
	 public String getPropertis() {
		 InputStream inputStream = ClassLoader.getSystemResourceAsStream("application.properties");
		 Properties p = new Properties();
		 try {
			p.load(inputStream);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		 String path = p.getProperty("cacheAddress");
		 File f = new File(path);
		 if(!f.exists()) {
			 f.mkdirs();
		 }
		 return path;
	 }

	 
	 public void save(String shortName,String saveName,Airport airport)  {
		
		 File file = new File(getPropertis()+File.separator+shortName+File.separator+saveName);
		 if(!file.exists()) {
			 file.mkdirs();
			 OutputStream out = null;
			 ObjectOutputStream obj = null;
			 try {
				 out = new FileOutputStream(file);
				  obj = new ObjectOutputStream(out);
				 obj.writeObject(airport);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if(out!=null) {
					try {
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(obj!=null ) {
					 try {
						obj.flush();
						 obj.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			 
		 }
	 }
	 /**
	  * 写入文档
	  * @param json
	  * @param fileName
	  */
	 
	 public void saveJsonToDocument (String json,String fileName) {
		 File f = new File(getPropertis()+File.separator+fileName+File.separator+util.getLastMonday(-7)+".txt");
		 if(!f.exists()) {
			 f.mkdirs();
		 }
		 try {
			FileUtils.writeStringToFile(new File(fileName), json, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 		 		 		 
	 }
	 /**
	  * 删除文档及缓存
	  */
	public void deleteCacheAndDocument() {
		
		
		File f = new File(getPropertis()+File.separator+File.separator+"airport"+util.getLastMonday(-7)+".txt");
		if(f.exists()) {
			f.delete();
		}
		
		long l = util.dateFormat(util.getLastMonday(-7));
		Set<Map.Entry<String, Map<String,Airport>>> set = AirportMapper.map.entrySet();
		Iterator<Map.Entry<String, Map<String,Airport>>> it = set.iterator();
		while(it.hasNext()) {
			Entry<String, Map<String, Airport>>  m = it.next(); 
			 Map<String,Airport>  m1  =   m.getValue();
			 if(m1!=null) {
				  Set<String> set1 =  m1.keySet();
				  Iterator<String> itertor = set1.iterator();
				  while(itertor.hasNext()) {
					  String str = itertor.next();
					 if(util.dateFormat(str)<l) {
						 itertor.remove();
					 }
				  }
			 }
			
		}
		
	}
	 /**
	  * 读取缓存文件        查找上周缓存文件，若存在，直接读取缓存文件，不存在从接口查询上周一至现在的数据
	  * @throws Exception
	  */
	 public void readDocument() throws Exception {
		System.out.println("读取缓存文件");
		 String json =null;
			File f = new File(getPropertis()+File.separator+File.separator+"airport"+util.getLastMonday(-7)+".txt");
			if(f.exists()) {
				
			  json  = FileUtils.readFileToString(f);	
			   airportMapper.parseJson(json,true);
			   File f1 = new File(getPropertis()+File.separator+File.separator+"airport"+util.getLastMonday(0)+".txt");
			   if(f1.exists()) {
				   json = FileUtils.readFileToString(f1);
				   airportMapper.parseJson(json,true);
			   }
			}else {
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DAY_OF_MONTH,-7);
				cal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
				SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
				SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
				 String one = util.getLastMonday(0)+"000000";  //本周一
				 String four = sf1.format(cal.getTime())+"000000"; //上周四
			    
				 log.info("获取上周一至上周四数据，初次加载，耐心等待...");
				 get141NowData(util.getLastMonday(-7)+"000000", four);
				 log.info("获取上周四至本周一数据...");
				 get141NowData(four, one);
			     
				 cal.add(Calendar.DAY_OF_WEEK, 0);
			     cal .set(Calendar.DAY_OF_WEEK,Calendar.THURSDAY);
			     String fourNow = sf1.format(cal.getTime())+"000000";
			     if(cal.getTime().getTime()<new Date().getTime()) {
			    	 log.info("获取本周一至周四数据..");
			    	 get141NowData(one, fourNow);
			    	 log.info("获取本周四至现在的数据..");
			    	 get141NowData(fourNow, sf.format(new Date()));
			     }else {
			    	 log.info("获取本周一至现在的数据..");
			    	 get141NowData(one, sf.format(new Date()));
			     }
			   
			}
			
			
	 }
	 /**
	  * 分段从接口获取数据周一至周四，周四至周一，解析
	  * @param from
	  * @param to
	 * @throws Exception 
	  */
	 public void get141NowData(String from ,String to) throws Exception {
		 
		  String json = util.getTms(from, to);
		  int i = json.lastIndexOf("[");
	      int j = json.indexOf("[");
	      if(i!=j) {
	    	  json = json.substring(i).toLowerCase();
		      airportMapper.parseJson(json,true);
	      }else {
	    	  log.info("从"+from+"至"+to+"，接口中无数据！");
	      }
	      
		 
	 }
	 /**
	  * 原缓存map和新增数据合并成一个map
	  * @param m
	  * @return
	  */
	  public Map<String, Map<String,Airport>> addNewData(Map<String, Map<String,Airport>> m) {
		  
			Set<Entry<String, Map<String, Airport>>> entry= AirportMapper.map.entrySet();
		      Iterator<Entry<String, Map<String, Airport>>> it = entry.iterator();
		      while(it.hasNext()) {
		    	   Entry<String, Map<String, Airport>>  e= it.next();
		    	   Map<String, Airport> temp = e.getValue();
		    	   Set<Entry<String, Airport>> tempEntry =  temp.entrySet(); 
		    	   Iterator<Entry<String, Airport>> tempIt = tempEntry.iterator();
		    	   while(tempIt.hasNext()) {
		    		   Entry<String, Airport> t = tempIt.next();
		    		   if(m.containsKey(e.getKey())) {
		    			  m.get(e.getValue()).put(t.getKey(), t.getValue()); 
		    		   }else {
		    			   Map<String, Airport> mm = new HashMap<String, Airport>();
		    			   mm.put(t.getKey(), t.getValue());
		    			   m.put(e.getKey(), mm);
		    			   
		    		   }
		    		   
		    	   }
		      }
		     return m; 
	  }
	 
	 
	 
}
