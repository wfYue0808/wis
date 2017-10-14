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

 
          
        public static void main(String[] args) {  
           
  
              
        	downFile("10.1.72.41", 21, "pub_nwp", "nafp8nmic", "NAFP/CMA/GRAPES_GFS/20171009/00/",null, "d:/aaa");
        	
        	
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
