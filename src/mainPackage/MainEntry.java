package mainPackage;

import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import segSentence.AnalyzerArticle;
import articleToSentence.ArticleImpl;
import articleToSentence.Paragraph;
import articleToSentence.Sentence;
import articleToSentence.SubSentence;
import articleToSentence.splitArticle;
import calculateSentenceScore.CalculateSenScore;

public class MainEntry {

	
	public static void main(String[] args) {
		String fileString="W:\\myproj\\sentimentalProj\\articleFile_1\\�������.txt";
		String fileRegex="W:\\myproj\\sentimentalProj\\ruleFile_2\\rule_extractWord.txt";
		analyzeArticleSentence(fileString,fileRegex);
	}
	
	
	/**�������£� ���� �����ļ���·���� ��ȡ��ƪ���µĵ÷֣� ���ҷ�����ƪ�����������
	 * ����һ�����£� �����µ�ÿһ�仰���зִʴ�������������һƪ���·�װ������Ԫ������
	 * @param filePath ���µ�·����W:\zackjob\hacker\nlp_workspace_files\temp\�������.txt
	 */
	public static void analyzeArticleSentence(String filePath, String regexfile){
		//����·��
		String pathString=filePath;
		ArticleImpl articleObj=splitArticle.getArticleObject(pathString);
		//��ƪ���µĵ÷�
		double articleScore=0.0;
		//��ÿ�仰���зִʺʹ��Ա�ע����
		for (Paragraph para:articleObj.getParaList()) {//��ȡÿ������
		    double paraScore=0.0;
			for (Sentence sent:para.getSenList()) {//��ȡÿһ�仰
				double senScore=0.0;
				for (SubSentence subSen:sent.getSubSenList()) {//��ȡÿһ���Ӿ�
//					System.out.println("�Ӿ䣺"+subSen.getSubSenNum()+"---"+subSen.getSubContent());
//					String segPostResultString=AnalyzerArticle.segAndPostagSentence(subSen.getSubContent());
					//��ȡ����   �Ӿ� �ĵ÷�
				  double subSenScore=calculateASentence(subSen.getSubContent(),regexfile);
				  subSen.setSubSenScore(subSenScore);
				  senScore=senScore+subSenScore;
				}
				//������仰�ĵ÷�
				sent.setSentScore(senScore);
				paraScore=paraScore+senScore;
			}
			//������̻��ĵ÷�
			para.setParaScore(paraScore);
			articleScore=articleScore+paraScore;
			System.out.println();
			System.out.println("����-----------------------------------------------------------==: "+paraScore);
		}
		articleObj.setArtScore(articleScore);
		System.out.println();
		System.out.println("��ƪ���µĵ÷�------------------------------------------------------------==: "+articleScore);
	}
	
	
	
	public static double calculateASentence(String sentence,String Regefile){
		//׼����һ�仰
//		String sentence="û��ϲ���Һ�ϲ��ŷ����ã��Ҳ��ǳ��ǳ��ǳ�����������һ�����ȷǳ�Ư�����ˡ���ʵ���Ҿ����������ر��ر�󡣲������������һ����൱ϲ�����ġ���ֻ��һ�㲻ϲ����";
//		String sentence="�Һ�ϲ���ܰ����������������ǳ��ǳ�Ư���ǳ�ϲ��";
		//׼����һ����ֵĹ����ļ��� ���ļ�����ȡ  ����   �̶ȴ�+���ݴʵ���ϣ� �Լ�  �̶ȴ�+���ʵ���� ��
//		String Regefile="W:\\myproj\\sentimentalProj\\ruleFile_2\\rule_extractWord.txt";
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
		System.out.println(sentence+" ---���ܷ��ǣ� "+score);
		return score;
	}
	

	
	/**
	 * ����һ�仰��һ�� �����ļ���  ��ȡ��仰�ĵ÷֡�����map�� Ҳ������仰���ܵ÷�
	 */
	@Test
	public void test_CalculateSenScore(){
		//׼����һ�仰
		String sentence="û��ϲ���Һ�ϲ��ŷ����ã��Ҳ��ǳ��ǳ��ǳ�����������һ�����ȷǳ�Ư�����ˡ���ʵ���Ҿ����������ر��ر�󡣲������������һ����൱ϲ�����ġ���ֻ��һ�㲻ϲ����";
//		String sentence="�Һ�ϲ���ܰ����������������ǳ��ǳ�Ư���ǳ�ϲ��";
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
