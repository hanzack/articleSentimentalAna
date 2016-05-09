package articleToSentence;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import fileHandle_From_Directory.ReadAndWriteFileFromDirectory;
import Primary_data_type_zack_util.String_handle_Zack_Util;


//当你传入一篇文章的时候 ，就自动建立一个文章对象， 文章又会建立段落的对象，段落又会建立句子的对象。
	//句子又会建立子句的对象。 每个类都有一个list对象用来存储  儿子对象的集合。
	
//传入一篇文章，返回这篇文章的对象， 文章对象里，有一个list存放着 段落的对象， 每个段落的对象里，存放着这个段落的句子的对象
//每个句子的对象又存放着一个list里面是 子句的对象（子句是一个句子按照逗号拆分后的结果）

//注意这里也可以sent.getSentenStr()   直接获得句子的内容， 而不用获得  一个句子的子句（对句子进行逗号进行拆分）
public class splitArticle {
	
	
	public static ArticleImpl getArticleObject(String articlePath){
//		String pathString="W:\\zackjob\\hacker\\nlp_workspace_files\\temp\\一个文章.txt";
		String fileName=ReadAndWriteFileFromDirectory.getFileNameFromFilePath(articlePath);
		String pathString=articlePath;
		String content=ReadAndWriteFileFromDirectory.readFileAndReturnContent(pathString);
		ArticleImpl art=splitArticle.splitArticleReturnArticle(content,fileName);
		return art;
	}
	/**
	 * 传入一篇文章，使得这篇文章进行结构化，存成  层层递进的对象。
	 */
	@Test
	public void test_(){
		String pathString="W:\\zackjob\\hacker\\nlp_workspace_files\\temp\\一个文章.txt";
		String content=ReadAndWriteFileFromDirectory.readFileAndReturnContent(pathString);
		ArticleImpl art=splitArticle.splitArticleReturnArticle(content,"一个文章");
		
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
   //当你传入一篇文章的时候 ，就自动建立一个文章对象， 文章又会建立段落的对象，段落又会建立句子的对象。
	//句子又会建立子句的对象。 每个类都有一个list对象用来存储  儿子对象的集合。
	
	/**
	 * 获取一篇文章，对文章进行拆分。得到一个文章的对象。
	 * @param articleStr
	 * @param art
	 */
	public static ArticleImpl splitArticleReturnArticle(String articleStr,String articleName){
		ArticleImpl article=new ArticleImpl();
		String titleString=articleName;
		//设置文章的标题
		article.setArticleTitle(titleString);
		List<Paragraph> paraList=splitToParagraph(articleStr);
		//设置文章的段落集合
		article.setParaList(paraList);
		return article;
	}
	
	/**
	 * 获取一篇文章，返回一个list，里面存储着不同的段落的对象。 对段落进行封装
	 * @param articleStr
	 * @return
	 */
	public static List<Paragraph> splitToParagraph(String articleStr){
		List<Paragraph> paraList=new ArrayList<>();
		//接受了文章以后，按照"(\r\n)+"的多空行进行文章的拆分。对文章进行拆分
		String []contStrings=articleStr.split("(\r\n)+");
		//打印出文章的各个段落
		for (int i = 0; i < contStrings.length; i++) {
//			System.out.println(i+"---"+contStrings[i]);
			//然后对每一段进行拆分
			String paraTemp=contStrings[i];//获取每一段的内容
			List<Sentence> sentList=splitToSentence(paraTemp);
			Paragraph aParagraph=new Paragraph();
			aParagraph.setSenList(sentList);//设置这个段落里面包含的句子的list
			aParagraph.setParaNumber(i);  //记录下来这是文章的第几段落
			//然后把这一段加入到文章中去。
			paraList.add(aParagraph);  
		}
		return paraList;
	}
	
	
	/**
	 * 把一个段落拆分成句子，封装到对象，然后存入list进行返回。
	 * @param paraContent
	 * @return
	 */
	public static List<Sentence> splitToSentence(String paraContent){
		List<Sentence> sentList=new ArrayList<>();
		//对段落按照句子进行拆分
//		String []contStrings=paraContent.split("[.]|[。]|[;]|[?]|[!]");
		String []contStrings=String_handle_Zack_Util.splitReserveMark(paraContent, "[.]|[。]|[;]|[?]|[!]|[；]");
		for (int i = 0; i < contStrings.length; i++) {
//			System.out.println(contStrings[i]);
			String sentString=contStrings[i];//获得了每一句
//			然后进行逗号的拆分
			Sentence sentence=new Sentence();//新建句子对象
			sentence.setSentenceNum(i);
			sentence.setSentenStr(sentString);// 把句子内容存在对象的senten属性
			// 对句子的内容进行拆分
			List<SubSentence> subsentList=splitToSubSentence(sentString);
			sentence.setSubSenList(subsentList);
			sentList.add(sentence);//把这个封装了子句的对象加入进去
			
		}
		
		return sentList;
	}
	
	
	/**
	 * 把一个句子拆分成更小的子句， 按照逗号进行拆分，封装到对象，然后存入list进行返回。
	 * @param sentenceContent
	 * @return
	 */
	public  static List<SubSentence> splitToSubSentence(String sentenceContent) {
		List<SubSentence> subsentList=new ArrayList<>();
		//对一句话进行拆分，拆分成逗号的子句。
		String subString[]=sentenceContent.split("[,]|[，]");
		for (int i = 0; i < subString.length; i++) {
//			System.out.println(subString[i]);
			SubSentence subSent=new SubSentence();
			subSent.setSubContent(subString[i]);
			subSent.setSubSenNum(i);
			subsentList.add(subSent);
		}
		
		return subsentList;
	}
	
	
}
