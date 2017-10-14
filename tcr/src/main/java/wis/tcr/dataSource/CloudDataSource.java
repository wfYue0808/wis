package wis.tcr.dataSource;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class CloudDataSource {

    public static boolean saveUrlAs(String photoUrl, String fileName) {  
        //此方法只能用户HTTP协议  
            try {  
              URL url = new URL(photoUrl);  
              HttpURLConnection connection = (HttpURLConnection) url.openConnection();  
              DataInputStream in = new DataInputStream(connection.getInputStream());  
              DataOutputStream out = new DataOutputStream(new FileOutputStream(fileName));  
              byte[] buffer = new byte[4096];  
              int count = 0;  
              while ((count = in.read(buffer)) > 0) {  
                out.write(buffer, 0, count);  
              }  
              out.close();  
              in.close();  
              return true;  
            }  
            catch (Exception e) {  
              return false;  
            }  
          }  
           
        public String getDocumentAt(String urlString) {  
        //此方法兼容HTTP和FTP协议  
            StringBuffer document = new StringBuffer();  
            try {  
              URL url = new URL(urlString);  
              URLConnection conn = url.openConnection();  
              BufferedReader reader = new BufferedReader(new InputStreamReader(conn.  
                  getInputStream()));  
              String line = null;  
              while ( (line = reader.readLine()) != null) {  
                document.append(line + "/n");  
              }  
              reader.close();  
            }  
            catch (MalformedURLException e) {  
              System.out.println("Unable to connect to URL: " + urlString);  
            }  
            catch (IOException e) {  
              System.out.println("IOException when connecting to URL: " + urlString);  
            }  
            return document.toString();  
          }  
          
        public static void main(String[] args) {  
           
              
             /* String photoUrl = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png";                                    
              String fileName = photoUrl.substring(photoUrl.lastIndexOf("/"));  
              String filePath = "d:/";  
              boolean flag = saveUrlAs(photoUrl, filePath + fileName);  
              System.out.println("Run ok!/n<BR>Get URL file " + flag);  */
              
        	downFile("10.1.72.41", 21, "pub_nwp", "nafp8nmic", "NAFP/CMA/GRAPES_GFS/20170924/12",null, "d:/aaa");
        	
        	
        }  
        
        public static boolean downFile(  
                String url, //FTP服务器hostname  
                int port,//FTP服务器端口  
                String username, //FTP登录账号  
                String password, //FTP登录密码  
                String remotePath,//FTP服务器上的相对路径   
                String fileName,//要下载的文件名  
                String localPath//下载后保存到本地的路径  
                ) {    
            boolean success = false;    
            FTPClient ftp = new FTPClient();    
            try {    
                int reply;    
                ftp.connect(url, port);    
                //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器     
                ftp.login(username, password);//登录     
                reply = ftp.getReplyCode();    
                if (!FTPReply.isPositiveCompletion(reply)) {    
                    ftp.disconnect();    
                    return success;    
                }    
                ftp.changeWorkingDirectory(remotePath);//转移到FTP服务器目录     
                FTPFile[] fs = ftp.listFiles();    
                for(FTPFile ff:fs){    
                   // if(ff.getName().equals(fileName)){    
                        File localFile = new File(localPath+"/"+ff.getName());    
                        OutputStream is = new FileOutputStream(localFile);     
                        ftp.retrieveFile(ff.getName(), is);    
                        is.close();    
                   // }    
                }    
                    
                ftp.logout();    
                success = true;    
            } catch (IOException e) {    
                e.printStackTrace();    
            } finally {    
                if (ftp.isConnected()) {    
                    try {    
                        ftp.disconnect();    
                    } catch (IOException ioe) {    
                    }    
                }    
            }    
            return success;    
        }
}
