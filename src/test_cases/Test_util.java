package test_cases;

import static org.junit.Assert.*;

import javax.rmi.CORBA.Util;

import org.junit.Test;

import util.Util_Sentimental;

public class Test_util {

	
	
	
	/**
	 * ���������ַ����Ƿ����ھӡ�
	 */
	@Test
	public void test_ifTwoStringNeighbour(){
		
		String word1="27_@��/d 28_@ϲ��/v 27_@��/d 0_@ϲ��/v";
		String word2="1_@�ر�/d 30_@�ر�/d 31_@��/a";
		
		boolean flag=Util_Sentimental.ifTwoStringNeighbour(word1, word2);
		assertTrue("true",flag);
		
	}
	
	
	
	
	
}
