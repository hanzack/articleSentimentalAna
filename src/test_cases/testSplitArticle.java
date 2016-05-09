package test_cases;

import org.junit.Test;

import articleToSentence.ArticleImpl;
import articleToSentence.Paragraph;
import articleToSentence.Sentence;
import articleToSentence.SubSentence;
import articleToSentence.splitArticle;
import fileHandle_From_Directory.ReadAndWriteFileFromDirectory;

public class testSplitArticle {

	
	/**
	 * 测试文章中每一段落的内容
	 */
	@Test  
	public void test_splitParagraph(){
		String pathString="W:\\zackjob\\hacker\\nlp_workspace_files\\temp\\一个文章.txt";
		String content=ReadAndWriteFileFromDirectory.readFileAndReturnContent(pathString);
		splitArticle.splitToParagraph(content);
	}
	
	/**
	 * 测试一段文章拆分成不同的句子。 打印每一句内容
	 */
	@Test
	public void test_splitSentence(){
		String paraCon="从大会的视频看，这款产品还属于实验室产品。一些可以识别的影像，是比较特定或者说比较典型的画面!如果面对纷繁复杂的大千世界，这款产品还需要更加强大的识别能力.因为，一位盲人所面对的不仅仅是可爱的小伙子、漂亮的姑娘和可口的美食?识别一些突发的未知情况，为盲人排忧解难，这才是根本?从目前看，Seeing AI的实战目标还不是很丰富，实际效果还不是很强。";
		splitArticle.splitToSentence(paraCon);
	}
	
	
	/**
	 * 测试一个句子拆分成更小的子句打印出来。
	 */
	@Test
	public void test_splitToSubSentence(){
		String parString="从大会的视频看，这款产品，还属于实验室产品。";
		splitArticle.splitToSubSentence(parString);
		
	}
	
	
	
	/**
	 * 传入一篇文章，使得这篇文章进行结构化，存成  层层递进的对象。
	 */
	@Test
	public void test_(){
		String pathString="W:\\zackjob\\hacker\\nlp_workspace_files\\temp\\一个文章.txt";
		String content=ReadAndWriteFileFromDirectory.readFileAndReturnContent(pathString);
		ArticleImpl art=splitArticle.splitArticleReturnArticle(content,"");
		// 注意这里也可以sent.getSentenStr()   直接获得句子的内容
		for (Paragraph para:art.getParaList()) {//获取每个段落
			for (Sentence sent:para.getSenList()) {//获取每一句话
				for (SubSentence subSen:sent.getSubSenList()) {//获取每一个子句
					
					System.out.println("段落： "+para.getParaNumber());
					System.out.println("句子："+sent.getSentenceNum());
					System.out.println("子句："+subSen.getSubSenNum()+"---"+subSen.getSubContent());
				}
				
			}
			System.out.println("-------------------------------------");
		}
		
		
	}
	
	
	
}
