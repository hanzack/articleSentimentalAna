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
	 * ����������ÿһ���������
	 */
	@Test  
	public void test_splitParagraph(){
		String pathString="W:\\zackjob\\hacker\\nlp_workspace_files\\temp\\һ������.txt";
		String content=ReadAndWriteFileFromDirectory.readFileAndReturnContent(pathString);
		splitArticle.splitToParagraph(content);
	}
	
	/**
	 * ����һ�����²�ֳɲ�ͬ�ľ��ӡ� ��ӡÿһ������
	 */
	@Test
	public void test_splitSentence(){
		String paraCon="�Ӵ�����Ƶ��������Ʒ������ʵ���Ҳ�Ʒ��һЩ����ʶ���Ӱ���ǱȽ��ض�����˵�Ƚϵ��͵Ļ���!�����Է׷����ӵĴ�ǧ���磬����Ʒ����Ҫ����ǿ���ʶ������.��Ϊ��һλä������ԵĲ������ǿɰ���С���ӡ�Ư���Ĺ���Ϳɿڵ���ʳ?ʶ��һЩͻ����δ֪�����Ϊä�����ǽ��ѣ�����Ǹ���?��Ŀǰ����Seeing AI��ʵսĿ�껹���Ǻܷḻ��ʵ��Ч�������Ǻ�ǿ��";
		splitArticle.splitToSentence(paraCon);
	}
	
	
	/**
	 * ����һ�����Ӳ�ֳɸ�С���Ӿ��ӡ������
	 */
	@Test
	public void test_splitToSubSentence(){
		String parString="�Ӵ�����Ƶ��������Ʒ��������ʵ���Ҳ�Ʒ��";
		splitArticle.splitToSubSentence(parString);
		
	}
	
	
	
	/**
	 * ����һƪ���£�ʹ����ƪ���½��нṹ�������  ���ݽ��Ķ���
	 */
	@Test
	public void test_(){
		String pathString="W:\\zackjob\\hacker\\nlp_workspace_files\\temp\\һ������.txt";
		String content=ReadAndWriteFileFromDirectory.readFileAndReturnContent(pathString);
		ArticleImpl art=splitArticle.splitArticleReturnArticle(content,"");
		// ע������Ҳ����sent.getSentenStr()   ֱ�ӻ�þ��ӵ�����
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
	
	
	
}
