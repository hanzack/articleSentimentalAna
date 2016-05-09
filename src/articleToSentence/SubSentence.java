package articleToSentence;

public class SubSentence{

	public int subSenNum=0;
	public double subSenScore=0.0;
	public int getSubSenNum() {
		return subSenNum;
	}

	public void setSubSenNum(int subSenNum) {
		this.subSenNum = subSenNum;
	}

	public String getSubContent() {
		return subContent;
	}

	public double getSubSenScore() {
		return subSenScore;
	}

	public void setSubSenScore(double subSenScore) {
		this.subSenScore = subSenScore;
	}

	public void setSubContent(String subContent) {
		this.subContent = subContent;
	}

	String subContent="";
	
	public SubSentence(){
		
	}
	
	
}
