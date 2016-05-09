package articleToSentence;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import fileHandle_From_Directory.ReadAndWriteFileFromDirectory;
import Primary_data_type_zack_util.String_handle_Zack_Util;


//���㴫��һƪ���µ�ʱ�� �����Զ�����һ�����¶��� �����ֻὨ������Ķ��󣬶����ֻὨ�����ӵĶ���
	//�����ֻὨ���Ӿ�Ķ��� ÿ���඼��һ��list���������洢  ���Ӷ���ļ��ϡ�
	
//����һƪ���£�������ƪ���µĶ��� ���¶������һ��list����� ����Ķ��� ÿ������Ķ����������������ľ��ӵĶ���
//ÿ�����ӵĶ����ִ����һ��list������ �Ӿ�Ķ����Ӿ���һ�����Ӱ��ն��Ų�ֺ�Ľ����

//ע������Ҳ����sent.getSentenStr()   ֱ�ӻ�þ��ӵ����ݣ� �����û��  һ�����ӵ��Ӿ䣨�Ծ��ӽ��ж��Ž��в�֣�
public class splitArticle {
	
	
	public static ArticleImpl getArticleObject(String articlePath){
//		String pathString="W:\\zackjob\\hacker\\nlp_workspace_files\\temp\\һ������.txt";
		String fileName=ReadAndWriteFileFromDirectory.getFileNameFromFilePath(articlePath);
		String pathString=articlePath;
		String content=ReadAndWriteFileFromDirectory.readFileAndReturnContent(pathString);
		ArticleImpl art=splitArticle.splitArticleReturnArticle(content,fileName);
		return art;
	}
	/**
	 * ����һƪ���£�ʹ����ƪ���½��нṹ�������  ���ݽ��Ķ���
	 */
	@Test
	public void test_(){
		String pathString="W:\\zackjob\\hacker\\nlp_workspace_files\\temp\\һ������.txt";
		String content=ReadAndWriteFileFromDirectory.readFileAndReturnContent(pathString);
		ArticleImpl art=splitArticle.splitArticleReturnArticle(content,"һ������");
		
		for (Paragraph para:art.getParaList()) {//��ȡÿ������
			for (Sentence sent:para.getSenList()) {//��ȡÿһ�仰
				for (SubSentence subSen:sent.getSubSenList()) {//��ȡÿһ���Ӿ�
					System.out.println("���䣺 "+para.getParaNumber());
					System.out.println("���ӣ�"+sent.getSentenceNum());
					System.out.println("�Ӿ䣺"+subSen.getSubSenNum()+"---"+subSen.getSubContent());
				}
				
			}
			System.out.println("-------------------------------------");
		}
		
		
	}
   //���㴫��һƪ���µ�ʱ�� �����Զ�����һ�����¶��� �����ֻὨ������Ķ��󣬶����ֻὨ�����ӵĶ���
	//�����ֻὨ���Ӿ�Ķ��� ÿ���඼��һ��list���������洢  ���Ӷ���ļ��ϡ�
	
	/**
	 * ��ȡһƪ���£������½��в�֡��õ�һ�����µĶ���
	 * @param articleStr
	 * @param art
	 */
	public static ArticleImpl splitArticleReturnArticle(String articleStr,String articleName){
		ArticleImpl article=new ArticleImpl();
		String titleString=articleName;
		//�������µı���
		article.setArticleTitle(titleString);
		List<Paragraph> paraList=splitToParagraph(articleStr);
		//�������µĶ��伯��
		article.setParaList(paraList);
		return article;
	}
	
	/**
	 * ��ȡһƪ���£�����һ��list������洢�Ų�ͬ�Ķ���Ķ��� �Զ�����з�װ
	 * @param articleStr
	 * @return
	 */
	public static List<Paragraph> splitToParagraph(String articleStr){
		List<Paragraph> paraList=new ArrayList<>();
		//�����������Ժ󣬰���"(\r\n)+"�Ķ���н������µĲ�֡������½��в��
		String []contStrings=articleStr.split("(\r\n)+");
		//��ӡ�����µĸ�������
		for (int i = 0; i < contStrings.length; i++) {
//			System.out.println(i+"---"+contStrings[i]);
			//Ȼ���ÿһ�ν��в��
			String paraTemp=contStrings[i];//��ȡÿһ�ε�����
			List<Sentence> sentList=splitToSentence(paraTemp);
			Paragraph aParagraph=new Paragraph();
			aParagraph.setSenList(sentList);//�������������������ľ��ӵ�list
			aParagraph.setParaNumber(i);  //��¼�����������µĵڼ�����
			//Ȼ�����һ�μ��뵽������ȥ��
			paraList.add(aParagraph);  
		}
		return paraList;
	}
	
	
	/**
	 * ��һ�������ֳɾ��ӣ���װ������Ȼ�����list���з��ء�
	 * @param paraContent
	 * @return
	 */
	public static List<Sentence> splitToSentence(String paraContent){
		List<Sentence> sentList=new ArrayList<>();
		//�Զ��䰴�վ��ӽ��в��
//		String []contStrings=paraContent.split("[.]|[��]|[;]|[?]|[!]");
		String []contStrings=String_handle_Zack_Util.splitReserveMark(paraContent, "[.]|[��]|[;]|[?]|[!]|[��]");
		for (int i = 0; i < contStrings.length; i++) {
//			System.out.println(contStrings[i]);
			String sentString=contStrings[i];//�����ÿһ��
//			Ȼ����ж��ŵĲ��
			Sentence sentence=new Sentence();//�½����Ӷ���
			sentence.setSentenceNum(i);
			sentence.setSentenStr(sentString);// �Ѿ������ݴ��ڶ����senten����
			// �Ծ��ӵ����ݽ��в��
			List<SubSentence> subsentList=splitToSubSentence(sentString);
			sentence.setSubSenList(subsentList);
			sentList.add(sentence);//�������װ���Ӿ�Ķ�������ȥ
			
		}
		
		return sentList;
	}
	
	
	/**
	 * ��һ�����Ӳ�ֳɸ�С���Ӿ䣬 ���ն��Ž��в�֣���װ������Ȼ�����list���з��ء�
	 * @param sentenceContent
	 * @return
	 */
	public  static List<SubSentence> splitToSubSentence(String sentenceContent) {
		List<SubSentence> subsentList=new ArrayList<>();
		//��һ�仰���в�֣���ֳɶ��ŵ��Ӿ䡣
		String subString[]=sentenceContent.split("[,]|[��]");
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
