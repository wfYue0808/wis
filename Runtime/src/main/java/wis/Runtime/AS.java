package wis.Runtime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class AS {

	
	   public static void main(String[] args) throws Exception {
			Set<String> pidNameSet = new HashSet<String>();
			InputStream is = null;
			InputStreamReader ir = null;
			BufferedReader br = null;
			String line = null;
			String[] array = (String[]) null;
			try {
				Process p = Runtime.getRuntime().exec("TASKLIST /NH /FO CSV");
				is = p.getInputStream();
				ir = new InputStreamReader(is);
				br = new BufferedReader(ir);
				while ((line = br.readLine()) != null) {
					array = line.split(",");
					line = array[0].replaceAll("\"", "");
					line = line.replaceAll(".exe", "");
					line = line.replaceAll(".exe".toUpperCase(), "");
					
						pidNameSet.add(line);
					System.out.println(line);
				}
			} catch (IOException localIOException) {
				throw new Exception("获取系统所有进程名出错！");
			} finally {
				if (br != null) {
					br.close();
				}
				if (ir != null) {
					ir.close();
				}
				if (is != null) {
					is.close();
				}
			}
	}
}
