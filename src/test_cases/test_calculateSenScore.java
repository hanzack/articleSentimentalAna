package test_cases;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import segSentence.AnalyzerArticle;
import calculateSentenceScore.CalculateSenScore;

public class test_calculateSenScore {
	/**
	 * 传入字符串， 提取里面的动词
	 */
	@Test
	public void test_getSpecificWord(){
		String wordPhrase="1_@非常/d 2_@非常/d 3_@喜欢/v 4_@憎恨/v 5_@过去/v";
		List<String> wordList=CalculateSenScore.getSpecificWordVerbOrAdjOrDegr(wordPhrase,"/v");
		for (String verbWord:wordList) {
			System.out.println(verbWord);
		}
	}
	
	
	/**
	 * 传入字符串， 提取里面的程度词
	 */
	@Test
	public void test_getSpecificWord1(){
		String wordPhrase="1_@非常/d 2_@非常/d 3_@喜欢/v 4_@憎恨/v 5_@过去/v";
		List<String> wordList=CalculateSenScore.getSpecificWordVerbOrAdjOrDegr(wordPhrase,"/d");
		for (String verbWord:wordList) {
			System.out.println(verbWord);
		}
	}
	
	
	/**
	 * 测试一个词组在一个字典里面的得分
	 */
	@Test
	public void test_retrieveScore(){
		String word="欣赏";
		String dictPathString="W:\\myproj\\sentimentalProj\\dict_All\\verb\\正面负面动词_score.txt";
		double score=CalculateSenScore.retrieveAWordScore(word, dictPathString);
		System.out.println(word+"---"+score);
	}
	
	
	/**
	 * 测试一个词组里的动词组合的总得分
	 */
	@Test
	public void Test_getVerbScore(){
		String verbsString="1_@非常/d 2_@非常/d 3_@喜欢/v 4_@喜欢/v 5_@恨/v";
		double score=CalculateSenScore.getVerbScore(verbsString);
		System.out.println(score);
	}
	
	
	/**
	 * 测试一个词组里的形容词组合的总得分
	 */
	@Test
	public void Test_getAdjScore(){
		// 三个正数一个负数， 得分应该是2
		String verbsString="0_@他/r 1_@非常/d 2_@美丽/a 3_@大方/a 4_@可爱/a 5_@傻/a";
		double score=CalculateSenScore.getAdjScore(verbsString);
		System.out.println(score);
	}
	
	
	
	/**
	 * 测试一个词组里的程度词组合的总得分
	 */
	@Test
	public void Test_getDegreeScore(){
		String abcString="不喜欢他。";
		String reString=AnalyzerArticle.segAndPostagSentence(abcString);
		System.out.println(reString);
		// 三个正数一个负数， 得分应该是2
//		String verbsString="0_@他/r 1_@非常/d 2_@美丽/a 3_@大方/a 4_@可爱/a 5_@傻/a";
		double score=CalculateSenScore.getDegreeScore(reString);
		System.out.println(score);
	}
	
	
	/**
	 *  判断是 程度词加形容词的组合， 还是程度词加动词的组合。
	 */
	@Test
	public void test_ifDAorDV(){
		String wordPhrase="1_@喜欢/a 1_@喜欢/a";
		String abcString=CalculateSenScore.ifDAorDV(wordPhrase);
		System.out.println(abcString);
	}
	
	
	/**
	 * 把一句话进行分词，按照规则提取词组，然后存入排序后的map中， 然后判断出每一个词组是  程度词加形容词组合，还是程度词加动词组合。
	 */
	@Test
	public void testMore_test_ifDAorDV(){
		//先获取一句话的拆分
		String sentence="我很喜欢他，我非常非常非常不恨他。你是一个极度非常漂亮的人";
		String fenciString=AnalyzerArticle.segAndPostagSentence(sentence);
		//通过规则文件提取属于，程度词形容词的组合，或者程度词与动词的组合
		String regexFile="W:\\myproj\\sentimentalProj\\ruleFile_2\\rule_extractWord.txt";
		List<String> phraseList=AnalyzerArticle.getSegWordFromMatchRuleReturnList(regexFile, fenciString);
		//然后存入map中
		Map<String, Double> phraseMap=AnalyzerArticle.SortSegWordIntoMap(phraseList);
		
		// 判断每一个词组是  DA 类型还是 DV类型
		Set<String> keysS=phraseMap.keySet();
		for (String string : keysS) {
			String flag=CalculateSenScore.ifDAorDV(string);
			System.out.println(string+" : "+flag);
		}
		
		
	}
	
	
	
	
	
