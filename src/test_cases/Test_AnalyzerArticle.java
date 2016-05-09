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
	 * ����һ�仰�ķִ�
	 */
	@Test
	public void test_segAndPostagSentence(){
//		String abcString="��ʵ����Ϊ����һ������ܼܺ��Ȳ���ĺ��ˣ��������죬�����ر�����������Ư�������Ǻ��ر𣬲��ر�";
		String abcString="�Һ�ϲ����.��������ϲ������ûŬ����û���ù����⻰���������������ϣ������̫ǿ�ˡ�ϲ�����������滮��ʵʩ�ƻ���������ȱ�ٵľ��Ǵ���Ψ��ȱ�ٵ�";
	String reString=AnalyzerArticle.segAndPostagSentence(abcString);
	System.out.println(reString);
	}
	
	
	
	/**
	 * ����һƪ���·ִ�
	 */
	@Test
	public void test_analyzeSentence(){
	String pathString="W:\\zackjob\\hacker\\nlp_workspace_files\\temp\\�������.txt";	
	AnalyzerArticle.analyzeArticleSentence(pathString);
	}
	
	
	
	/**
	 * ���Ը��ݹ����ļ���ȡ��� Ȼ�󣬶Դ����������д��map��
	 * ([\\d]+_@[\u4e00-\u9fa5a-zA-Z0-9]*===/d\\s)*([\\d]+_@[\u4e00-\u9fa5a-zA-Z0-9]*==/a)
	 */
	@Test
	public void getSegWordFromMatchRule(){
//		String abcString="��ʵ����Ϊ����һ������ܼܺ��Ȳ���ĺ��ˣ��������죬�����ر�����������Ư�������Ǻ��ر𣬲��ر�";
		String abcString="ʵսĿ�껹���Ǻܷḻ��ʵ��Ч�������Ǻ�ǿ�������������Ҵ󷽣��ǳ��ǳ�Ư���ɰ���";
	String reString=AnalyzerArticle.segAndPostagSentence(abcString);
	System.out.println("ԭʼ�� "+ reString);
	String regexFile="W:\\myproj\\sentimentalProj\\ruleFile_2\\rule_extractWord.txt";
	List<String> resList=AnalyzerArticle.getSegWordFromMatchRuleReturnList(regexFile, reString);
	for (String lString:resList) {
		System.out.println(lString);
	}
	
	System.out.println("finish");
	
	System.out.println();
	System.out.println();
	//���map������һ������������зִʣ� Ȼ�����Ⱥ�˳�����map
	Map<String, Double> eachWordMap=AnalyzerArticle.SortSegWordIntoMap(resList);
	Set<String> key=eachWordMap.keySet();
	for (String keyString:key) {
		System.out.println(keyString+"  "+eachWordMap.get(keyString));
	}
	
	
	}
	

	

	/**
	 * ���Ը��ݹ����ļ���ȡ��� Ȼ�󣬶Դ����������д��map��
	 * ([\\d]+_@[\u4e00-\u9fa5a-zA-Z0-9]*===/d\\s)*([\\d]+_@[\u4e00-\u9fa5a-zA-Z0-9]*==/a)
	 */
	@Test
	public void getScorefromVerb(){
//		String abcString="��ʵ����Ϊ����һ������ܼܺ��Ȳ���ĺ��ˣ��������죬�����ر�����������Ư�������Ǻ��ر𣬲��ر�";
		String abcString="�ҷǳ��ǳ�ϲ������ʵսĿ�껹���Ǻܷḻ��ʵ��Ч�������Ǻ�ǿ�������������Ҵ󷽣��ǳ��ǳ�Ư���ɰ���";
	String reString=AnalyzerArticle.segAndPostagSentence(abcString);
	System.out.println("ԭʼ�� "+ reString);
	String regexFile="W:\\myproj\\sentimentalProj\\ruleFile_2\\rule_extractWord.txt";
	List<String> resList=AnalyzerArticle.getSegWordFromMatchRuleReturnList(regexFile, reString);
	System.out.println("finish");
	System.out.println();
	String firstSen="";
	//���map������һ������������зִʣ� Ȼ�����Ⱥ�˳�����map
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
