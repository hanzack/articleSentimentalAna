package segSentence;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import list_Set_Map_Handle_Zack_util.mapHandleZackUtil;
import regex_String_File_Zack_util.regex_All_util;
import splitWordByIctclas.splitWordByIctclas;
import articleToSentence.ArticleImpl;
import articleToSentence.Paragraph;
import articleToSentence.Sentence;
import articleToSentence.SubSentence;
import articleToSentence.splitArticle;
/**
 * 
 * 
 * @author The author is Zack email: hanzack@163.com
 * 
 */
public class AnalyzerArticle {

	
	/**
	 * ����һ�����£� �����µ�ÿһ�仰���зִʴ�������������һƪ���·�װ������Ԫ������
	 * @param filePath ���µ�·����W:\zackjob\hacker\nlp_workspace_files\temp\�������.txt
	 */
	public static void analyzeArticleSentence(String filePath){
		//����·��
		String pathString=filePath;
		ArticleImpl articleObj=splitArticle.getArticleObject(pathString);
		//��ÿ�仰���зִʺʹ��Ա�ע����
		for (Paragraph para:articleObj.getParaList()) {//��ȡÿ������
			for (Sentence sent:para.getSenList()) {//��ȡÿһ�仰
				for (SubSentence subSen:sent.getSubSenList()) {//��ȡÿһ���Ӿ�
//					System.out.println("���䣺 "+para.getParaNumber());
//					System.out.println("���ӣ�"+sent.getSentenceNum());
//					System.out.println("�Ӿ䣺"+subSen.getSubSenNum()+"---"+subSen.getSubContent());
					String segPostResultString=segAndPostagSentence(subSen.getSubContent());
					
				}
				
			}
			System.out.println("-------------------------------------");
			System.out.println();
		}
	}
	
	
	/**
	 * ��һ���Ӿ���д��Ա�ע�ͷִʡ� ���ҷ��طִʺʹ��Ա�ע����ַ���
	 * @param Subsent
	 */
	public static String segAndPostagSentence(String Subsent){
		
		String posString="";
			posString=splitWordByIctclas.getSplitWordsByICTCLASWithUserDictWithNum(Subsent, 1);
		return posString;
	}
	
	
	/**
	 * ���ݹ����ļ���ȡ���ϵĴ��
	 *   ����  һ��������ʽ���ļ���  ���봫��һ���������Ա�ע�ͷִʺ�����»��߾��ӣ� ��ȡ������������ʽ�����дʣ� ����list���档 ע�����˳��һ��������ġ�
	 * @param regexFile
	 * @param content   ����Ķ�ʽ�����ִʺ���ַ�����
	 * @return
	 */
	public static List<String> getSegWordFromMatchRuleReturnList(String regexFile,String content){
		List<String> resultList=regex_All_util.getContentByRegexFileReturnList(regexFile, content);
		return resultList;
	}
	
	
	
	/**
	 * ��ȡmap�����Ƿ��ϳ̶ȴʼ����ݴʵ���ϣ� �����ǳ̶ȴʼӶ��ʵ���ϡ� ���Ұ�����Щ�����ھ����е��Ⱥ�˳�����������
	 * map=(0_@��/d 1_@ϲ��/v, 0.0)    key ��һ��/d/v   ������  /d/a  ����ϣ�   value �������ϵĵ÷�
	 * �԰��չ����ȡ�Ĵ������������뵽map��ȥ��map�� double�������������ĵ÷֡���ʼ�÷�Ϊ0
	 * ֮ǰ���list<String> �����word��ϣ� ���������ַ�����ͷ��  1_@����    ����1����������ǵڼ����ʡ�����ķ�����
	 * ���ȳ��ֵĴ��鶼��ǰ����
	 * @param wordList  ͨ�� getSegWordFromMatchRule ����������Ի�ȡlist
	 * @return
	 * һ��map���൱��һ�����ӣ� ����������������г̶ȴ������ݴʣ� ���̶߳ȴ��붯�ʵ����
	 */
	public static Map<String, Double> SortSegWordIntoMap(List<String> wordList){
		Map<String, Double> eachWordMap=new HashMap<>();
		for (String word:wordList) {
			eachWordMap.put(word, 0.0);
		}
		eachWordMap=mapHandleZackUtil.sortMapByMapKeyFirstLetter(eachWordMap, true);
		
		return eachWordMap;
	}
	
	
	
	
	
	
}
