package articleToSentence;

import java.util.ArrayList;
import java.util.List;

public class Paragraph {

	public double paraScore=0.0;
	public double getParaScore() {
		return paraScore;
	}


	public void setParaScore(double paraScore) {
		this.paraScore = paraScore;
	}


	public int paraNumber=0;
	public Sentence sent=null;
	public List<Sentence> senList=new ArrayList<>();
	
	
	public int getParaNumber() {
		return paraNumber;
	}


	public void setParaNumber(int paraNumber) {
		this.paraNumber = paraNumber;
	}


	public Sentence getSent() {
		return sent;
	}


	public void setSent(Sentence sent) {
		this.sent = sent;
	}


	public List<Sentence> getSenList() {
		return senList;
	}


	public void setSenList(List<Sentence> senList) {
		this.senList = senList;
	}


	public Paragraph() {
		// TODO Auto-generated constructor stub
	}
	
}
