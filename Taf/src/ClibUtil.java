

import cma.cimiss.RetArray2D;
import cma.cimiss.RetFilesInfo;
import cma.cimiss.RetGridArray2D;

public class ClibUtil {  

  /*
   * 输出返回结果，RetArray2D
   */
  public void outputRst( RetArray2D retArray2D ) {
    /* 1. 请求信息 */
    System.out.println( "请求信息：request，所属类：RequestInfo" ) ;
    System.out.println( "\t" + "成员：" );
    System.out.println( "\t" + "returnCode（返回码）：" + retArray2D.request.errorCode ) ;
    System.out.println( "\t" + "returnMessage（提示信息）：" + retArray2D.request.errorMessage ) ;
    System.out.println( "\t" + "requestElems（检索请求的要素）：" + retArray2D.request.requestElems ) ;
    System.out.println( "\t" + "requestParams（检索请求的参数）：" + retArray2D.request.requestParams ) ;
    System.out.println( "\t" + "requestTime（请求发起时间）：" + retArray2D.request.requestTime ) ;
    System.out.println( "\t" + "responseTime（请求返回时间）：" + retArray2D.request.responseTime ) ;
    System.out.println( "\t" + "takeTime（请求耗时，单位ms）：" + retArray2D.request.takeTime ) ;
    System.out.println() ;
    
    /* 2. 返回的数据  */
    System.out.println( "结果集：retArray2D，所属类：RetArray2D" ) ;
    System.out.printf( "\t" + "成员：data，类：String[][]，值(记录数：%d，要素数：%d）：\n", retArray2D.data.length, retArray2D.data[0].length ) ;
    //遍历数据：retArray2D.data
    //行数：retArray2D.data.length
    System.out.println("\t---------------------------------------------------------------------") ;
    for( int iRow = 0; iRow < retArray2D.data.length; iRow ++ ) {
      System.out.print( "\t" ) ;
      //列数： retArray2D.data[iRow].length
      for( int iCol = 0; iCol < retArray2D.data[iRow].length; iCol ++ ) {
        System.out.print( retArray2D.data[iRow][iCol] + "\t" ) ;
      }
      System.out.println() ;
      //DEMO中，最多只输出10行
      if( iRow > 10 ) {
        System.out.println( "\t......" ) ;
        break ;
      }
    } 
    System.out.println("\t---------------------------------------------------------------------") ;
  }
  

/*
 * 输出返回结果，RetGridArray2D
 */
public void outputRst( RetGridArray2D retGridArray2D ) {
  /* 1. 请求信息 */
  System.out.println( "请求信息：request，所属类：RequestInfo" ) ;
  System.out.println( "\t" + "成员：" );
  System.out.println( "\t" + "returnCode（返回码）：" + retGridArray2D.request.errorCode ) ;
  System.out.println( "\t" + "returnMessage（提示信息）：" + retGridArray2D.request.errorMessage ) ;
  System.out.println( "\t" + "requestElems（检索请求的要素）：" + retGridArray2D.request.requestElems ) ;
  System.out.println( "\t" + "requestParams（检索请求的参数）：" + retGridArray2D.request.requestParams ) ;
  System.out.println( "\t" + "requestTime（请求发起时间）：" + retGridArray2D.request.requestTime ) ;
  System.out.println( "\t" + "responseTime（请求返回时间）：" + retGridArray2D.request.responseTime ) ;
  System.out.println( "\t" + "takeTime（请求耗时，单位ms）：" + retGridArray2D.request.takeTime ) ;
  System.out.println() ;
  
  /* 2. 返回的数据  */
  System.out.println( "结果集：retGridArray2D，所属类：RetGridArray2D" ) ;
  System.out.println( "\t" + "成员：startLat（网格起始纬度）：" + retGridArray2D.startLat ) ; 
  System.out.println( "\t" + "成员：endLat（网格终止纬度）：" + retGridArray2D.endLat ) ; 
  System.out.println( "\t" + "成员：startLon（网格起始经度）：" + retGridArray2D.startLon ) ; 
  System.out.println( "\t" + "成员：endLon（网格终止经度）：" + retGridArray2D.endLon ) ; 
  System.out.println( "\t" + "成员：latCount（纬向格点数）：" + retGridArray2D.latCount ) ;
  System.out.println( "\t" + "成员：lonCount（经向格点数）：" + retGridArray2D.lonCount ) ;
  System.out.println( "\t" + "成员：latStep（纬向格距）：" + retGridArray2D.latStep ) ;
  System.out.println( "\t" + "成员：lonStep（经向格距）：" + retGridArray2D.lonStep ) ;
  
  System.out.printf( "\t" + "成员：data，类：String[][]，值(行：%d，列：%d）：\n", retGridArray2D.latCount, retGridArray2D.lonCount ) ;
  //遍历数据：retArray2D.data
  //行数：retArray2D.data.length
  System.out.println("\t-------------------------------------------------------------------------------------------------------------") ;
  System.out.printf( "\t%7s", "纬度/经度|" ) ;
  float lon = retGridArray2D.startLon ;
  for( int iLon = 0; iLon < retGridArray2D.lonCount; iLon ++ ) {
    System.out.printf( "%10g|", lon ) ;
    lon += retGridArray2D.lonStep ;
  }
  System.out.println() ;

  System.out.printf( "\t----------" ) ;
  for( int iLon = 0; iLon < retGridArray2D.lonCount; iLon ++ ) {
    System.out.printf( "----------" ) ;
  }
  System.out.println() ;
  
  //遍历数据：retArray2D.data
  float lat = retGridArray2D.startLat ;
  //行数：retGridArray2D.data.length
  for( int iLat = 0; iLat < retGridArray2D.latCount; iLat ++ ) {
    System.out.printf( "\t%10g|", lat ) ;
    //列数：  retGridArray2D.lonCount
    for( int iLon = 0; iLon < retGridArray2D.lonCount; iLon ++ ) {
      System.out.printf( "%10g|", retGridArray2D.data[iLat][iLon] ) ;
    }
    System.out.println() ;
    lat += retGridArray2D.latStep ;

    //DEMO中，最多只输出10行
    if( iLat > 10 ) {
      System.out.println( "\t......" ) ;
      break ;
    }
  }

  System.out.printf( "\t----------" ) ;
  for( int iLon = 0; iLon < retGridArray2D.lonCount; iLon ++ ) {
    System.out.printf( "----------" ) ;
  } 
    
}

