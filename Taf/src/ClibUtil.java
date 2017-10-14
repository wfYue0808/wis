

import cma.cimiss.RetArray2D;
import cma.cimiss.RetFilesInfo;
import cma.cimiss.RetGridArray2D;

public class ClibUtil {  

  /*
   * ������ؽ����RetArray2D
   */
  public void outputRst( RetArray2D retArray2D ) {
    /* 1. ������Ϣ */
    System.out.println( "������Ϣ��request�������ࣺRequestInfo" ) ;
    System.out.println( "\t" + "��Ա��" );
    System.out.println( "\t" + "returnCode�������룩��" + retArray2D.request.errorCode ) ;
    System.out.println( "\t" + "returnMessage����ʾ��Ϣ����" + retArray2D.request.errorMessage ) ;
    System.out.println( "\t" + "requestElems�����������Ҫ�أ���" + retArray2D.request.requestElems ) ;
    System.out.println( "\t" + "requestParams����������Ĳ�������" + retArray2D.request.requestParams ) ;
    System.out.println( "\t" + "requestTime��������ʱ�䣩��" + retArray2D.request.requestTime ) ;
    System.out.println( "\t" + "responseTime�����󷵻�ʱ�䣩��" + retArray2D.request.responseTime ) ;
    System.out.println( "\t" + "takeTime�������ʱ����λms����" + retArray2D.request.takeTime ) ;
    System.out.println() ;
    
    /* 2. ���ص�����  */
    System.out.println( "�������retArray2D�������ࣺRetArray2D" ) ;
    System.out.printf( "\t" + "��Ա��data���ࣺString[][]��ֵ(��¼����%d��Ҫ������%d����\n", retArray2D.data.length, retArray2D.data[0].length ) ;
    //�������ݣ�retArray2D.data
    //������retArray2D.data.length
    System.out.println("\t---------------------------------------------------------------------") ;
    for( int iRow = 0; iRow < retArray2D.data.length; iRow ++ ) {
      System.out.print( "\t" ) ;
      //������ retArray2D.data[iRow].length
      for( int iCol = 0; iCol < retArray2D.data[iRow].length; iCol ++ ) {
        System.out.print( retArray2D.data[iRow][iCol] + "\t" ) ;
      }
      System.out.println() ;
      //DEMO�У����ֻ���10��
      if( iRow > 10 ) {
        System.out.println( "\t......" ) ;
        break ;
      }
    } 
    System.out.println("\t---------------------------------------------------------------------") ;
  }
  

/*
 * ������ؽ����RetGridArray2D
 */
public void outputRst( RetGridArray2D retGridArray2D ) {
  /* 1. ������Ϣ */
  System.out.println( "������Ϣ��request�������ࣺRequestInfo" ) ;
  System.out.println( "\t" + "��Ա��" );
  System.out.println( "\t" + "returnCode�������룩��" + retGridArray2D.request.errorCode ) ;
  System.out.println( "\t" + "returnMessage����ʾ��Ϣ����" + retGridArray2D.request.errorMessage ) ;
  System.out.println( "\t" + "requestElems�����������Ҫ�أ���" + retGridArray2D.request.requestElems ) ;
  System.out.println( "\t" + "requestParams����������Ĳ�������" + retGridArray2D.request.requestParams ) ;
  System.out.println( "\t" + "requestTime��������ʱ�䣩��" + retGridArray2D.request.requestTime ) ;
  System.out.println( "\t" + "responseTime�����󷵻�ʱ�䣩��" + retGridArray2D.request.responseTime ) ;
  System.out.println( "\t" + "takeTime�������ʱ����λms����" + retGridArray2D.request.takeTime ) ;
  System.out.println() ;
  
  /* 2. ���ص�����  */
  System.out.println( "�������retGridArray2D�������ࣺRetGridArray2D" ) ;
  System.out.println( "\t" + "��Ա��startLat��������ʼγ�ȣ���" + retGridArray2D.startLat ) ; 
  System.out.println( "\t" + "��Ա��endLat��������ֹγ�ȣ���" + retGridArray2D.endLat ) ; 
  System.out.println( "\t" + "��Ա��startLon��������ʼ���ȣ���" + retGridArray2D.startLon ) ; 
  System.out.println( "\t" + "��Ա��endLon��������ֹ���ȣ���" + retGridArray2D.endLon ) ; 
  System.out.println( "\t" + "��Ա��latCount��γ����������" + retGridArray2D.latCount ) ;
  System.out.println( "\t" + "��Ա��lonCount��������������" + retGridArray2D.lonCount ) ;
  System.out.println( "\t" + "��Ա��latStep��γ���ࣩ��" + retGridArray2D.latStep ) ;
  System.out.println( "\t" + "��Ա��lonStep�������ࣩ��" + retGridArray2D.lonStep ) ;
  
  System.out.printf( "\t" + "��Ա��data���ࣺString[][]��ֵ(�У�%d���У�%d����\n", retGridArray2D.latCount, retGridArray2D.lonCount ) ;
  //�������ݣ�retArray2D.data
  //������retArray2D.data.length
  System.out.println("\t-------------------------------------------------------------------------------------------------------------") ;
  System.out.printf( "\t%7s", "γ��/����|" ) ;
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
  
  //�������ݣ�retArray2D.data
  float lat = retGridArray2D.startLat ;
  //������retGridArray2D.data.length
  for( int iLat = 0; iLat < retGridArray2D.latCount; iLat ++ ) {
    System.out.printf( "\t%10g|", lat ) ;
    //������  retGridArray2D.lonCount
    for( int iLon = 0; iLon < retGridArray2D.lonCount; iLon ++ ) {
      System.out.printf( "%10g|", retGridArray2D.data[iLat][iLon] ) ;
    }
    System.out.println() ;
    lat += retGridArray2D.latStep ;

    //DEMO�У����ֻ���10��
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
   * ������ؽ����RetFilesInfo
   */
  public void outputRst( RetFilesInfo retFilesInfo ) {
    /* 1. ������Ϣ */
    System.out.println( "������Ϣ��request�������ࣺRequestInfo" ) ;
    System.out.println( "\t" + "��Ա��" );
    System.out.println( "\t" + "returnCode�������룩��" + retFilesInfo.request.errorCode ) ;
    System.out.println( "\t" + "returnMessage����ʾ��Ϣ����" + retFilesInfo.request.errorMessage ) ;
    System.out.println( "\t" + "requestElems�����������Ҫ�أ���" + retFilesInfo.request.requestElems ) ;
    System.out.println( "\t" + "requestParams����������Ĳ�������" + retFilesInfo.request.requestParams ) ;
    System.out.println( "\t" + "requestTime��������ʱ�䣩��" + retFilesInfo.request.requestTime ) ;
    System.out.println( "\t" + "responseTime�����󷵻�ʱ�䣩��" + retFilesInfo.request.responseTime ) ;
    System.out.println( "\t" + "takeTime�������ʱ����λms����" + retFilesInfo.request.takeTime ) ;
    System.out.println() ;
    
    /* 2. ���ص�����  */
    System.out.println( "�������retFilesInfo�������ࣺRetFilesInfo" ) ;
    System.out.printf( "\t" + "��Ա��fileInfos���ࣺFileInfo[]��ֵ(��¼����%d����\n", retFilesInfo.fileInfos.length ) ;
    //�������ݣ�retFilesInfo.fileInfos
    //������retFilesInfo.fileInfos.length
    System.out.println("\t---------------------------------------------------------------------") ;
    for( int iRow = 0; iRow < retFilesInfo.fileInfos.length; iRow ++ ) {
      System.out.print( "\t" ) ;
      System.out.print( "\t" + "fileName���ļ�������" + retFilesInfo.fileInfos[iRow].fileName ) ;
      //�ڽ���ȡ�ļ���Ϣ�б�Ľӿ��У�savePathΪ��
      System.out.print( "\t" + "savePath�����ص��ͻ��˵�ȫ·������" + retFilesInfo.fileInfos[iRow].savePath ) ;
      System.out.print( "\t" + "suffix���ļ���׺����" + retFilesInfo.fileInfos[iRow].suffix ) ;
      System.out.printf( "\t" + "size���ļ���Сbyte����" + retFilesInfo.fileInfos[iRow].size ) ;
      System.out.print( "\t" + "fileUrl��������ļ�url����" + retFilesInfo.fileInfos[iRow].fileUrl ) ;
      //�Է�ͼƬ�ļ����ó�ԱΪ�գ���ͼƬ�ļ�����Ϊ�գ�ʹ��fileUrl����
      System.out.print( "\t" + "imgBase64��ͼƬ�ļ�base64���룩��" + retFilesInfo.fileInfos[iRow].imgBase64 ) ;
      System.out.println() ;
      //DEMO�У����ֻ���10��
      if( iRow > 10 ) {
        System.out.println( "\t......" ) ;
        break ;
      }
    } 
    System.out.println("\t---------------------------------------------------------------------") ;
  }
  
}
