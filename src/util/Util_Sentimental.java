package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util_Sentimental {

	
	/**
	 * 第一个字符串的末尾数字，减去  第二个字符串的   首位数字   等于   1
	 * 传入两个分词的字符串，  判断这两个分词是否是邻居。
	 *    1_@不/d 2_@是/v             与                  3_@美丽/a
	 *    这两个是连续的，就认为 是neighbour， 因为 2后面是3
	 * @param str1  
	 * @param str2
	 * @return
	 */
	public static boolean ifTwoStringNeighbour(String str1,String str2){
		boolean neighbour=false;
		int firstNum=-1;
		int secondNum=-1;
		//首先去掉两端空格
		str1=str1.trim();
		str2=str2.trim();
		String[] str1Array=str1.split("\\s+");
		String[] str2Array=str2.split("\\s+");
		//获取第一个字符串的最后一个词的序号
		String numRegex="\\d+";
		Pattern pattern=Pattern.compile(numRegex);
		Matcher matcher=pattern.matcher(str1Array[str1Array.length-1]);
		if (matcher.find()) {
			firstNum=Integer.parseInt(matcher.group());
		}
		
		Matcher matcher2=pattern.matcher(str2Array[0]);
		if (matcher2.find()) {
			secondNum=Integer.parseInt(matcher2.group());
		}
		
//		System.out.println("ifTwoStringNeighbour"+firstNum+" : "+secondNum);
		if (secondNum-firstNum==1) {
			neighbour=true;
		}
		return neighbour;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
