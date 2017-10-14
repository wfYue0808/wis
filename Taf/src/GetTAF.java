import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

public class GetTAF {

	public static Map<String, String> map = new HashMap<>();
	

	
	public static void main(String[] args) throws Exception {
		new TAF().getTAF("SEVP_CV_UPAR_TTKK", "20170901000000", "20170930000000");
		System.out.println("----------------------------------------");
			List<String> list=FileUtils.readLines(new File("aaa.txt"));
		for (String str : list) {
			map.put(str.trim(), "1");
		}
		getFile();
	    System.out.println("*****//////////////////////////////////");

	}
	
	public static void parseFile(File file) throws Exception {
		List<String> list = new ArrayList<>();	
		 String str	=FileUtils.readFileToString(file);
		 String[] a = str.split("c2017");
		  for (String s : a) {
			  if(s!=null && !"".equals(s) && s.length()>20) {
				  String[] aa = s.split(","); 
				  System.out.println(aa[2].substring(1, 5));
					if(map.get(aa[2].substring(1, 5))!=null) {
						System.out.println("--------------------");
						 String aaa = aa[2]+"\t"+aa[5]+"\t"+aa[19].split("\n")[0];
				         list.add(aaa);
					}
			  }		
		}
		  FileUtils.writeLines(new File("e:/taf/test1.txt"), list,true);
		 System.out.println("****************************");
		 
		  
	}
	
	public static void getFile() {
		     File file = new File("e:/tmp");
		     String[] fileList  =file.list();
		     for (String str : fileList) {
		    	 System.out.println(str);
		    	 File f = new File("e:/tmp"+File.separator+str);
				if(f.isFile()) {
					try {
						System.out.println("½âÎö");
						parseFile(f);
						//f.delete();
					} catch (Exception e) {
						
						e.printStackTrace();
					}
				}
			}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
