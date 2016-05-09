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
 * 词典的存储地址： W:\myproj\sentimentalProj\dict_All
 * 
 * 这个类对一句话，一个单词， 或者一篇文章进行  倾向性得分的计算 
 * 这个类还可以计算 动词得分， 形容词得分，程度词得分等等。
 */
public class CalculateSenScore {

	
	/**
	 * 传入一个map， 返回这个map里的每一项得分之和。
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
	 * @param wordScoreMap:  这是一个map， 里面 key 是单词或者词组， value是这个词组的倾向性得分.
	 * 例如：    1_@很/d 2_@是/v   						2.0
	 *       2_@不/d 3_@喜欢/v						-3
	 *       29_@特别/d 30_@特别/d 31_@丑/a				-4.0
	 *       
	 * @return   返回一个整合后的map，  
	 * 例如      上面，  很是不喜欢， 经过调整，变成
	 *      1_@很/d 2_@是/v 2_@不/d 3_@喜欢/v						-6.0
	 *       因为只考虑  很是 没有意义，当他遇到 不喜欢  的时候，就可以计算总得分了。我们认为它俩组合是一个词。
	 */
	public static Map<String, Double> FurtherCalculateScoreForMap(Map<String, Double> wordScoreMap){
//		System.out.println("在FurtherCalculateScoreForMap方法中：");
		Set<String> wordSet=wordScoreMap.keySet();
		// 把set转换成list
		ArrayList<String> wordList=new ArrayList<>(wordSet);
		for (int i = 0; i < wordList.size(); i++) {
			System.out.println(wordList.get(i));
			if (isNextStringANeighbor(wordScoreMap, i)) {//判断这个字符串是不是和下一个字符串是邻居
				//如果是邻居则判断这个邻居是dv还是da
				String theFirString=wordList.get(i);
				String theSecondString=wordList.get(i+1);
				if ("DV".equals(ifDAorDV(wordList.get(i+1)))) {
					wordScoreMap=dvCalculatedForAll(wordScoreMap,theFirString,theSecondString,i);
					//如果已经计算了得分就不用重复计算了
					i=count;
				}else if ("DA".equals(ifDAorDV(wordList.get(i+1)))) {
					wordScoreMap=daCalculatedForAll(wordScoreMap,theFirString,theSecondString,i);
				}else{
					
				}
				
			}else {//如果不是，这个字符串的则得分计算完成，
				continue;
			}
		}
		return wordScoreMap;
	}
	
	
	/**
	 * 计算DV   前者乘以后者，后者，并且把分数赋值给后者，前者修改为0
	 */
	private static int count=0;
	public static Map<String, Double> dvCalculatedForAll(Map<String, Double> map,String first,String second,int i){
//		System.out.println("在dvCalculatedForAll方法中： "+i+" "+first+"+"+second);
		double theScore=-1.0;
		double firstScore=map.get(first);
		double secondScore=map.get(second);
		if (firstScore==0.0||secondScore==0.0) {
			theScore=firstScore>=secondScore?firstScore:secondScore;
		}else {
			//若两者都不为0，则相乘。
			theScore=firstScore*secondScore;
		}
		 map.put(first, 0.0);
		 map.put(second, theScore);
		 count=i+1;
		 //然后进行递归判断。 这个字符串与下一个字符串是否仍然是邻居。 如果是邻居， 就判断是DA还是DV，调用相对应的方法。
		 if (isNextStringANeighbor(map,i+1)) {
			 int newI=i+1;
			   Set<String> wordSet=map.keySet();
				// 把set转换成list
				ArrayList<String> wordList=new ArrayList<>(wordSet);
			    String firstNext=second;
			    String secondNext=wordList.get(i+2);
			    if ("DV".equals(ifDAorDV(secondNext))) {
//			    System.out.println("dvCalculatedForAll的内层递归循环: "+firstNext+": "+secondNext+"i+1:"+newI);
			    	map=dvCalculatedForAll(map, firstNext, secondNext, i+1);
				}else if ("DA".equals(ifDAorDV(secondNext))) {
					map=daCalculatedForAll(map, firstNext, secondNext, i+1);
				}
			    count=i+2;
	   	}
		 return map;
	}
	
	
	/**
	 * 本方法的方法体和上面是一样的，但是为了方便以后修改， 所以分开写成两个方法
	 * @param map
	 * @param first
	 * @param second
	 * @param i
	 * @return
	 */
	public static Map<String, Double> daCalculatedForAll(Map<String, Double> map,String first,String second,int i){
//		System.out.println("在daCalculatedForAll方法中： "+i+" "+first+"+"+second);
		double theScore=-1.0;
		double firstScore=map.get(first);
		double secondScore=map.get(second);
		if (firstScore==0.0||secondScore==0.0) {
			theScore=firstScore>=secondScore?firstScore:secondScore;
		}else {
			//若两者都不为0，则相乘。
			theScore=firstScore*secondScore;
		}
		 map.put(first, 0.0);
		 map.put(second, theScore);
		 count=i+1;
		 //然后进行递归判断。 这个字符串与下一个字符串是否仍然是邻居。 如果是邻居， 就判断是DA还是DV，调用相对应的方法。
		 if (isNextStringANeighbor(map,i+1)) {
			   int newI=i+1;
			   Set<String> wordSet=map.keySet();
				// 把set转换成list
				ArrayList<String> wordList=new ArrayList<>(wordSet);
			    String firstNext=second;
			    String secondNext=wordList.get(i+2);
			    if ("DV".equals(ifDAorDV(secondNext))) {
//			    System.out.println("dvCalculatedForAll的内层递归循环: "+firstNext+": "+secondNext+"i+1:"+newI);
			    	map=dvCalculatedForAll(map, firstNext, secondNext, i+1);
				}else if ("DA".equals(ifDAorDV(secondNext))) {
					map=daCalculatedForAll(map, firstNext, secondNext, i+1);
				}
			    count=i+2;
	   	}
		 return map;
	}
	
	
	/**
	 * 传入一个map，和一个下标判断，map中这个下标和下一个下标是否邻居
	 * @param map
	 * @param i
	 * @return
	 */
	public static boolean isNextStringANeighbor(Map<String, Double> map, int i){
		
		Set<String> wordSet=map.keySet();
		// 把set转换成list
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
	 * 传入一个句子的字符串，或者只要是字符串就行， 然后传入一个规则文件， 本方法会先对字符串进行分词和词性标注
	 * 规则文件是一个正则表达式，它用来提取出句子中的特定组合词语，   提取方式将根据自行标注进行。
	 * 这些取出的词语，存入到map中，并且会计算每个词组的倾向性得分。 如果不传入规则文件则使用默认的规则文件
	 * 
	 * 举例， 传入 ，“我很喜欢你”   本方法会对他进行分词和词性标注： 变成： 0_@我/r 1_@很/d 2_@喜欢/v 3_@你/r  其中1_@是标注之后经过某一方法进行处理的
	 * 目的是  记录  这个字在句子中什么顺序。    获取这个分词结果后，规则文件会提取所有    /d /v 的组合，  获得 1_@很/d 2_@喜欢/v  然后加入map中
	 * 然后计算   1_@很/d 2_@喜欢/v   的得分， 存入到map 的value中去。
	 * @param sentStr   ： 一个句子 例如：  “我很喜欢你”  
	 * @param regexFilePath  ： "W:\\myproj\\sentimentalProj\\ruleFile_2\\rule_extractWord.txt" 
	 * @return
	 */
	public static Map<String, Double> scoreSentenceSubPhreaseIntoMap(String sentStr,String regexFilePath){
		//先获取一句话的拆分
//		String sentence="我很喜欢欧几里得，我非常非常非常不恨他。你是一个极度非常漂亮的人。其实吧我觉得他长得特别特别丑。不过呢整体上我还是相当喜欢他的。我只有一点不喜欢他";
		String sentence=sentStr;
		String fenciString=AnalyzerArticle.segAndPostagSentence(sentence);
//		System.out.println("原始分词： "+fenciString);
		//通过规则文件提取属于，程度词形容词的组合，或者程度词与动词的组合
		if (String_handle_Zack_Util.isEmpty(regexFilePath)) {
			regexFilePath="W:\\myproj\\sentimentalProj\\ruleFile_2\\rule_extractWord.txt";
		}
		List<String> phraseList=AnalyzerArticle.getSegWordFromMatchRuleReturnList(regexFilePath, fenciString);
		//然后存入map中
		Map<String, Double> phraseMap=AnalyzerArticle.SortSegWordIntoMap(phraseList);
		
		// 判断每一个词组是  DA 类型还是 DV类型
		Set<String> keysS=phraseMap.keySet();
		for (String string : keysS) {
			double scorePh=CalculateSenScore.getScoreForDVOrDA(string);
//			System.out.println(string+"  得分： "+scorePh);
			phraseMap.put(string, scorePh);
		}
		
		return phraseMap;
	}
	
	
	
	
	/**
	 * 计算一个词组的整体得分， 例如     /d /d /v /v 这种词组，计算总得分。
	 * 如果传入  0_@不/d 1_@喜欢/v   那么就是计算   /d 与/v 的组合得分进行返回。
	 * @param wordPhrase       符合某一规则的分词字符串
	 * @return
	 */
	public static double getScoreForDVOrDA(String wordPhrase){
		double score=0.0;
		if ("DA".equals(ifDAorDV(wordPhrase))) {
			double scoreString=getDegreeAndAdjCombScore(wordPhrase);
//			System.out.println(wordPhrase+" 计算DA返回： "+scoreString);
			return scoreString;
		}else if("DV".equals(ifDAorDV(wordPhrase))){
			double scoreString=getDegreeAndVerbCombScore(wordPhrase);
//			System.out.println(wordPhrase+" 计算DV返回： "+scoreString);
			return scoreString;
		}else {
//			System.out.println(wordPhrase+" 这个结构 既不是 DV 也不是 DA");
			return score;
		}
	}
	
	
	
	/**
	 * 传入  程度词与形容词的组合， 然后计算出得分   DA
	 * @param wordPhrase
	 * @return
	 */
	public static double getDegreeAndAdjCombScore(String wordPhrase){
		double DAscore=0.0;
        double degreeScore=getDegreeScore(wordPhrase);
        double adjScore=getAdjScore(wordPhrase);
        //程度词得分  乘以  形容词得分。 但是要注意有一方相乘为零
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
	 * 传入  程度词与动词的组合， 然后计算出得分  DV
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
        //程度词得分  乘以  动词得分
      //程度词得分  乘以  形容词得分。 但是要注意有一方相乘为零
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
	 * 判断传进来的是   (/d)*(/a)+   还是(/d)*(/v)+    返回DA或者DV
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
	 * 传入一个词组， 计算里面属于动词  的   动词得分总和。
	 * 1_@非常/d 2_@非常/d 3_@喜欢/v 
	 * @param wordPhrase
	 */
	public static double getVerbScore(String wordPhrase){
			double verbScore=0.0;
			//指定字典路径
			String verbDictPath="W:\\myproj\\sentimentalProj\\dict_All\\verb\\正面负面动词_score.txt";
		//拆分词组， 
			List<String> verbList=getSpecificWordVerbOrAdjOrDegr(wordPhrase,"/v");
//			System.out.println(verbList.size()+"--------------------------"+wordPhrase+"-----"+verbList.get(0));
			for (String verb:verbList) {
				double theVerbScore=retrieveAWordScore(verb,verbDictPath);
				//如果动词得分是正数就相加， 是负数就相乘. 0 则忽略，为了以后可能会更改
				if (theVerbScore<0) {
					//相乘的时候先判断 verbScore是不是0， 如果是0就乘以1，否则就用verbScore
					if (verbScore==0.0) {
						verbScore=1.0*theVerbScore;
					}else {
						verbScore=verbScore*theVerbScore;
					}
				
				}else if (theVerbScore>0) {
					verbScore=verbScore+theVerbScore;
				}else {
					//如果是0  怎么处理？  暂时不进行处理。
				}
			}
		return verbScore;
	}
	
	/**
	 * 传入一个词组， 计算里面属于形容词  的  得分总和。
	 * @param wordPhrase
	 */
	public static double getAdjScore(String wordPhrase){
		double adjScore=0.0;
		//指定字典路径
		String adjDictPath="W:\\myproj\\sentimentalProj\\dict_All\\adj\\正面负面形容词_score.txt";
	//拆分词组， 获取字符串里形容词的list
		List<String> adjList=getSpecificWordVerbOrAdjOrDegr(wordPhrase,"/a");
//		System.out.println("词语总数： "+adjList.size());
		for (String adj:adjList) {
//			System.out.println(adj);
			double theAdjScore=retrieveAWordScore(adj,adjDictPath);
			if (theAdjScore!=0) {
				//形容词的总分进行相加
				adjScore=adjScore+theAdjScore;
			}
		}
		return adjScore;
	}
	
	/**
	 * 传入一个词组， 计算里面属于程度词  的  得分总和。
	 * 程度词的计算方式：  每个程度词的得分相加， 如果最后一个程度词是  负数， 则用总分乘以它， 
	 * @param wordPhrase
	 */
	public static double getDegreeScore(String wordPhrase){
		double degreeScore=0.0;
		//指定字典路径
		String degreeDictPath="W:\\myproj\\sentimentalProj\\dict_All\\degree\\正面负面程度级别词语_score.txt";
		//拆分词组， 获取字符串里形容词的list
		List<String> degreeList=getSpecificWordVerbOrAdjOrDegr(wordPhrase,"/d");
//		System.out.println(degreeList.size());  //查看获得了几个程度词语
		for (int i = 0; i < degreeList.size(); i++) {
			double theDegreeScore=retrieveAWordScore(degreeList.get(i),degreeDictPath);
			if (theDegreeScore==0) {
				continue;
			}
			//如果最后的一个程度词是负面的，则计算的得分  乘以 负面得分  进行否定。 同时还要考虑如果之前得分是0，则返回-1
			if (i==(degreeList.size()-1)) {
				if (theDegreeScore<0) {//若最后一个词负面的话，则 总分进行相乘
					//相乘之前还要判断 degreeScore是否等于0，如果是0，则乘以1
					if (degreeScore==0.0) {
						degreeScore=1.0*theDegreeScore;
					}else {
						degreeScore=degreeScore*theDegreeScore;
					}
				}else {
					degreeScore=degreeScore+theDegreeScore;
				}
			}else {//只要不是最后一个都是进行相加的方式
				degreeScore=degreeScore+theDegreeScore;
			}
		}
		return degreeScore;
	}
	

	
	
	/**
	 * 获取字符串中的所有动词，或者形容词，获取程度词
	 *  返回一个所有动词，或者形容词，或者程度词的集合
	 *  把动词组合的字符串都找出来，放入到数组中， 然后返回集合。
	 *  并且不只是提取/v  的单词， 同时还会去掉  1_@
	 * @param word   例如1_@非常/d 2_@非常/d 3_@喜欢/v 
	 * @param verbOrAdj    例如  /v  这个就意味着 获取所有动词
	 * @return
	 */
	public static List<String> getSpecificWordVerbOrAdjOrDegr(String wordPhrase,String verbOrAdj){
		List<String> wordList=new ArrayList<>();
		String regexVerb="([\\d]*_@[\u4e00-\u9fa5a-zA-Z0-9]+"+verbOrAdj+"\\s*)+";
		Pattern pattern=Pattern.compile(regexVerb);
		Matcher matcher=pattern.matcher(wordPhrase);
		while (matcher.find()) {
			//按照空格进行拆分
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
	 * 传入一个单词，并说明，从哪个dictionary里寻找这个单词并获取它对应的得分
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
			//获取了每一行， 对每一行拆分
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