	/**
	 * 把一句话进行分词，按照规则提取词组，然后存入排序后的map中，然后获取每一个key   的得分，并写入value中。
	 */
	@Test
	public void test_getScoreForDVOrDA(){
		//先获取一句话的拆分
		String sentence="我很喜欢欧几里得，我非常非常非常不恨他。你是一个极度非常漂亮的人。其实吧我觉得他长得特别特别丑。不过呢整体上我还是相当喜欢他的。我只有一点不喜欢他";
//		String sentence="我非常非常非常不恨他";
//		String sentence="我很喜欢他，我非常非常非常不恨他。";
		String fenciString=AnalyzerArticle.segAndPostagSentence(sentence);
		System.out.println("原始分词： "+fenciString);
		//通过规则文件提取属于，程度词形容词的组合，或者程度词与动词的组合
		String regexFile="W:\\myproj\\sentimentalProj\\ruleFile_2\\rule_extractWord.txt";
		List<String> phraseList=AnalyzerArticle.getSegWordFromMatchRuleReturnList(regexFile, fenciString);
		//然后存入map中
		Map<String, Double> phraseMap=AnalyzerArticle.SortSegWordIntoMap(phraseList);
		
		// 判断每一个词组是  DA 类型还是 DV类型
		Set<String> keysS=phraseMap.keySet();
		for (String string : keysS) {
			double scorePh=CalculateSenScore.getScoreForDVOrDA(string);
			System.out.println(string+"  得分： "+scorePh);
		}
		
		
	}
	
	
	
	/**
	 * 给定一句话， 获取一个map， map里面的key是，一些经过规则提取后的词组，value， 是这个词组的倾向性得分 
	 */
	@Test
	public void test_scoreSentenceSubPhreaseIntoMap(){
		String sentence="我很喜欢欧几里得，我不非常非常非常恨他。你是一个极度非常漂亮的人。其实吧我觉得他长得特别特别丑。不过呢整体上我还是相当喜欢他的。我只有一点不喜欢他";
		String Regefile="W:\\myproj\\sentimentalProj\\ruleFile_2\\rule_extractWord.txt";
		Map<String, Double> phraseMap=CalculateSenScore.scoreSentenceSubPhreaseIntoMap(sentence,Regefile);
		// 判断每一个词组是  DA 类型还是 DV类型
		System.out.println();
		System.out.println("-------------修改以后存入map-------------------");
		Set<String> keysS=phraseMap.keySet();
				for (String string : keysS) {
					System.out.println(string+"  得分： "+phraseMap.get(string));
				}
	}
	
	
	
	
	
	
	
	/**
	 * 临时测试   FurtherCalculateScoreForMap 方法
	 */
	@Test
	public void test_FurtherCalculateScoreForMap(){
		//准备好一句话
//		String sentence="没有喜欢我很喜欢欧几里得，我不非常非常非常恨他。你是一个极度非常漂亮的人。其实吧我觉得他长得特别特别丑。不过呢整体上我还是相当喜欢他的。我只有一点不喜欢他";
		String sentence="他是一个很厉害的人";
		//准备好一个拆分的规则文件， 该文件是提取  所有   程度词+形容词的组合， 以及  程度词+动词的组合 。
		String Regefile="W:\\myproj\\sentimentalProj\\ruleFile_2\\rule_extractWord.txt";
		//  然后    根据规则文件和一个句子， 进行分词，把提取出来的分词放入到map中，value  是符合规则的分词， key是这个分词的得分，初始值为0.0
		Map<String, Double> phraseMap=CalculateSenScore.scoreSentenceSubPhreaseIntoMap(sentence,Regefile);
		
		phraseMap=CalculateSenScore.FurtherCalculateScoreForMap(phraseMap);
		Set<String> key1=phraseMap.keySet();
//		for (String key:key1) {
//			//这里输出每个词 以及  对应的分数。
//			System.out.println(key+" : "+phraseMap.get(key));
//		}
		//获取map的总分
		double score=CalculateSenScore.CalculateMapTotalScore(phraseMap);
		System.out.println("总分是： "+score);
	}
	
	
	
	
	
}












