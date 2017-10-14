

import java.util.HashMap;
import cma.cimiss.RetFilesInfo;
import cma.cimiss.client.DataQueryClient;

/*
 * 客户端调取，下载文件，并返回RetFilesInfo对象
 */
public class TAF {
  /*
   * main方法
   * 如：按时间段、站号检索雷达文件 getRadaFileByTimeRangeAndStaId
   */
  public  void getTAF(String dataCode,String timeFrom,String timeTo) {
    
    /* 1. 定义client对象 */
    DataQueryClient client = new DataQueryClient() ;
    
    /* 2. 调用方法的参数定义，并赋值 */
    /* 2.1 用户名&密码 */
    String userId = "NMC_YBS_yangmeiling" ;
    String pwd = "123456" ;
    /* 2.2  接口ID */
    String interfaceId = "getSevpFileByTimeRange" ;
    /* 2.3  接口参数，多个参数间无顺序 */
    HashMap<String, String> params = new HashMap<String, String>();
    //必选参数
   params.put("dataCode", dataCode); //资料：质控前标准格式单站多普勒雷达基数据
   params.put("timeRange", "["+timeFrom+","+timeTo+")"); //时间段，前闭后开  TODO:异常
  //  params.put("times", "20170510000000"); //时间段，前闭后开
    //params.put("staIds", "Z9859,Z9852,Z9856,Z9851,Z9855"); //雷达站
    //可选参数
    /* 2.4 文件的本地保持目录 */
    String saveDir = "e:/tmp/aaa" ;
    /* 2.5 返回文件的描述对象 */
    RetFilesInfo retFilesInfo = new RetFilesInfo() ;
    
    /* 3. 调用接口 */
    try {
      //初始化接口服务连接资源
      client.initResources() ;
      //调用接口
      int rst = client.callAPI_to_downFile(userId, pwd, interfaceId, params, saveDir, retFilesInfo) ;
      //输出结果
      if(rst == 0) { //正常返回
        ClibUtil clibUtil = new ClibUtil() ;
        clibUtil.outputRst( retFilesInfo ) ;
      } else { //异常返回
        System.out.println( "[error] FileInfoSearchAPI_CLIB_callAPI_to_fileList." ) ;       
        System.out.printf( "\treturn code: %d. \n", rst ) ;
        System.out.printf( "\terror message: %s.\n", retFilesInfo.request.errorMessage ) ;
      }
    } catch (Exception e) {
      //异常输出
      e.printStackTrace() ;
    } finally {
      //释放接口服务连接资源
      client.destroyResources() ;
    }
  }
  
}

