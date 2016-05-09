package test_cases;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import segSentence.AnalyzerArticle;
import calculateSentenceScore.CalculateSenScore;

public class test_calculateSenScore {
	/**
	 * �����ַ����� ��ȡ����Ķ���
	 */
	@Test
	public void test_getSpecificWord(){
		String wordPhrase="1_@�ǳ�/d 2_@�ǳ�/d 3_@ϲ��/v 4_@����/v 5_@��ȥ/v";
		List<String> wordList=CalculateSenScore.getSpecificWordVerbOrAdjOrDegr(wordPhrase,"/v");
		for (String verbWord:wordList) {
			System.out.println(verbWord);
		}
	}
	
	
	/**
	 * �����ַ����� ��ȡ����ĳ̶ȴ�
	 */
	@Test
	public void test_getSpecificWord1(){
		String wordPhrase="1_@�ǳ�/d 2_@�ǳ�/d 3_@ϲ��/v 4_@����/v 5_@��ȥ/v";
		List<String> wordList=CalculateSenScore.getSpecificWordVerbOrAdjOrDegr(wordPhrase,"/d");
		for (String verbWord:wordList) {
			System.out.println(verbWord);
		}
	}
	
	
	/**
	 * ����һ��������һ���ֵ�����ĵ÷�
	 */
	@Test
	public void test_retrieveScore(){
		String word="����";
		String dictPathString="W:\\myproj\\sentimentalProj\\dict_All\\verb\\���渺�涯��_score.txt";
		double score=CalculateSenScore.retrieveAWordScore(word, dictPathString);
		System.out.println(word+"---"+score);
	}
	
	
	/**
	 * ����һ��������Ķ�����ϵ��ܵ÷�
	 */
	@Test
	public void Test_getVerbScore(){
		String verbsString="1_@�ǳ�/d 2_@�ǳ�/d 3_@ϲ��/v 4_@ϲ��/v 5_@��/v";
		double score=CalculateSenScore.getVerbScore(verbsString);
		System.out.println(score);
	}
	
	
	/**
	 * ����һ������������ݴ���ϵ��ܵ÷�
	 */
	@Test
	public void Test_getAdjScore(){
		// ��������һ�������� �÷�Ӧ����2
		String verbsString="0_@��/r 1_@�ǳ�/d 2_@����/a 3_@��/a 4_@�ɰ�/a 5_@ɵ/a";
		double score=CalculateSenScore.getAdjScore(verbsString);
		System.out.println(score);
	}
	
	
	
	/**
	 * ����һ��������ĳ̶ȴ���ϵ��ܵ÷�
	 */
	@Test
	public void Test_getDegreeScore(){
		String abcString="��ϲ������";
		String reString=AnalyzerArticle.segAndPostagSentence(abcString);
		System.out.println(reString);
		// ��������һ�������� �÷�Ӧ����2
//		String verbsString="0_@��/r 1_@�ǳ�/d 2_@����/a 3_@��/a 4_@�ɰ�/a 5_@ɵ/a";
		double score=CalculateSenScore.getDegreeScore(reString);
		System.out.println(score);
	}
	
	
	/**
	 *  �ж��� �̶ȴʼ����ݴʵ���ϣ� ���ǳ̶ȴʼӶ��ʵ���ϡ�
	 */
	@Test
	public void test_ifDAorDV(){
		String wordPhrase="1_@ϲ��/a 1_@ϲ��/a";
		String abcString=CalculateSenScore.ifDAorDV(wordPhrase);
		System.out.println(abcString);
	}
	
	
	/**
	 * ��һ�仰���зִʣ����չ�����ȡ���飬Ȼ�����������map�У� Ȼ���жϳ�ÿһ��������  �̶ȴʼ����ݴ���ϣ����ǳ̶ȴʼӶ�����ϡ�
	 */
	@Test
	public void testMore_test_ifDAorDV(){
		//�Ȼ�ȡһ�仰�Ĳ��
		String sentence="�Һ�ϲ�������ҷǳ��ǳ��ǳ�������������һ�����ȷǳ�Ư������";
		String fenciString=AnalyzerArticle.segAndPostagSentence(sentence);
		//ͨ�������ļ���ȡ���ڣ��̶ȴ����ݴʵ���ϣ����̶߳ȴ��붯�ʵ����
		String regexFile="W:\\myproj\\sentimentalProj\\ruleFile_2\\rule_extractWord.txt";
		List<String> phraseList=AnalyzerArticle.getSegWordFromMatchRuleReturnList(regexFile, fenciString);
		//Ȼ�����map��
		Map<String, Double> phraseMap=AnalyzerArticle.SortSegWordIntoMap(phraseList);
		
		// �ж�ÿһ��������  DA ���ͻ��� DV����
		Set<String> keysS=phraseMap.keySet();
		for (String string : keysS) {
			String flag=CalculateSenScore.ifDAorDV(string);
			System.out.println(string+" : "+flag);
		}
		
		
	}
	
	
	
	
	
