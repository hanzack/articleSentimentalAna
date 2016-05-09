package test_cases;

import org.junit.Test;

import 给词典文件赋分.WriteDictScore;
import fileHandle_From_Directory.ReadAndWriteFileFromDirectory;

public class Test_WriteDictScore {

	
	/**
	 * 给动词字典文件 的每一个单词 后面 跟上分数。
	 */
	@Test
	public void test_dictScoreChange0(){
		String fromFile="W:\\myproj\\sentimentalProj\\dict_All\\verb\\正面情感词语（中文）.txt";
		int score=1;
		String splitmark="\t";
		String toFile="W:\\myproj\\sentimentalProj\\dict_All\\verb\\正面动词_score.txt";
//		String toFile="W:\\myproj\\sentimentalProj\\dict_All\\";
//		ReadAndWriteFileFromDirectory.readFileAndWriteToFile(fromFile, toFile, "123", "txt");
		WriteDictScore.dictScoreChange(fromFile,score,splitmark,toFile);
	}
	
	
	/**
	 * 给动词字典文件 的每一个单词 后面 跟上分数。
	 */
	@Test
	public void test_dictScoreChange1(){
		String fromFile="W:\\myproj\\sentimentalProj\\dict_All\\verb\\负面情感词语（中文）.txt";
		int score=-1;
		String splitmark="\t";
		String toFile="W:\\myproj\\sentimentalProj\\dict_All\\verb\\负面动词_score.txt";
//		String toFile="W:\\myproj\\sentimentalProj\\dict_All\\";
//		ReadAndWriteFileFromDirectory.readFileAndWriteToFile(fromFile, toFile, "123", "txt");
		WriteDictScore.dictScoreChange(fromFile,score,splitmark,toFile);
	}
	
	
	
	
	/**
	 * 给形容词字典文件 的每一个单词 后面 跟上分数。  正面
	 */
	@Test
	public void test_dictScoreChange12(){
		String fromFile="W:\\myproj\\sentimentalProj\\dict_All\\adj\\正面评价词语（中文）.txt";
		int score=1;
		String splitmark="\t";
		String toFile="W:\\myproj\\sentimentalProj\\dict_All\\adj\\正面形容词_score.txt";
//		String toFile="W:\\myproj\\sentimentalProj\\dict_All\\";
//		ReadAndWriteFileFromDirectory.readFileAndWriteToFile(fromFile, toFile, "123", "txt");
		WriteDictScore.dictScoreChange(fromFile,score,splitmark,toFile);
	}
	
	
	
	
	/**
	 * 给形容词字典文件 的每一个单词 后面 跟上分数。  负面
	 */
	@Test
	public void test_dictScoreChange13(){
		String fromFile="W:\\myproj\\sentimentalProj\\dict_All\\adj\\负面评价词语（中文）.txt";
		int score=-1;
		String splitmark="\t";
		String toFile="W:\\myproj\\sentimentalProj\\dict_All\\adj\\负面形容词_score.txt";
//		String toFile="W:\\myproj\\sentimentalProj\\dict_All\\";
//		ReadAndWriteFileFromDirectory.readFileAndWriteToFile(fromFile, toFile, "123", "txt");
		WriteDictScore.dictScoreChange(fromFile,score,splitmark,toFile);
	}
	
	
	
	
	/**
	 * 给程度词字典文件 的每一个单词 后面 跟上分数。  正面
	 */
	@Test
	public void test_dictScoreChange14(){
		String fromFile="W:\\myproj\\sentimentalProj\\dict_All\\degree\\正面程度级别词语（中文）.txt";
		int score=2;
		String splitmark="\t";
		String toFile="W:\\myproj\\sentimentalProj\\dict_All\\degree\\正面程度级别词语_score.txt";
//		String toFile="W:\\myproj\\sentimentalProj\\dict_All\\";
//		ReadAndWriteFileFromDirectory.readFileAndWriteToFile(fromFile, toFile, "123", "txt");
		WriteDictScore.dictScoreChange(fromFile,score,splitmark,toFile);
	}
	
	
	
	/**
	 * 给程度字典文件 的每一个单词 后面 跟上分数。  负面
	 */
	@Test
	public void test_dictScoreChange15(){
		String fromFile="W:\\myproj\\sentimentalProj\\dict_All\\degree\\负面程度级别词语（中文）.txt";
		int score=-2;
		String splitmark="\t";
		String toFile="W:\\myproj\\sentimentalProj\\dict_All\\degree\\负面程度级别词语_score.txt";
//		String toFile="W:\\myproj\\sentimentalProj\\dict_All\\";
//		ReadAndWriteFileFromDirectory.readFileAndWriteToFile(fromFile, toFile, "123", "txt");
		WriteDictScore.dictScoreChange(fromFile,score,splitmark,toFile);
	}
	
	
	
	
	
	
	
	
	
}
