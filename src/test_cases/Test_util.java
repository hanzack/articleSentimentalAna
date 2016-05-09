package test_cases;

import static org.junit.Assert.*;

import javax.rmi.CORBA.Util;

import org.junit.Test;

import util.Util_Sentimental;

public class Test_util {

	
	
	
	/**
	 * 测试两个字符串是否是邻居。
	 */
	@Test
	public void test_ifTwoStringNeighbour(){
		
		String word1="27_@很/d 28_@喜欢/v 27_@很/d 0_@喜欢/v";
		String word2="1_@特别/d 30_@特别/d 31_@丑/a";
		
		boolean flag=Util_Sentimental.ifTwoStringNeighbour(word1, word2);
		assertTrue("true",flag);
		
	}
	
	
	
	
	
}
