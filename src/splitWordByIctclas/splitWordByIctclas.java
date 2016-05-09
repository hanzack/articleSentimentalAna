package splitWordByIctclas;

import ICTCLAS.I3S.AC.ICTCLAS50;
import Primary_data_type_zack_util.String_handle_Zack_Util;

import java.util.*;
import java.io.*;

import org.junit.Test;

import fileHandle_From_Directory.ReadAndWriteFileFromDirectory;


public class splitWordByIctclas
{  
	
	@Test
	public void test_segAndPostagSentence(){
		String abcString="����ʵ��һ�����岻��ĺ���";
		System.out.println(getSplitWordsByICTCLASWithUserDict(abcString,1));
	}
	/**
	 * ʹ���û��ʵ䣬��д���ļ�
	 */
	@Test
	public void test_getSplitWords1(){
	     String abc="���ǳ��ǳ����ã����ǳ��õġ������Ƿǳ��ǳ��õ�. �Ҳ���Ϊ���ж�á�";
	 String abcString=    getSplitWordsByICTCLASWithUserDict(abc,1); //1��ʾ�Ƿ�֧�ִ��Ա�ע��
	 System.out.println(abcString);
	}
	
	
	
	/**
	 * ���طִʽ����������ÿ���ִ��ǵڼ�����   ����һ�仰     ����һ�����ˡ� �ִʺ� 0_@��/r 1_@��v 2_@һ��/m 3_@����/n
	 * ����һ���ַ���������һ���ִʽ���� ����������Ictclas�ִʡ� ����������ICTCLAS�е�testMain������  �����û��ֵ䡣
	 * @param str
	 * @return   �ִʽ��
	 * @param str
	 * @param toPath
	 * @param filename
	 * @return
	 */
	public static String getSplitWordsByICTCLASWithUserDictWithNum(String str,int postTag0or1){
	    String result="";
	    try
	    {
	        ICTCLAS50 testICTCLAS50 = new ICTCLAS50();
	        String argu = ".";
	        //��ʼ��
	        if (testICTCLAS50.ICTCLAS_Init(argu.getBytes("GB2312")) == false)
	        {
	            System.out.println("Init Fail!");
	            return "";
	        }
	        //���ô��Ա�ע��(0 ������������ע����1 ������һ����ע����2 ���������ע����3 ����һ����ע��)
	        testICTCLAS50.ICTCLAS_SetPOSmap(1);
//	        //�����û��ʵ�ǰ�ִ�
//	        byte nativeBytes[] = testICTCLAS50.ICTCLAS_ParagraphProcess(str.getBytes("GB2312"), 0, postTag0or1);//�ִʴ��� 1 ������Ա�ע��
//	         result = new String(nativeBytes, 0, nativeBytes.length, "GB2312");
	        
	    	//�����û��ֵ�   
			int nCount = 0;
			String usrdir = "userdict.txt"; //�û��ֵ�·��
			byte[] usrdirb = usrdir.getBytes();//��stringת��Ϊbyte����
			//�����û��ֵ�,���ص����û����������һ������Ϊ�û��ֵ�·�����ڶ�������Ϊ�û��ֵ�ı�������
			nCount = testICTCLAS50.ICTCLAS_ImportUserDictFile(usrdirb, 0);
//			System.out.println("�����û��ʸ���" + nCount);
			nCount = 0;

			//�����û��ֵ���ٷִ�
			byte nativeBytes1[] = testICTCLAS50.ICTCLAS_ParagraphProcess(str.getBytes("GB2312"), 2, postTag0or1);//�Ƿ���д��Ա�ע
//			System.out.println(nativeBytes1.length);
			 result = new String(nativeBytes1, 0, nativeBytes1.length, "GB2312");
//			System.out.println("�����û��ʵ��ķִʽ���� " + result);
//			 System.out.println("�ִ����");
			//�����û��ֵ�
			testICTCLAS50.ICTCLAS_SaveTheUsrDic();
			//�ͷŷִ������Դ
			testICTCLAS50.ICTCLAS_Exit();
	    }
	    catch (Exception ex)
	    {  ex.printStackTrace();   }
//	    ReadAndWriteFileFromDirectory.writeStringToFile(result, toPath, filename, "txt");
	    String[] tempresult=result.split(" ");
	    String afterResult="";
	    for (int i = 0; i < tempresult.length; i++) {
	    	tempresult[i]=i+"_@"+tempresult[i];
	    	afterResult=afterResult+tempresult[i]+" ";
		}
	    return afterResult;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * ����һ���ַ���������һ���ִʽ���� ����������Ictclas�ִʡ� ����������ICTCLAS�е�testMain������  �����û��ֵ䡣
	 * @param str
	 * @return   �ִʽ��
	 * @param str
	 * @param toPath
	 * @param filename
	 * @return
	 */
	public static String getSplitWordsByICTCLASWithUserDict(String str,int postTag0or1){
	    String result="";
	    try
	    {
	        ICTCLAS50 testICTCLAS50 = new ICTCLAS50();
	        String argu = ".";
	        //��ʼ��
	        if (testICTCLAS50.ICTCLAS_Init(argu.getBytes("GB2312")) == false)
	        {
	            System.out.println("Init Fail!");
	            return "";
	        }
	        //���ô��Ա�ע��(0 ������������ע����1 ������һ����ע����2 ���������ע����3 ����һ����ע��)
	        testICTCLAS50.ICTCLAS_SetPOSmap(1);
//	        //�����û��ʵ�ǰ�ִ�
//	        byte nativeBytes[] = testICTCLAS50.ICTCLAS_ParagraphProcess(str.getBytes("GB2312"), 0, postTag0or1);//�ִʴ��� 1 ������Ա�ע��
//	         result = new String(nativeBytes, 0, nativeBytes.length, "GB2312");
	        
	    	//�����û��ֵ�   
			int nCount = 0;
			String usrdir = "userdict.txt"; //�û��ֵ�·��
			byte[] usrdirb = usrdir.getBytes();//��stringת��Ϊbyte����
			//�����û��ֵ�,���ص����û����������һ������Ϊ�û��ֵ�·�����ڶ�������Ϊ�û��ֵ�ı�������
			nCount = testICTCLAS50.ICTCLAS_ImportUserDictFile(usrdirb, 0);
//			System.out.println("�����û��ʸ���" + nCount);
			nCount = 0;

			//�����û��ֵ���ٷִ�
			byte nativeBytes1[] = testICTCLAS50.ICTCLAS_ParagraphProcess(str.getBytes("GB2312"), 2, postTag0or1);//�Ƿ���д��Ա�ע
//			System.out.println(nativeBytes1.length);
			 result = new String(nativeBytes1, 0, nativeBytes1.length, "GB2312");
//			System.out.println("�����û��ʵ��ķִʽ���� " + result);
//			 System.out.println("�ִ����");
			//�����û��ֵ�
			testICTCLAS50.ICTCLAS_SaveTheUsrDic();
			//�ͷŷִ������Դ
			testICTCLAS50.ICTCLAS_Exit();
	    }
	    catch (Exception ex)
	    {  ex.printStackTrace();   }
//	    ReadAndWriteFileFromDirectory.writeStringToFile(result, toPath, filename, "txt");
	    return result;
	}

	
	
	
	
	
	
	
	

	/**
	 * ���û��ʵ䣬�ִ�  ��д���ļ�
	 */
	@Test
	public void test_getSplitWords(){
	     String abc="����ʵ��һ������ĺ����ء�";
	     String toPath="W://";
	     String filename="123";
	     getSplitWordsByICTCLASWrtFilePostTag(abc, toPath, filename,1); //1��ʾ�Ƿ�֧�ִ��Ա�ע��
	}
	
	
	

	/**
	 * ����һ���ַ���������һ���ִʽ���� ����������Ictclas�ִʡ� ����������ICTCLAS�е�testMain������  �������û��ֵ䡣
	 * @param str
	 * 
	 * @return   �ִʽ��
	 * @param str
	 * @param toPath
	 * @param filename
	 * @return
	 */
	public static String getSplitWordsByICTCLASWrtFilePostTag(String str,String toPath,String filename,int postTag0or1){
	    if (String_handle_Zack_Util.isEmpty(toPath)) {
	        toPath="W:\\zackjob\\hacker\\nlp_workspace_files\\";
	    }
	    String result="";
	    try
	    {
	        ICTCLAS50 testICTCLAS50 = new ICTCLAS50();
	        String argu = ".";
	        //��ʼ��
	        if (testICTCLAS50.ICTCLAS_Init(argu.getBytes("GB2312")) == false)
	        {
	            System.out.println("Init Fail!");
	            return "";
	        }
	        //���ô��Ա�ע��(0 ������������ע����1 ������һ����ע����2 ���������ע����3 ����һ����ע��)
	        testICTCLAS50.ICTCLAS_SetPOSmap(2);
	        //�����û��ʵ�ǰ�ִ�
	        byte nativeBytes[] = testICTCLAS50.ICTCLAS_ParagraphProcess(str.getBytes("GB2312"), 0, postTag0or1);//�ִʴ��� 1 ������Ա�ע��
	         result = new String(nativeBytes, 0, nativeBytes.length, "GB2312");
	    }
	    catch (Exception ex)
	    {  ex.printStackTrace();   }
	    ReadAndWriteFileFromDirectory.writeStringToFile(result, toPath, filename, "txt");
	    return result;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 //������
		public static void main(String[] args)
		{
			try
			{
				//�ַ����ִ�          
				String sInput = "�����������뿪�������سǣ�Ԥ���������������ͻص����������Ͼ��ǽ�����������¶�̬";
			new splitWordByIctclas().testICTCLAS_ParagraphProcess(sInput);//ͬtestimportuserdict��testSetPOSmap
				//�ı��ļ��ִ�
//				testICTCLAS_FileProcess();
				

			}
			catch (Exception ex)
			{
			}
		}
	
	
	/**
	 * @param sInput  ԭʼ������
	 */
	public void testICTCLAS_ParagraphProcess(String sInput)
	{
		try
		{
			ICTCLAS50 testICTCLAS50 = new ICTCLAS50();
			String argu = ".";
			//��ʼ��
			if (testICTCLAS50.ICTCLAS_Init(argu.getBytes("GB2312")) == false)
			{
				System.out.println("Init Fail!");
				return;
			}


			//���ô��Ա�ע��(0 ������������ע����1 ������һ����ע����2 ���������ע����3 ����һ����ע��)
			testICTCLAS50.ICTCLAS_SetPOSmap(2);


			//�����û��ʵ�ǰ�ִ�
			byte nativeBytes[] = testICTCLAS50.ICTCLAS_ParagraphProcess(sInput.getBytes("GB2312"), 0, 0);//�ִʴ���
			System.out.println(nativeBytes.length);
			String nativeStr = new String(nativeBytes, 0, nativeBytes.length, "GB2312");
			System.out.println("δ�����û��ʵ�ķִʽ���� " + nativeStr);//��ӡ���


			//�����û��ֵ�
			int nCount = 0;
			String usrdir = "userdict.txt"; //�û��ֵ�·��
			byte[] usrdirb = usrdir.getBytes();//��stringת��Ϊbyte����
			//�����û��ֵ�,���ص����û����������һ������Ϊ�û��ֵ�·�����ڶ�������Ϊ�û��ֵ�ı�������
			nCount = testICTCLAS50.ICTCLAS_ImportUserDictFile(usrdirb, 0);
			System.out.println("�����û��ʸ���" + nCount);
			nCount = 0;


			//�����û��ֵ���ٷִ�
			byte nativeBytes1[] = testICTCLAS50.ICTCLAS_ParagraphProcess(sInput.getBytes("GB2312"), 2, 0);
			System.out.println(nativeBytes1.length);
			String nativeStr1 = new String(nativeBytes1, 0, nativeBytes1.length, "GB2312");
			System.out.println("�����û��ʵ��ķִʽ���� " + nativeStr1);
			//�����û��ֵ�
			testICTCLAS50.ICTCLAS_SaveTheUsrDic();
			//�ͷŷִ������Դ
			testICTCLAS50.ICTCLAS_Exit();
		}
		catch (Exception ex)
		{
		}

	}



	public void testICTCLAS_FileProcess()
	{
		try
		{
			ICTCLAS50 testICTCLAS50 = new ICTCLAS50();
			//�ִ�������·��
			String argu = ".";
			//��ʼ��
			if (testICTCLAS50.ICTCLAS_Init(argu.getBytes("GB2312")) == false)
			{
				System.out.println("Init Fail!");
				return;
			}

			//�����ļ���
			String Inputfilename = "test.txt";
			byte[] Inputfilenameb = Inputfilename.getBytes();//���ļ���string����תΪbyte����

			//�ִʴ��������ļ���
			String Outputfilename = "test_result.txt";
			byte[] Outputfilenameb = Outputfilename.getBytes();//���ļ���string����תΪbyte����

			//�ļ��ִ�(��һ������Ϊ�����ļ�����,�ڶ�������Ϊ�ļ���������,����������Ϊ�Ƿ��Ǵ��Լ�1 yes,0 no,���ĸ�����Ϊ����ļ���)
			testICTCLAS50.ICTCLAS_FileProcess(Inputfilenameb, 0, 0, Outputfilenameb);

			int nCount = 0;
			String usrdir = "userdict.txt"; //�û��ֵ�·��
			byte[] usrdirb = usrdir.getBytes();//��stringת��Ϊbyte����
			//��һ������Ϊ�û��ֵ�·�����ڶ�������Ϊ�û��ֵ�ı�������(0:type unknown;1:ASCII��;2:GB2312,GBK,GB10380;3:UTF-8;4:BIG5)
			nCount = testICTCLAS50.ICTCLAS_ImportUserDictFile(usrdirb, 0);//�����û��ֵ�,���ص����û��������
			System.out.println("�����û��ʸ���" + nCount);
			nCount = 0;

			String Outputfilename1 = "testing_result.txt";
			byte[] Outputfilenameb1 = Outputfilename1.getBytes();//���ļ���string����תΪbyte����

			//�ļ��ִ�(��һ������Ϊ�����ļ�����,�ڶ�������Ϊ�ļ���������,����������Ϊ�Ƿ��Ǵ��Լ�1 yes,0 no,���ĸ�����Ϊ����ļ���)
			testICTCLAS50.ICTCLAS_FileProcess(Inputfilenameb, 0, 0, Outputfilenameb1);





		}
		catch (Exception ex)
		{
		}

	}

}


	
	

	