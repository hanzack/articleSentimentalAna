package test_cases;

import org.junit.Test;

import ���ʵ��ļ�����.WriteDictScore;
import fileHandle_From_Directory.ReadAndWriteFileFromDirectory;

public class Test_WriteDictScore {

	
	/**
	 * �������ֵ��ļ� ��ÿһ������ ���� ���Ϸ�����
	 */
	@Test
	public void test_dictScoreChange0(){
		String fromFile="W:\\myproj\\sentimentalProj\\dict_All\\verb\\������д�����ģ�.txt";
		int score=1;
		String splitmark="\t";
		String toFile="W:\\myproj\\sentimentalProj\\dict_All\\verb\\���涯��_score.txt";
//		String toFile="W:\\myproj\\sentimentalProj\\dict_All\\";
//		ReadAndWriteFileFromDirectory.readFileAndWriteToFile(fromFile, toFile, "123", "txt");
		WriteDictScore.dictScoreChange(fromFile,score,splitmark,toFile);
	}
	
	
	/**
	 * �������ֵ��ļ� ��ÿһ������ ���� ���Ϸ�����
	 */
	@Test
	public void test_dictScoreChange1(){
		String fromFile="W:\\myproj\\sentimentalProj\\dict_All\\verb\\������д�����ģ�.txt";
		int score=-1;
		String splitmark="\t";
		String toFile="W:\\myproj\\sentimentalProj\\dict_All\\verb\\���涯��_score.txt";
//		String toFile="W:\\myproj\\sentimentalProj\\dict_All\\";
//		ReadAndWriteFileFromDirectory.readFileAndWriteToFile(fromFile, toFile, "123", "txt");
		WriteDictScore.dictScoreChange(fromFile,score,splitmark,toFile);
	}
	
	
	
	
	/**
	 * �����ݴ��ֵ��ļ� ��ÿһ������ ���� ���Ϸ�����  ����
	 */
	@Test
	public void test_dictScoreChange12(){
		String fromFile="W:\\myproj\\sentimentalProj\\dict_All\\adj\\�������۴�����ģ�.txt";
		int score=1;
		String splitmark="\t";
		String toFile="W:\\myproj\\sentimentalProj\\dict_All\\adj\\�������ݴ�_score.txt";
//		String toFile="W:\\myproj\\sentimentalProj\\dict_All\\";
//		ReadAndWriteFileFromDirectory.readFileAndWriteToFile(fromFile, toFile, "123", "txt");
		WriteDictScore.dictScoreChange(fromFile,score,splitmark,toFile);
	}
	
	
	
	
	/**
	 * �����ݴ��ֵ��ļ� ��ÿһ������ ���� ���Ϸ�����  ����
	 */
	@Test
	public void test_dictScoreChange13(){
		String fromFile="W:\\myproj\\sentimentalProj\\dict_All\\adj\\�������۴�����ģ�.txt";
		int score=-1;
		String splitmark="\t";
		String toFile="W:\\myproj\\sentimentalProj\\dict_All\\adj\\�������ݴ�_score.txt";
//		String toFile="W:\\myproj\\sentimentalProj\\dict_All\\";
//		ReadAndWriteFileFromDirectory.readFileAndWriteToFile(fromFile, toFile, "123", "txt");
		WriteDictScore.dictScoreChange(fromFile,score,splitmark,toFile);
	}
	
	
	
	
	/**
	 * ���̶ȴ��ֵ��ļ� ��ÿһ������ ���� ���Ϸ�����  ����
	 */
	@Test
	public void test_dictScoreChange14(){
		String fromFile="W:\\myproj\\sentimentalProj\\dict_All\\degree\\����̶ȼ��������ģ�.txt";
		int score=2;
		String splitmark="\t";
		String toFile="W:\\myproj\\sentimentalProj\\dict_All\\degree\\����̶ȼ������_score.txt";
//		String toFile="W:\\myproj\\sentimentalProj\\dict_All\\";
//		ReadAndWriteFileFromDirectory.readFileAndWriteToFile(fromFile, toFile, "123", "txt");
		WriteDictScore.dictScoreChange(fromFile,score,splitmark,toFile);
	}
	
	
	
	/**
	 * ���̶��ֵ��ļ� ��ÿһ������ ���� ���Ϸ�����  ����
	 */
	@Test
	public void test_dictScoreChange15(){
		String fromFile="W:\\myproj\\sentimentalProj\\dict_All\\degree\\����̶ȼ��������ģ�.txt";
		int score=-2;
		String splitmark="\t";
		String toFile="W:\\myproj\\sentimentalProj\\dict_All\\degree\\����̶ȼ������_score.txt";
//		String toFile="W:\\myproj\\sentimentalProj\\dict_All\\";
//		ReadAndWriteFileFromDirectory.readFileAndWriteToFile(fromFile, toFile, "123", "txt");
		WriteDictScore.dictScoreChange(fromFile,score,splitmark,toFile);
	}
	
	
	
	
	
	
	
	
	
}