  /*
   * 输出返回结果，RetFilesInfo
   */
  public void outputRst( RetFilesInfo retFilesInfo ) {
    /* 1. 请求信息 */
    System.out.println( "请求信息：request，所属类：RequestInfo" ) ;
    System.out.println( "\t" + "成员：" );
    System.out.println( "\t" + "returnCode（返回码）：" + retFilesInfo.request.errorCode ) ;
    System.out.println( "\t" + "returnMessage（提示信息）：" + retFilesInfo.request.errorMessage ) ;
    System.out.println( "\t" + "requestElems（检索请求的要素）：" + retFilesInfo.request.requestElems ) ;
    System.out.println( "\t" + "requestParams（检索请求的参数）：" + retFilesInfo.request.requestParams ) ;
    System.out.println( "\t" + "requestTime（请求发起时间）：" + retFilesInfo.request.requestTime ) ;
    System.out.println( "\t" + "responseTime（请求返回时间）：" + retFilesInfo.request.responseTime ) ;
    System.out.println( "\t" + "takeTime（请求耗时，单位ms）：" + retFilesInfo.request.takeTime ) ;
    System.out.println() ;
    
    /* 2. 返回的数据  */
    System.out.println( "结果集：retFilesInfo，所属类：RetFilesInfo" ) ;
    System.out.printf( "\t" + "成员：fileInfos，类：FileInfo[]，值(记录数：%d）：\n", retFilesInfo.fileInfos.length ) ;
    //遍历数据：retFilesInfo.fileInfos
    //行数：retFilesInfo.fileInfos.length
    System.out.println("\t---------------------------------------------------------------------") ;
    for( int iRow = 0; iRow < retFilesInfo.fileInfos.length; iRow ++ ) {
      System.out.print( "\t" ) ;
      System.out.print( "\t" + "fileName（文件名）：" + retFilesInfo.fileInfos[iRow].fileName ) ;
      //在仅获取文件信息列表的接口中，savePath为空
      System.out.print( "\t" + "savePath（下载到客户端的全路径）：" + retFilesInfo.fileInfos[iRow].savePath ) ;
      System.out.print( "\t" + "suffix（文件后缀）：" + retFilesInfo.fileInfos[iRow].suffix ) ;
      System.out.printf( "\t" + "size（文件大小byte）：" + retFilesInfo.fileInfos[iRow].size ) ;
      System.out.print( "\t" + "fileUrl（服务端文件url）：" + retFilesInfo.fileInfos[iRow].fileUrl ) ;
      //对非图片文件，该成员为空；对图片文件，可为空（使用fileUrl）。
      System.out.print( "\t" + "imgBase64（图片文件base64编码）：" + retFilesInfo.fileInfos[iRow].imgBase64 ) ;
      System.out.println() ;
      //DEMO中，最多只输出10行
      if( iRow > 10 ) {
        System.out.println( "\t......" ) ;
        break ;
      }
    } 
    System.out.println("\t---------------------------------------------------------------------") ;
  }
  
}
