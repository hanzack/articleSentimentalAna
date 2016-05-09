package test_cases;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import segSentence.AnalyzerArticle;
import splitWordByIctclas.splitWordByIctclas;

public class Test_AnalyzerArticle {

	/**
	 * 测试一句话的分词
	 */
	@Test
	public void test_segAndPostagSentence(){
//		String abcString="其实不认为他是一个整体很很极度不错的好人，节奏明快，不是特别美丽，不很漂亮。不是很特别，不特别";
		String abcString="我很喜欢你.不是认真喜欢他。没努力，没有用功。这话用在我们这个社会上，针对性太强了。喜欢侮辱他。规划、实施计划，从来不缺少的就是大方向，唯独缺少的";
	String reString=AnalyzerArticle.segAndPostagSentence(abcString);
	System.out.println(reString);
	}
	
	
	
	/**
	 * 测试一篇文章分词
	 */
	@Test
	public void test_analyzeSentence(){
	String pathString="W:\\zackjob\\hacker\\nlp_workspace_files\\temp\\情感文章.txt";	
	AnalyzerArticle.analyzeArticleSentence(pathString);
	}
	
	
	
	/**
	 * 测试根据规则文件提取词语： 然后，对词语进行排序写入map中
	 * ([\\d]+_@[\u4e00-\u9fa5a-zA-Z0-9]*===/d\\s)*([\\d]+_@[\u4e00-\u9fa5a-zA-Z0-9]*==/a)
	 */
	@Test
	public void getSegWordFromMatchRule(){
//		String abcString="其实不认为他是一个整体很很极度不错的好人，节奏明快，不是特别美丽，不很漂亮。不是很特别，不特别";
		String abcString="实战目标还不是很丰富，实际效果还不是很强。不很美丽而且大方，非常非常漂亮可爱。";
	String reString=AnalyzerArticle.segAndPostagSentence(abcString);
	System.out.println("原始： "+ reString);
	String regexFile="W:\\myproj\\sentimentalProj\\ruleFile_2\\rule_extractWord.txt";
	List<String> resList=AnalyzerArticle.getSegWordFromMatchRuleReturnList(regexFile, reString);
	for (String lString:resList) {
		System.out.println(lString);
	}
	
	System.out.println("finish");
	
	System.out.println();
	System.out.println();
	//这个map里面有一个句子里的所有分词， 然后按照先后顺序存入map
	Map<String, Double> eachWordMap=AnalyzerArticle.SortSegWordIntoMap(resList);
	Set<String> key=eachWordMap.keySet();
	for (String keyString:key) {
		System.out.println(keyString+"  "+eachWordMap.get(keyString));
	}
	
	
	}
	

	

	/**
	 * 测试根据规则文件提取词语： 然后，对词语进行排序写入map中
	 * ([\\d]+_@[\u4e00-\u9fa5a-zA-Z0-9]*===/d\\s)*([\\d]+_@[\u4e00-\u9fa5a-zA-Z0-9]*==/a)
	 */
	@Test
	public void getScorefromVerb(){
//		String abcString="其实不认为他是一个整体很很极度不错的好人，节奏明快，不是特别美丽，不很漂亮。不是很特别，不特别";
		String abcString="我非常非常喜欢她，实战目标还不是很丰富，实际效果还不是很强。不很美丽而且大方，非常非常漂亮可爱。";
	String reString=AnalyzerArticle.segAndPostagSentence(abcString);
	System.out.println("原始： "+ reString);
	String regexFile="W:\\myproj\\sentimentalProj\\ruleFile_2\\rule_extractWord.txt";
	List<String> resList=AnalyzerArticle.getSegWordFromMatchRuleReturnList(regexFile, reString);
	System.out.println("finish");
	System.out.println();
	String firstSen="";
	//这个map里面有一个句子里的所有分词， 然后按照先后顺序存入map
	Map<String, Double> eachWordMap=AnalyzerArticle.SortSegWordIntoMap(resList);
	Set<String> key=eachWordMap.keySet();
	int abc=0;
	for (String keyString:key) {
		if (abc==0) {
			firstSen=keyString;
		}
//		System.out.println(keyString+"  "+eachWordMap.get(keyString));
		abc++;
		}
	System.out.println(firstSen);
	
	}
	

	

	

	
	
	
}
