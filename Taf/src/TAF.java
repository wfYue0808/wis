

import java.util.HashMap;
import cma.cimiss.RetFilesInfo;
import cma.cimiss.client.DataQueryClient;

/*
 * �ͻ��˵�ȡ�������ļ���������RetFilesInfo����
 */
public class TAF {
  /*
   * main����
   * �磺��ʱ��Ρ�վ�ż����״��ļ� getRadaFileByTimeRangeAndStaId
   */
  public  void getTAF(String dataCode,String timeFrom,String timeTo) {
    
    /* 1. ����client���� */
    DataQueryClient client = new DataQueryClient() ;
    
    /* 2. ���÷����Ĳ������壬����ֵ */
    /* 2.1 �û���&���� */
    String userId = "NMC_YBS_yangmeiling" ;
    String pwd = "123456" ;
    /* 2.2  �ӿ�ID */
    String interfaceId = "getSevpFileByTimeRange" ;
    /* 2.3  �ӿڲ����������������˳�� */
    HashMap<String, String> params = new HashMap<String, String>();
    //��ѡ����
   params.put("dataCode", dataCode); //���ϣ��ʿ�ǰ��׼��ʽ��վ�������״������
   params.put("timeRange", "["+timeFrom+","+timeTo+")"); //ʱ��Σ�ǰ�պ�  TODO:�쳣
  //  params.put("times", "20170510000000"); //ʱ��Σ�ǰ�պ�
    //params.put("staIds", "Z9859,Z9852,Z9856,Z9851,Z9855"); //�״�վ
    //��ѡ����
    /* 2.4 �ļ��ı��ر���Ŀ¼ */
    String saveDir = "e:/tmp/aaa" ;
    /* 2.5 �����ļ����������� */
    RetFilesInfo retFilesInfo = new RetFilesInfo() ;
    
    /* 3. ���ýӿ� */
    try {
      //��ʼ���ӿڷ���������Դ
      client.initResources() ;
      //���ýӿ�
      int rst = client.callAPI_to_downFile(userId, pwd, interfaceId, params, saveDir, retFilesInfo) ;
      //������
      if(rst == 0) { //��������
        ClibUtil clibUtil = new ClibUtil() ;
        clibUtil.outputRst( retFilesInfo ) ;
      } else { //�쳣����
        System.out.println( "[error] FileInfoSearchAPI_CLIB_callAPI_to_fileList." ) ;       
        System.out.printf( "\treturn code: %d. \n", rst ) ;
        System.out.printf( "\terror message: %s.\n", retFilesInfo.request.errorMessage ) ;
      }
    } catch (Exception e) {
      //�쳣���
      e.printStackTrace() ;
    } finally {
      //�ͷŽӿڷ���������Դ
      client.destroyResources() ;
    }
  }
  
}

