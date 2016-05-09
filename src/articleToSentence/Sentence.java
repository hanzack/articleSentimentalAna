package articleToSentence;

import java.util.ArrayList;
import java.util.List;

public class Sentence {
    public double sentScore=0.0;
	public int sentenceNum=0;
	public String sentenStr="";
	public int getSentenceNum() {
		return sentenceNum;
	}
	public double getSentScore() {
		return sentScore;
	}
	public void setSentScore(double sentScore) {
		this.sentScore = sentScore;
	}
	public void setSentenceNum(int sentenceNum) {
		this.sentenceNum = sentenceNum;
	}
	public String getSentenStr() {
		return sentenStr;
	}
	public void setSentenStr(String sentenStr) {
		this.sentenStr = sentenStr;
	}
	public SubSentence getSubSent() {
		return subSent;
	}
	public void setSubSent(SubSentence subSent) {
		this.subSent = subSent;
	}
	public List<SubSentence> getSubSenList() {
		return subSenList;
	}
	public void setSubSenList(List<SubSentence> subSenList) {
		this.subSenList = subSenList;
	}
	public SubSentence subSent=null;
	public List<SubSentence> subSenList=new ArrayList<>();
	
	
}
