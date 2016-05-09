package ���ʵ��ļ�����;

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
	 * ��һ���ֵ��ļ� ��ÿһ������ ���� ���Ϸ�����
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
	 * ����   a ���ļ��������
	 * Ȼ�� ����ķ�����   1.  �ָ������   tab
	 *    ����ļ���     a		1
	 * ����ʵ��ļ��� ����һ��ָ�������� ��ÿ�����ʸ�ֵ���������  ͬʱ����  �޸ĺ�洢�ĵ�ַ��
	 * splitMark:  ���� ��  �����м�ķָ���š�
	 * @param fromFileName  ��   �ֵ��ļ��洢��ַ
	 * @param Score�� ���ʵ÷�
	 * @param splitMark:  �ָ������һ��   tab
	 * @param newFilePath:  �µ��ֵ��ļ��洢��ַ  ���� w://abc.txt
	 * @return
	 */
	public static String dictScoreChange(String fromFileName,int Score, String splitMark,String newFilePath) {
		if (String_handle_Zack_Util.isEmpty(newFilePath)) {
			String fileNamwithSurfix=ReadAndWriteFileFromDirectory.getFileNameAndSurfixFromFilePath(fromFileName);
			String fileN=ReadAndWriteFileFromDirectory.getFileNameFromFilePath(fromFileName);
			newFilePath=fromFileName.replace(fileNamwithSurfix, "")+fileN+"_Score.txt";
		}
//		System.out.println("���ļ�·����"+newFilePath);
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
            //д���ļ�
            //if file doesnt exists, then create it
            File file1 =new File(newFilePath);
	           if(!file1.exists()){
	        	   file1.createNewFile();
	           }
            FileWriter fileWriter=new FileWriter(newFilePath, true);
	        BufferedWriter bw = new BufferedWriter(fileWriter);
            // ��д���ļ�������
	        
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
