package 给词典文件赋分;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

import fileHandle_From_Directory.ReadAndWriteFileFromDirectory;
import Primary_data_type_zack_util.String_handle_Zack_Util;
import Primary_data_type_zack_util.createID_Unique;

public class WriteDictScore {

	/**
	 * 给一个字典文件 的每一个单词 后面 跟上分数。
	 */
	@Test
	public void test_dictScoreChange(){
		String fromFile="W:\\myproj\\sentimentalProj\\dict_All\\abc.txt";
		int score=1;
		String splitmark="\t";
		String toFile="W:\\myproj\\sentimentalProj\\dict_All\\123.txt";
//		String toFile="W:\\myproj\\sentimentalProj\\dict_All\\";
//		ReadAndWriteFileFromDirectory.readFileAndWriteToFile(fromFile, toFile, "123", "txt");
		dictScoreChange(fromFile,score,splitmark,"");
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * 例如   a 是文件里的内容
	 * 然后， 传入的分数是   1.  分割符号是   tab
	 *    输出文件是     a		1
	 * 传入词典文件， 传入一个指定分数， 给每个单词赋值这个分数。  同时输入  修改后存储的地址。
	 * splitMark:  单词 和  分数中间的分割符号。
	 * @param fromFileName  ：   字典文件存储地址
	 * @param Score： 单词得分
	 * @param splitMark:  分割符号是一个   tab
	 * @param newFilePath:  新的字典文件存储地址  例如 w://abc.txt
	 * @return
	 */
	public static String dictScoreChange(String fromFileName,int Score, String splitMark,String newFilePath) {
		if (String_handle_Zack_Util.isEmpty(newFilePath)) {
			String fileNamwithSurfix=ReadAndWriteFileFromDirectory.getFileNameAndSurfixFromFilePath(fromFileName);
			String fileN=ReadAndWriteFileFromDirectory.getFileNameFromFilePath(fromFileName);
			newFilePath=fromFileName.replace(fileNamwithSurfix, "")+fileN+"_Score.txt";
		}
//		System.out.println("新文件路径："+newFilePath);
		if (String_handle_Zack_Util.isEmpty(splitMark)) {
			splitMark="\t";
		}
		 
        File file = new File(fromFileName);
        BufferedReader reader = null;
        String fileString1="";
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString ="";
            int line = 1;
            //写入文件
            //if file doesnt exists, then create it
            File file1 =new File(newFilePath);
	           if(!file1.exists()){
	        	   file1.createNewFile();
	           }
            FileWriter fileWriter=new FileWriter(newFilePath, true);
	        BufferedWriter bw = new BufferedWriter(fileWriter);
            // 对写入文件的配置
	        
            while ((tempString = reader.readLine()) != null) {
//             fileString1=fileString1+tempString+"\r\n";
            	String tempWord=tempString+splitMark+Score+"\r\n";
                line++;
                bw.write(tempWord);
	             bw.flush();
	           
            }
            bw.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return fileString1;
    }
	
}