	/**
	 * ��һ�仰���зִʣ����չ�����ȡ���飬Ȼ�����������map�У�Ȼ���ȡÿһ��key   �ĵ÷֣���д��value�С�
	 */
	@Test
	public void test_getScoreForDVOrDA(){
		//�Ȼ�ȡһ�仰�Ĳ��
		String sentence="�Һ�ϲ��ŷ����ã��ҷǳ��ǳ��ǳ�������������һ�����ȷǳ�Ư�����ˡ���ʵ���Ҿ����������ر��ر�󡣲������������һ����൱ϲ�����ġ���ֻ��һ�㲻ϲ����";
//		String sentence="�ҷǳ��ǳ��ǳ�������";
//		String sentence="�Һ�ϲ�������ҷǳ��ǳ��ǳ���������";
		String fenciString=AnalyzerArticle.segAndPostagSentence(sentence);
		System.out.println("ԭʼ�ִʣ� "+fenciString);
		//ͨ�������ļ���ȡ���ڣ��̶ȴ����ݴʵ���ϣ����̶߳ȴ��붯�ʵ����
		String regexFile="W:\\myproj\\sentimentalProj\\ruleFile_2\\rule_extractWord.txt";
		List<String> phraseList=AnalyzerArticle.getSegWordFromMatchRuleReturnList(regexFile, fenciString);
		//Ȼ�����map��
		Map<String, Double> phraseMap=AnalyzerArticle.SortSegWordIntoMap(phraseList);
		
		// �ж�ÿһ��������  DA ���ͻ��� DV����
		Set<String> keysS=phraseMap.keySet();
		for (String string : keysS) {
			double scorePh=CalculateSenScore.getScoreForDVOrDA(string);
			System.out.println(string+"  �÷֣� "+scorePh);
		}
		
		
	}
	
	
	
	/**
	 * ����һ�仰�� ��ȡһ��map�� map�����key�ǣ�һЩ����������ȡ��Ĵ��飬value�� ���������������Ե÷� 
	 */
	@Test
	public void test_scoreSentenceSubPhreaseIntoMap(){
		String sentence="�Һ�ϲ��ŷ����ã��Ҳ��ǳ��ǳ��ǳ�����������һ�����ȷǳ�Ư�����ˡ���ʵ���Ҿ����������ر��ر�󡣲������������һ����൱ϲ�����ġ���ֻ��һ�㲻ϲ����";
		String Regefile="W:\\myproj\\sentimentalProj\\ruleFile_2\\rule_extractWord.txt";
		Map<String, Double> phraseMap=CalculateSenScore.scoreSentenceSubPhreaseIntoMap(sentence,Regefile);
		// �ж�ÿһ��������  DA ���ͻ��� DV����
		System.out.println();
		System.out.println("-------------�޸��Ժ����map-------------------");
		Set<String> keysS=phraseMap.keySet();
				for (String string : keysS) {
					System.out.println(string+"  �÷֣� "+phraseMap.get(string));
				}
	}
	
	
	
	
	
	
	
	/**
	 * ��ʱ����   FurtherCalculateScoreForMap ����
	 */
	@Test
	public void test_FurtherCalculateScoreForMap(){
		//׼����һ�仰
//		String sentence="û��ϲ���Һ�ϲ��ŷ����ã��Ҳ��ǳ��ǳ��ǳ�����������һ�����ȷǳ�Ư�����ˡ���ʵ���Ҿ����������ر��ر�󡣲������������һ����൱ϲ�����ġ���ֻ��һ�㲻ϲ����";
		String sentence="����һ������������";
		//׼����һ����ֵĹ����ļ��� ���ļ�����ȡ  ����   �̶ȴ�+���ݴʵ���ϣ� �Լ�  �̶ȴ�+���ʵ���� ��
		String Regefile="W:\\myproj\\sentimentalProj\\ruleFile_2\\rule_extractWord.txt";
		//  Ȼ��    ���ݹ����ļ���һ�����ӣ� ���зִʣ�����ȡ�����ķִʷ��뵽map�У�value  �Ƿ��Ϲ���ķִʣ� key������ִʵĵ÷֣���ʼֵΪ0.0
		Map<String, Double> phraseMap=CalculateSenScore.scoreSentenceSubPhreaseIntoMap(sentence,Regefile);
		
		phraseMap=CalculateSenScore.FurtherCalculateScoreForMap(phraseMap);
		Set<String> key1=phraseMap.keySet();
//		for (String key:key1) {
//			//�������ÿ���� �Լ�  ��Ӧ�ķ�����
//			System.out.println(key+" : "+phraseMap.get(key));
//		}
		//��ȡmap���ܷ�
		double score=CalculateSenScore.CalculateMapTotalScore(phraseMap);
		System.out.println("�ܷ��ǣ� "+score);
	}
	
	
	
	
	
}












