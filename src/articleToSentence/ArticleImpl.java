package articleToSentence;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

public class ArticleImpl {
	public double artScore=0.0;

	public String ArticleTitle="";
	public Paragraph para=null;
	public double getArtScore() {
		return artScore;
	}
	public void setArtScore(double artScore) {
		this.artScore = artScore;
	}
	public List<Paragraph> paraList=new ArrayList<>();
	
	
	public String getArticleTitle() {
		return ArticleTitle;
	}
	public List<Paragraph> getParaList() {
		return paraList;
	}
	public void setParaList(List<Paragraph> paraList) {
		this.paraList = paraList;
	}
	public void setArticleTitle(String articleTitle) {
		ArticleTitle = articleTitle;
	}
	public Paragraph getPara() {
		return para;
	}
	public void setPara(Paragraph para) {
		this.para = para;
	}
	
}
