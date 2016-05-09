package calculateSentenceScore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import normalUse.input_Util;

import org.junit.Test;
import org.junit.experimental.theories.Theories;

import Primary_data_type_zack_util.String_handle_Zack_Util;
import segSentence.AnalyzerArticle;
import util.Util_Sentimental;
import fileHandle_From_Directory.ReadAndWriteFileFromDirectory;



/**
 * @author The author is Zack email: hanzack@163.com
 * 
 * �ʵ�Ĵ洢��ַ�� W:\myproj\sentimentalProj\dict_All
 * 
 * ������һ�仰��һ�����ʣ� ����һƪ���½���  �����Ե÷ֵļ��� 
 * ����໹���Լ��� ���ʵ÷֣� ���ݴʵ÷֣��̶ȴʵ÷ֵȵȡ�
 */
public class CalculateSenScore {

	
	/**
	 * ����һ��map�� �������map���ÿһ��÷�֮�͡�
	 * @param wordScoreMap
	 * @return
	 */
	public static double CalculateMapTotalScore(Map<String, Double> wordScoreMap){
		double score=0.0;
			Set<String> set1=wordScoreMap.keySet();
			for (String string : set1) {
				score=score+wordScoreMap.get(string);
			}
	    return score; 
	}
	
	
	/**
	 * @param wordScoreMap:  ����һ��map�� ���� key �ǵ��ʻ��ߴ��飬 value���������������Ե÷�.
	 * ���磺    1_@��/d 2_@��/v   						2.0
	 *       2_@��/d 3_@ϲ��/v						-3
	 *       29_@�ر�/d 30_@�ر�/d 31_@��/a				-4.0
	 *       
	 * @return   ����һ�����Ϻ��map��  
	 * ����      ���棬  ���ǲ�ϲ���� �������������
	 *      1_@��/d 2_@��/v 2_@��/d 3_@ϲ��/v						-6.0
	 *       ��Ϊֻ����  ���� û�����壬�������� ��ϲ��  ��ʱ�򣬾Ϳ��Լ����ܵ÷��ˡ�������Ϊ���������һ���ʡ�
	 */
	public static Map<String, Double> FurtherCalculateScoreForMap(Map<String, Double> wordScoreMap){
//		System.out.println("��FurtherCalculateScoreForMap�����У�");
		Set<String> wordSet=wordScoreMap.keySet();
		// ��setת����list
		ArrayList<String> wordList=new ArrayList<>(wordSet);
		for (int i = 0; i < wordList.size(); i++) {
			System.out.println(wordList.get(i));
			if (isNextStringANeighbor(wordScoreMap, i)) {//�ж�����ַ����ǲ��Ǻ���һ���ַ������ھ�
				//������ھ����ж�����ھ���dv����da
				String theFirString=wordList.get(i);
				String theSecondString=wordList.get(i+1);
				if ("DV".equals(ifDAorDV(wordList.get(i+1)))) {
					wordScoreMap=dvCalculatedForAll(wordScoreMap,theFirString,theSecondString,i);
					//����Ѿ������˵÷־Ͳ����ظ�������
					i=count;
				}else if ("DA".equals(ifDAorDV(wordList.get(i+1)))) {
					wordScoreMap=daCalculatedForAll(wordScoreMap,theFirString,theSecondString,i);
				}else{
					
				}
				
			}else {//������ǣ�����ַ�������÷ּ�����ɣ�
				continue;
			}
		}
		return wordScoreMap;
	}
	
	
	/**
	 * ����DV   ǰ�߳��Ժ��ߣ����ߣ����Ұѷ�����ֵ�����ߣ�ǰ���޸�Ϊ0
	 */
	private static int count=0;
	public static Map<String, Double> dvCalculatedForAll(Map<String, Double> map,String first,String second,int i){
//		System.out.println("��dvCalculatedForAll�����У� "+i+" "+first+"+"+second);
		double theScore=-1.0;
		double firstScore=map.get(first);
		double secondScore=map.get(second);
		if (firstScore==0.0||secondScore==0.0) {
			theScore=firstScore>=secondScore?firstScore:secondScore;
		}else {
			//�����߶���Ϊ0������ˡ�
			theScore=firstScore*secondScore;
		}
		 map.put(first, 0.0);
		 map.put(second, theScore);
		 count=i+1;
		 //Ȼ����еݹ��жϡ� ����ַ�������һ���ַ����Ƿ���Ȼ���ھӡ� ������ھӣ� ���ж���DA����DV���������Ӧ�ķ�����
		 if (isNextStringANeighbor(map,i+1)) {
			 int newI=i+1;
			   Set<String> wordSet=map.keySet();
				// ��setת����list
				ArrayList<String> wordList=new ArrayList<>(wordSet);
			    String firstNext=second;
			    String secondNext=wordList.get(i+2);
			    if ("DV".equals(ifDAorDV(secondNext))) {
//			    System.out.println("dvCalculatedForAll���ڲ�ݹ�ѭ��: "+firstNext+": "+secondNext+"i+1:"+newI);
			    	map=dvCalculatedForAll(map, firstNext, secondNext, i+1);
				}else if ("DA".equals(ifDAorDV(secondNext))) {
					map=daCalculatedForAll(map, firstNext, secondNext, i+1);
				}
			    count=i+2;
	   	}
		 return map;
	}
	
	
	/**
	 * �������ķ������������һ���ģ�����Ϊ�˷����Ժ��޸ģ� ���Էֿ�д����������
	 * @param map
	 * @param first
	 * @param second
	 * @param i
	 * @return
	 */
	public static Map<String, Double> daCalculatedForAll(Map<String, Double> map,String first,String second,int i){
//		System.out.println("��daCalculatedForAll�����У� "+i+" "+first+"+"+second);
		double theScore=-1.0;
		double firstScore=map.get(first);
		double secondScore=map.get(second);
		if (firstScore==0.0||secondScore==0.0) {
			theScore=firstScore>=secondScore?firstScore:secondScore;
		}else {
			//�����߶���Ϊ0������ˡ�
			theScore=firstScore*secondScore;
		}
		 map.put(first, 0.0);
		 map.put(second, theScore);
		 count=i+1;
		 //Ȼ����еݹ��жϡ� ����ַ�������һ���ַ����Ƿ���Ȼ���ھӡ� ������ھӣ� ���ж���DA����DV���������Ӧ�ķ�����
		 if (isNextStringANeighbor(map,i+1)) {
			   int newI=i+1;
			   Set<String> wordSet=map.keySet();
				// ��setת����list
				ArrayList<String> wordList=new ArrayList<>(wordSet);
			    String firstNext=second;
			    String secondNext=wordList.get(i+2);
			    if ("DV".equals(ifDAorDV(secondNext))) {
//			    System.out.println("dvCalculatedForAll���ڲ�ݹ�ѭ��: "+firstNext+": "+secondNext+"i+1:"+newI);
			    	map=dvCalculatedForAll(map, firstNext, secondNext, i+1);
				}else if ("DA".equals(ifDAorDV(secondNext))) {
					map=daCalculatedForAll(map, firstNext, secondNext, i+1);
				}
			    count=i+2;
	   	}
		 return map;
	}
	
	
	/**
	 * ����һ��map����һ���±��жϣ�map������±����һ���±��Ƿ��ھ�
	 * @param map
	 * @param i
	 * @return
	 */
	public static boolean isNextStringANeighbor(Map<String, Double> map, int i){
		
		Set<String> wordSet=map.keySet();
		// ��setת����list
		ArrayList<String> wordList=new ArrayList<>(wordSet);
		if (i<map.size()-1) {
			String first=wordList.get(i);
			String second=wordList.get(i+1);
			boolean ifNeig=Util_Sentimental.ifTwoStringNeighbour(first, second);
			if (ifNeig) {return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
		
	}
	
	
	
	/**
	 * ����һ�����ӵ��ַ���������ֻҪ���ַ������У� Ȼ����һ�������ļ��� ���������ȶ��ַ������зִʺʹ��Ա�ע
	 * �����ļ���һ��������ʽ����������ȡ�������е��ض���ϴ��   ��ȡ��ʽ���������б�ע���С�
	 * ��Щȡ���Ĵ�����뵽map�У����һ����ÿ������������Ե÷֡� �������������ļ���ʹ��Ĭ�ϵĹ����ļ�
	 * 
	 * ������ ���� �����Һ�ϲ���㡱   ��������������зִʺʹ��Ա�ע�� ��ɣ� 0_@��/r 1_@��/d 2_@ϲ��/v 3_@��/r  ����1_@�Ǳ�ע֮�󾭹�ĳһ�������д����
	 * Ŀ����  ��¼  ������ھ�����ʲô˳��    ��ȡ����ִʽ���󣬹����ļ�����ȡ����    /d /v ����ϣ�  ��� 1_@��/d 2_@ϲ��/v  Ȼ�����map��
	 * Ȼ�����   1_@��/d 2_@ϲ��/v   �ĵ÷֣� ���뵽map ��value��ȥ��
	 * @param sentStr   �� һ������ ���磺  ���Һ�ϲ���㡱  
	 * @param regexFilePath  �� "W:\\myproj\\sentimentalProj\\ruleFile_2\\rule_extractWord.txt" 
	 * @return
	 */
	public static Map<String, Double> scoreSentenceSubPhreaseIntoMap(String sentStr,String regexFilePath){
		//�Ȼ�ȡһ�仰�Ĳ��
//		String sentence="�Һ�ϲ��ŷ����ã��ҷǳ��ǳ��ǳ�������������һ�����ȷǳ�Ư�����ˡ���ʵ���Ҿ����������ر��ر�󡣲������������һ����൱ϲ�����ġ���ֻ��һ�㲻ϲ����";
		String sentence=sentStr;
		String fenciString=AnalyzerArticle.segAndPostagSentence(sentence);
//		System.out.println("ԭʼ�ִʣ� "+fenciString);
		//ͨ�������ļ���ȡ���ڣ��̶ȴ����ݴʵ���ϣ����̶߳ȴ��붯�ʵ����
		if (String_handle_Zack_Util.isEmpty(regexFilePath)) {
			regexFilePath="W:\\myproj\\sentimentalProj\\ruleFile_2\\rule_extractWord.txt";
		}
		List<String> phraseList=AnalyzerArticle.getSegWordFromMatchRuleReturnList(regexFilePath, fenciString);
		//Ȼ�����map��
		Map<String, Double> phraseMap=AnalyzerArticle.SortSegWordIntoMap(phraseList);
		
		// �ж�ÿһ��������  DA ���ͻ��� DV����
		Set<String> keysS=phraseMap.keySet();
		for (String string : keysS) {
			double scorePh=CalculateSenScore.getScoreForDVOrDA(string);
//			System.out.println(string+"  �÷֣� "+scorePh);
			phraseMap.put(string, scorePh);
		}
		
		return phraseMap;
	}
	
	
	
	
	/**
	 * ����һ�����������÷֣� ����     /d /d /v /v ���ִ��飬�����ܵ÷֡�
	 * �������  0_@��/d 1_@ϲ��/v   ��ô���Ǽ���   /d ��/v ����ϵ÷ֽ��з��ء�
	 * @param wordPhrase       ����ĳһ����ķִ��ַ���
	 * @return
	 */
	public static double getScoreForDVOrDA(String wordPhrase){
		double score=0.0;
		if ("DA".equals(ifDAorDV(wordPhrase))) {
			double scoreString=getDegreeAndAdjCombScore(wordPhrase);
//			System.out.println(wordPhrase+" ����DA���أ� "+scoreString);
			return scoreString;
		}else if("DV".equals(ifDAorDV(wordPhrase))){
			double scoreString=getDegreeAndVerbCombScore(wordPhrase);
//			System.out.println(wordPhrase+" ����DV���أ� "+scoreString);
			return scoreString;
		}else {
//			System.out.println(wordPhrase+" ����ṹ �Ȳ��� DV Ҳ���� DA");
			return score;
		}
	}
	
	
	
	/**
	 * ����  �̶ȴ������ݴʵ���ϣ� Ȼ�������÷�   DA
	 * @param wordPhrase
	 * @return
	 */
	public static double getDegreeAndAdjCombScore(String wordPhrase){
		double DAscore=0.0;
        double degreeScore=getDegreeScore(wordPhrase);
        double adjScore=getAdjScore(wordPhrase);
        //�̶ȴʵ÷�  ����  ���ݴʵ÷֡� ����Ҫע����һ�����Ϊ��
        if (degreeScore==0.0&&adjScore==0.0) {
			return 0.0;
		}else if (degreeScore==0 || adjScore==0) {
			return degreeScore==0?adjScore:degreeScore;
		}else {
			DAscore=degreeScore*adjScore;
		}
		return DAscore;
	}
	
	/**
	 * ����  �̶ȴ��붯�ʵ���ϣ� Ȼ�������÷�  DV
	 * @param wordPhrase
	 * @return
	 */
	public static double getDegreeAndVerbCombScore(String wordPhrase){
		wordPhrase=wordPhrase.trim();
		double DVscore=0.0;
        double degreeScore=getDegreeScore(wordPhrase);
//        System.out.println("degreeScore for "+wordPhrase+": "+degreeScore);
        double verbScore=getVerbScore(wordPhrase);
//        System.out.println("verbScore for "+wordPhrase+": "+verbScore);
        //�̶ȴʵ÷�  ����  ���ʵ÷�
      //�̶ȴʵ÷�  ����  ���ݴʵ÷֡� ����Ҫע����һ�����Ϊ��
        if (degreeScore==0.0 && verbScore==0.0) {
			return 0.0;
		}else if (degreeScore==0 || verbScore==0) {
			return degreeScore==0?verbScore:degreeScore;
		}else {
			DVscore=degreeScore*verbScore;
		}
		return DVscore;
	}
	
	
	
	
	
	
	
	/**
	 * �жϴ���������   (/d)*(/a)+   ����(/d)*(/v)+    ����DA����DV
	 * @param wordPhrase
	 * @return
	 */
	public static String ifDAorDV(String wordPhrase){
            
		String DVRegex="([\\d]+_@[\u4e00-\u9fa5a-zA-Z0-9]*/d\\s)*([\\d]+_@[\u4e00-\u9fa5a-zA-Z0-9]*/v\\s*)+";
		String DARegex="([\\d]+_@[\u4e00-\u9fa5a-zA-Z0-9]*/d\\s)*([\\d]+_@[\u4e00-\u9fa5a-zA-Z0-9]*/a\\s*)+";
	   
		if (wordPhrase.matches(DVRegex)) {
			return "DV";
		} else if (wordPhrase.matches(DARegex)) {
			return "DA";
		}else {
			return "NO";
		}
		
	}
	
	
	
	
	/**
	 * ����һ�����飬 �����������ڶ���  ��   ���ʵ÷��ܺ͡�
	 * 1_@�ǳ�/d 2_@�ǳ�/d 3_@ϲ��/v 
	 * @param wordPhrase
	 */
	public static double getVerbScore(String wordPhrase){
			double verbScore=0.0;
			//ָ���ֵ�·��
			String verbDictPath="W:\\myproj\\sentimentalProj\\dict_All\\verb\\���渺�涯��_score.txt";
		//��ִ��飬 
			List<String> verbList=getSpecificWordVerbOrAdjOrDegr(wordPhrase,"/v");
//			System.out.println(verbList.size()+"--------------------------"+wordPhrase+"-----"+verbList.get(0));
			for (String verb:verbList) {
				double theVerbScore=retrieveAWordScore(verb,verbDictPath);
				//������ʵ÷�����������ӣ� �Ǹ��������. 0 ����ԣ�Ϊ���Ժ���ܻ����
				if (theVerbScore<0) {
					//��˵�ʱ�����ж� verbScore�ǲ���0�� �����0�ͳ���1���������verbScore
					if (verbScore==0.0) {
						verbScore=1.0*theVerbScore;
					}else {
						verbScore=verbScore*theVerbScore;
					}
				
				}else if (theVerbScore>0) {
					verbScore=verbScore+theVerbScore;
				}else {
					//�����0  ��ô����  ��ʱ�����д���
				}
			}
		return verbScore;
	}
	
	/**
	 * ����һ�����飬 ���������������ݴ�  ��  �÷��ܺ͡�
	 * @param wordPhrase
	 */
	public static double getAdjScore(String wordPhrase){
		double adjScore=0.0;
		//ָ���ֵ�·��
		String adjDictPath="W:\\myproj\\sentimentalProj\\dict_All\\adj\\���渺�����ݴ�_score.txt";
	//��ִ��飬 ��ȡ�ַ��������ݴʵ�list
		List<String> adjList=getSpecificWordVerbOrAdjOrDegr(wordPhrase,"/a");
//		System.out.println("���������� "+adjList.size());
		for (String adj:adjList) {
//			System.out.println(adj);
			double theAdjScore=retrieveAWordScore(adj,adjDictPath);
			if (theAdjScore!=0) {
				//���ݴʵ��ֽܷ������
				adjScore=adjScore+theAdjScore;
			}
		}
		return adjScore;
	}
	
	/**
	 * ����һ�����飬 �����������ڳ̶ȴ�  ��  �÷��ܺ͡�
	 * �̶ȴʵļ��㷽ʽ��  ÿ���̶ȴʵĵ÷���ӣ� ������һ���̶ȴ���  ������ �����ֳܷ������� 
	 * @param wordPhrase
	 */
	public static double getDegreeScore(String wordPhrase){
		double degreeScore=0.0;
		//ָ���ֵ�·��
		String degreeDictPath="W:\\myproj\\sentimentalProj\\dict_All\\degree\\���渺��̶ȼ������_score.txt";
		//��ִ��飬 ��ȡ�ַ��������ݴʵ�list
		List<String> degreeList=getSpecificWordVerbOrAdjOrDegr(wordPhrase,"/d");
//		System.out.println(degreeList.size());  //�鿴����˼����̶ȴ���
		for (int i = 0; i < degreeList.size(); i++) {
			double theDegreeScore=retrieveAWordScore(degreeList.get(i),degreeDictPath);
			if (theDegreeScore==0) {
				continue;
			}
			//�������һ���̶ȴ��Ǹ���ģ������ĵ÷�  ���� ����÷�  ���з񶨡� ͬʱ��Ҫ�������֮ǰ�÷���0���򷵻�-1
			if (i==(degreeList.size()-1)) {
				if (theDegreeScore<0) {//�����һ���ʸ���Ļ����� �ֽܷ������
					//���֮ǰ��Ҫ�ж� degreeScore�Ƿ����0�������0�������1
					if (degreeScore==0.0) {
						degreeScore=1.0*theDegreeScore;
					}else {
						degreeScore=degreeScore*theDegreeScore;
					}
				}else {
					degreeScore=degreeScore+theDegreeScore;
				}
			}else {//ֻҪ�������һ�����ǽ�����ӵķ�ʽ
				degreeScore=degreeScore+theDegreeScore;
			}
		}
		return degreeScore;
	}
	

	
	
	/**
	 * ��ȡ�ַ����е����ж��ʣ��������ݴʣ���ȡ�̶ȴ�
	 *  ����һ�����ж��ʣ��������ݴʣ����̶߳ȴʵļ���
	 *  �Ѷ�����ϵ��ַ������ҳ��������뵽�����У� Ȼ�󷵻ؼ��ϡ�
	 *  ���Ҳ�ֻ����ȡ/v  �ĵ��ʣ� ͬʱ����ȥ��  1_@
	 * @param word   ����1_@�ǳ�/d 2_@�ǳ�/d 3_@ϲ��/v 
	 * @param verbOrAdj    ����  /v  �������ζ�� ��ȡ���ж���
	 * @return
	 */
	public static List<String> getSpecificWordVerbOrAdjOrDegr(String wordPhrase,String verbOrAdj){
		List<String> wordList=new ArrayList<>();
		String regexVerb="([\\d]*_@[\u4e00-\u9fa5a-zA-Z0-9]+"+verbOrAdj+"\\s*)+";
		Pattern pattern=Pattern.compile(regexVerb);
		Matcher matcher=pattern.matcher(wordPhrase);
		while (matcher.find()) {
			//���տո���в��
			String[] wordlStrings=matcher.group().split("\\s+");
			for (String eachW:wordlStrings) {
				eachW=eachW.replaceAll("\\d+_@", "");
				eachW=eachW.replaceAll(verbOrAdj, "").trim();
				wordList.add(eachW);
			}
		}
		return wordList;
	}
	
	/**
	 * ����һ�����ʣ���˵�������ĸ�dictionary��Ѱ��������ʲ���ȡ����Ӧ�ĵ÷�
	 * @param aWord
	 * @param scoreDictPath
	 */
	public static double retrieveAWordScore(String aWord,String scoreDictPath){
//		System.out.println(aWord+" received");
		String encoding="UTF-8";
		try {
			File file=new File(scoreDictPath);
			InputStreamReader isReader=new InputStreamReader(new FileInputStream(file),encoding);
			BufferedReader bf=new BufferedReader(isReader);
			String eachLine="";
			while ((eachLine=bf.readLine())!=null) {
			//��ȡ��ÿһ�У� ��ÿһ�в��
				String[] temphere=eachLine.split("\\t");
				String thisWordString=temphere[0].trim();
				String scoreHere=temphere[1].trim();
				double sco=Double.parseDouble(scoreHere);
				if (aWord.equals(thisWordString)) {
					return sco;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	return 0.0;
	}
	
	
}
