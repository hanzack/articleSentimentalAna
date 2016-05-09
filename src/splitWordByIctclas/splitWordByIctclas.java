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
		String abcString="他其实是一个整体不错的好人";
		System.out.println(getSplitWordsByICTCLASWithUserDict(abcString,1));
	}
	/**
	 * 使用用户词典，不写入文件
	 */
	@Test
	public void test_getSplitWords1(){
	     String abc="他非常非常不好，他非常好的。他不是非常非常好的. 我不认为他有多好。";
	 String abcString=    getSplitWordsByICTCLASWithUserDict(abc,1); //1表示是否支持词性标注。
	 System.out.println(abcString);
	}
	
	
	
	/**
	 * 返回分词结果，并给出每个分词是第几个。   例如一句话     他是一个好人。 分词后： 0_@他/r 1_@是v 2_@一个/m 3_@好人/n
	 * 传入一个字符串，返回一个分词结果。 本方法采用Ictclas分词。 本方法来自ICTCLAS中的testMain方法。  采用用户字典。
	 * @param str
	 * @return   分词结果
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
	        //初始化
	        if (testICTCLAS50.ICTCLAS_Init(argu.getBytes("GB2312")) == false)
	        {
	            System.out.println("Init Fail!");
	            return "";
	        }
	        //设置词性标注集(0 计算所二级标注集，1 计算所一级标注集，2 北大二级标注集，3 北大一级标注集)
	        testICTCLAS50.ICTCLAS_SetPOSmap(1);
//	        //导入用户词典前分词
//	        byte nativeBytes[] = testICTCLAS50.ICTCLAS_ParagraphProcess(str.getBytes("GB2312"), 0, postTag0or1);//分词处理 1 代表词性标注。
//	         result = new String(nativeBytes, 0, nativeBytes.length, "GB2312");
	        
	    	//导入用户字典   
			int nCount = 0;
			String usrdir = "userdict.txt"; //用户字典路径
			byte[] usrdirb = usrdir.getBytes();//将string转化为byte类型
			//导入用户字典,返回导入用户词语个数第一个参数为用户字典路径，第二个参数为用户字典的编码类型
			nCount = testICTCLAS50.ICTCLAS_ImportUserDictFile(usrdirb, 0);
//			System.out.println("导入用户词个数" + nCount);
			nCount = 0;

			//导入用户字典后再分词
			byte nativeBytes1[] = testICTCLAS50.ICTCLAS_ParagraphProcess(str.getBytes("GB2312"), 2, postTag0or1);//是否进行词性标注
//			System.out.println(nativeBytes1.length);
			 result = new String(nativeBytes1, 0, nativeBytes1.length, "GB2312");
//			System.out.println("导入用户词典后的分词结果： " + result);
//			 System.out.println("分词完成");
			//保存用户字典
			testICTCLAS50.ICTCLAS_SaveTheUsrDic();
			//释放分词组件资源
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
	 * 传入一个字符串，返回一个分词结果。 本方法采用Ictclas分词。 本方法来自ICTCLAS中的testMain方法。  采用用户字典。
	 * @param str
	 * @return   分词结果
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
	        //初始化
	        if (testICTCLAS50.ICTCLAS_Init(argu.getBytes("GB2312")) == false)
	        {
	            System.out.println("Init Fail!");
	            return "";
	        }
	        //设置词性标注集(0 计算所二级标注集，1 计算所一级标注集，2 北大二级标注集，3 北大一级标注集)
	        testICTCLAS50.ICTCLAS_SetPOSmap(1);
//	        //导入用户词典前分词
//	        byte nativeBytes[] = testICTCLAS50.ICTCLAS_ParagraphProcess(str.getBytes("GB2312"), 0, postTag0or1);//分词处理 1 代表词性标注。
//	         result = new String(nativeBytes, 0, nativeBytes.length, "GB2312");
	        
	    	//导入用户字典   
			int nCount = 0;
			String usrdir = "userdict.txt"; //用户字典路径
			byte[] usrdirb = usrdir.getBytes();//将string转化为byte类型
			//导入用户字典,返回导入用户词语个数第一个参数为用户字典路径，第二个参数为用户字典的编码类型
			nCount = testICTCLAS50.ICTCLAS_ImportUserDictFile(usrdirb, 0);
//			System.out.println("导入用户词个数" + nCount);
			nCount = 0;

			//导入用户字典后再分词
			byte nativeBytes1[] = testICTCLAS50.ICTCLAS_ParagraphProcess(str.getBytes("GB2312"), 2, postTag0or1);//是否进行词性标注
//			System.out.println(nativeBytes1.length);
			 result = new String(nativeBytes1, 0, nativeBytes1.length, "GB2312");
//			System.out.println("导入用户词典后的分词结果： " + result);
//			 System.out.println("分词完成");
			//保存用户字典
			testICTCLAS50.ICTCLAS_SaveTheUsrDic();
			//释放分词组件资源
			testICTCLAS50.ICTCLAS_Exit();
	    }
	    catch (Exception ex)
	    {  ex.printStackTrace();   }
//	    ReadAndWriteFileFromDirectory.writeStringToFile(result, toPath, filename, "txt");
	    return result;
	}

	
	
	
	
	
	
	
	

	/**
	 * 无用户词典，分词  后写入文件
	 */
	@Test
	public void test_getSplitWords(){
	     String abc="他其实是一个不错的好人呢。";
	     String toPath="W://";
	     String filename="123";
	     getSplitWordsByICTCLASWrtFilePostTag(abc, toPath, filename,1); //1表示是否支持词性标注。
	}
	
	
	

	/**
	 * 传入一个字符串，返回一个分词结果。 本方法采用Ictclas分词。 本方法来自ICTCLAS中的testMain方法。  不采用用户字典。
	 * @param str
	 * 
	 * @return   分词结果
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
	        //初始化
	        if (testICTCLAS50.ICTCLAS_Init(argu.getBytes("GB2312")) == false)
	        {
	            System.out.println("Init Fail!");
	            return "";
	        }
	        //设置词性标注集(0 计算所二级标注集，1 计算所一级标注集，2 北大二级标注集，3 北大一级标注集)
	        testICTCLAS50.ICTCLAS_SetPOSmap(2);
	        //导入用户词典前分词
	        byte nativeBytes[] = testICTCLAS50.ICTCLAS_ParagraphProcess(str.getBytes("GB2312"), 0, postTag0or1);//分词处理 1 代表词性标注。
	         result = new String(nativeBytes, 0, nativeBytes.length, "GB2312");
	    }
	    catch (Exception ex)
	    {  ex.printStackTrace();   }
	    ReadAndWriteFileFromDirectory.writeStringToFile(result, toPath, filename, "txt");
	    return result;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 //主函数
		public static void main(String[] args)
		{
			try
			{
				//字符串分词          
				String sInput = "随后温总理就离开了舟曲县城，预计温总理今天下午就回到北京。以上就是今天上午的最新动态";
			new splitWordByIctclas().testICTCLAS_ParagraphProcess(sInput);//同testimportuserdict和testSetPOSmap
				//文本文件分词
//				testICTCLAS_FileProcess();
				

			}
			catch (Exception ex)
			{
			}
		}
	
	
	/**
	 * @param sInput  原始方法。
	 */
	public void testICTCLAS_ParagraphProcess(String sInput)
	{
		try
		{
			ICTCLAS50 testICTCLAS50 = new ICTCLAS50();
			String argu = ".";
			//初始化
			if (testICTCLAS50.ICTCLAS_Init(argu.getBytes("GB2312")) == false)
			{
				System.out.println("Init Fail!");
				return;
			}


			//设置词性标注集(0 计算所二级标注集，1 计算所一级标注集，2 北大二级标注集，3 北大一级标注集)
			testICTCLAS50.ICTCLAS_SetPOSmap(2);


			//导入用户词典前分词
			byte nativeBytes[] = testICTCLAS50.ICTCLAS_ParagraphProcess(sInput.getBytes("GB2312"), 0, 0);//分词处理
			System.out.println(nativeBytes.length);
			String nativeStr = new String(nativeBytes, 0, nativeBytes.length, "GB2312");
			System.out.println("未导入用户词典的分词结果： " + nativeStr);//打印结果


			//导入用户字典
			int nCount = 0;
			String usrdir = "userdict.txt"; //用户字典路径
			byte[] usrdirb = usrdir.getBytes();//将string转化为byte类型
			//导入用户字典,返回导入用户词语个数第一个参数为用户字典路径，第二个参数为用户字典的编码类型
			nCount = testICTCLAS50.ICTCLAS_ImportUserDictFile(usrdirb, 0);
			System.out.println("导入用户词个数" + nCount);
			nCount = 0;


			//导入用户字典后再分词
			byte nativeBytes1[] = testICTCLAS50.ICTCLAS_ParagraphProcess(sInput.getBytes("GB2312"), 2, 0);
			System.out.println(nativeBytes1.length);
			String nativeStr1 = new String(nativeBytes1, 0, nativeBytes1.length, "GB2312");
			System.out.println("导入用户词典后的分词结果： " + nativeStr1);
			//保存用户字典
			testICTCLAS50.ICTCLAS_SaveTheUsrDic();
			//释放分词组件资源
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
			//分词所需库的路径
			String argu = ".";
			//初始化
			if (testICTCLAS50.ICTCLAS_Init(argu.getBytes("GB2312")) == false)
			{
				System.out.println("Init Fail!");
				return;
			}

			//输入文件名
			String Inputfilename = "test.txt";
			byte[] Inputfilenameb = Inputfilename.getBytes();//将文件名string类型转为byte类型

			//分词处理后输出文件名
			String Outputfilename = "test_result.txt";
			byte[] Outputfilenameb = Outputfilename.getBytes();//将文件名string类型转为byte类型

			//文件分词(第一个参数为输入文件的名,第二个参数为文件编码类型,第三个参数为是否标记词性集1 yes,0 no,第四个参数为输出文件名)
			testICTCLAS50.ICTCLAS_FileProcess(Inputfilenameb, 0, 0, Outputfilenameb);

			int nCount = 0;
			String usrdir = "userdict.txt"; //用户字典路径
			byte[] usrdirb = usrdir.getBytes();//将string转化为byte类型
			//第一个参数为用户字典路径，第二个参数为用户字典的编码类型(0:type unknown;1:ASCII码;2:GB2312,GBK,GB10380;3:UTF-8;4:BIG5)
			nCount = testICTCLAS50.ICTCLAS_ImportUserDictFile(usrdirb, 0);//导入用户字典,返回导入用户词语个数
			System.out.println("导入用户词个数" + nCount);
			nCount = 0;

			String Outputfilename1 = "testing_result.txt";
			byte[] Outputfilenameb1 = Outputfilename1.getBytes();//将文件名string类型转为byte类型

			//文件分词(第一个参数为输入文件的名,第二个参数为文件编码类型,第三个参数为是否标记词性集1 yes,0 no,第四个参数为输出文件名)
			testICTCLAS50.ICTCLAS_FileProcess(Inputfilenameb, 0, 0, Outputfilenameb1);





		}
		catch (Exception ex)
		{
		}

	}

}


	
	

	