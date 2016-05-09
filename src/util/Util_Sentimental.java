package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util_Sentimental {

	
	/**
	 * ��һ���ַ�����ĩβ���֣���ȥ  �ڶ����ַ�����   ��λ����   ����   1
	 * ���������ִʵ��ַ�����  �ж��������ִ��Ƿ����ھӡ�
	 *    1_@��/d 2_@��/v             ��                  3_@����/a
	 *    �������������ģ�����Ϊ ��neighbour�� ��Ϊ 2������3
	 * @param str1  
	 * @param str2
	 * @return
	 */
	public static boolean ifTwoStringNeighbour(String str1,String str2){
		boolean neighbour=false;
		int firstNum=-1;
		int secondNum=-1;
		//����ȥ�����˿ո�
		str1=str1.trim();
		str2=str2.trim();
		String[] str1Array=str1.split("\\s+");
		String[] str2Array=str2.split("\\s+");
		//��ȡ��һ���ַ��������һ���ʵ����
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
