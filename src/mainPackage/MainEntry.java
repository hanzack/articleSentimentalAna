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
		String fileString="W:\\myproj\\sentimentalProj\\articleFile_1\\情感文章.txt";
		String fileRegex="W:\\myproj\\sentimentalProj\\ruleFile_2\\rule_extractWord.txt";
		analyzeArticleSentence(fileString,fileRegex);
	}
	
	
	/**传入文章， 传入 规则文件的路径。 获取这篇文章的得分， 并且返回这篇文章这个对象。
	 * 给定一个文章， 对文章的每一句话进行分词处理。这个方法会把一篇文章封装到对象元素里面
	 * @param filePath 文章的路径：W:\zackjob\hacker\nlp_workspace_files\temp\情感文章.txt
	 */
	public static void analyzeArticleSentence(String filePath, String regexfile){
		//文章路径
		String pathString=filePath;
		ArticleImpl articleObj=splitArticle.getArticleObject(pathString);
		//整篇文章的得分
		double articleScore=0.0;
		//对每句话进行分词和词性标注处理
		for (Paragraph para:articleObj.getParaList()) {//获取每个段落
		    double paraScore=0.0;
			for (Sentence sent:para.getSenList()) {//获取每一句话
				double senScore=0.0;
				for (SubSentence subSen:sent.getSubSenList()) {//获取每一个子句
//					System.out.println("子句："+subSen.getSubSenNum()+"---"+subSen.getSubContent());
//					String segPostResultString=AnalyzerArticle.segAndPostagSentence(subSen.getSubContent());
					//获取了这   子句 的得分
				  double subSenScore=calculateASentence(subSen.getSubContent(),regexfile);
				  subSen.setSubSenScore(subSenScore);
				  senScore=senScore+subSenScore;
				}
				//设置这句话的得分
				sent.setSentScore(senScore);
				paraScore=paraScore+senScore;
			}
			//设置这短话的得分
			para.setParaScore(paraScore);
			articleScore=articleScore+paraScore;
			System.out.println();
			System.out.println("本段-----------------------------------------------------------==: "+paraScore);
		}
		articleObj.setArtScore(articleScore);
		System.out.println();
		System.out.println("整篇文章的得分------------------------------------------------------------==: "+articleScore);
	}
	
	
	
	public static double calculateASentence(String sentence,String Regefile){
		//准备好一句话
//		String sentence="没有喜欢我很喜欢欧几里得，我不非常非常非常恨他。你是一个极度非常漂亮的人。其实吧我觉得他长得特别特别丑。不过呢整体上我还是相当喜欢他的。我只有一点不喜欢他";
//		String sentence="我很喜欢很爱很欣赏她而且他非常非常漂亮非常喜欢";
		//准备好一个拆分的规则文件， 该文件是提取  所有   程度词+形容词的组合， 以及  程度词+动词的组合 。
//		String Regefile="W:\\myproj\\sentimentalProj\\ruleFile_2\\rule_extractWord.txt";
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
		System.out.println(sentence+" ---的总分是： "+score);
		return score;
	}
	

	
	/**
	 * 传入一句话和一个 规则文件，  获取这句话的得分。存入map， 也会获得这句话的总得分
	 */
	@Test
	public void test_CalculateSenScore(){
		//准备好一句话
		String sentence="没有喜欢我很喜欢欧几里得，我不非常非常非常恨他。你是一个极度非常漂亮的人。其实吧我觉得他长得特别特别丑。不过呢整体上我还是相当喜欢他的。我只有一点不喜欢他";
//		String sentence="我很喜欢很爱很欣赏她而且他非常非常漂亮非常喜欢";
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
