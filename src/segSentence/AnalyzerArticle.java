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
	 * 给定一个文章， 对文章的每一句话进行分词处理。这个方法会把一篇文章封装到对象元素里面
	 * @param filePath 文章的路径：W:\zackjob\hacker\nlp_workspace_files\temp\情感文章.txt
	 */
	public static void analyzeArticleSentence(String filePath){
		//文章路径
		String pathString=filePath;
		ArticleImpl articleObj=splitArticle.getArticleObject(pathString);
		//对每句话进行分词和词性标注处理
		for (Paragraph para:articleObj.getParaList()) {//获取每个段落
			for (Sentence sent:para.getSenList()) {//获取每一句话
				for (SubSentence subSen:sent.getSubSenList()) {//获取每一个子句
//					System.out.println("段落： "+para.getParaNumber());
//					System.out.println("句子："+sent.getSentenceNum());
//					System.out.println("子句："+subSen.getSubSenNum()+"---"+subSen.getSubContent());
					String segPostResultString=segAndPostagSentence(subSen.getSubContent());
					
				}
				
			}
			System.out.println("-------------------------------------");
			System.out.println();
		}
	}
	
	
	/**
	 * 对一句子句进行词性标注和分词。 并且返回分词和词性标注后的字符串
	 * @param Subsent
	 */
	public static String segAndPostagSentence(String Subsent){
		
		String posString="";
			posString=splitWordByIctclas.getSplitWordsByICTCLASWithUserDictWithNum(Subsent, 1);
		return posString;
	}
	
	
	/**
	 * 根据规则文件提取符合的词语。
	 *   传入  一个正则表达式的文件，  传入传入一个经过词性标注和分词后的文章或者句子， 提取出符合正则表达式的所有词， 存入list里面。 注意存入顺序不一定是正序的。
	 * @param regexFile
	 * @param content   传入的俄式经过分词后的字符串。
	 * @return
	 */
	public static List<String> getSegWordFromMatchRuleReturnList(String regexFile,String content){
		List<String> resultList=regex_All_util.getContentByRegexFileReturnList(regexFile, content);
		return resultList;
	}
	
	
	
	/**
	 * 获取map里面是符合程度词加形容词的组合， 或者是程度词加动词的组合。 并且按照这些词语在句子中的先后顺序进行了排序。
	 * map=(0_@不/d 1_@喜欢/v, 0.0)    key 是一个/d/v   或者是  /d/a  的组合，   value 是这个组合的得分
	 * 对按照规则获取的词语进行排序放入到map中去。map的 double类型是这个词语的得分。初始得分为0
	 * 之前获得list<String> 里面的word组合， 是以数字字符串开头的  1_@美丽    其中1表明这个词是第几个词。下面的方法是
	 * 最先出现的词组都往前排序。
	 * @param wordList  通过 getSegWordFromMatchRule 这个方法可以获取list
	 * @return
	 * 一个map就相当于一个句子， 他包含了里面的所有程度词与形容词， 或者程度词与动词的组合
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
