package com.hrktsoft.jns;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.math.BigInteger;
import java.util.Random;

import org.junit.Test;

public class JNSConverterTest {

	/**
	 * confirm basic boundary test
	 */
	@Test
	public void test() {
		BigInteger num10000 = BigInteger.valueOf(10000);
		BigInteger num12345 = BigInteger.valueOf(12345);
		BigInteger num100000000 = BigInteger.valueOf(100000000);
		assertEquals("0",
				JNSConverter.createJapaneseNumeralString(BigInteger.valueOf(0)));
		assertEquals("1",
				JNSConverter.createJapaneseNumeralString(BigInteger.valueOf(1)));
		assertEquals("1234",
				JNSConverter.createJapaneseNumeralString(BigInteger.valueOf(1234)));
		assertEquals("9999",
				JNSConverter.createJapaneseNumeralString(BigInteger.valueOf(9999)));
		assertEquals("1万",
				JNSConverter.createJapaneseNumeralString(num10000));
		assertEquals("1万1",
				JNSConverter.createJapaneseNumeralString(BigInteger.valueOf(10001)));
		assertEquals("1万2345",
				JNSConverter.createJapaneseNumeralString(num12345));
		assertEquals("1万9999",
				JNSConverter.createJapaneseNumeralString(BigInteger.valueOf(19999)));
		assertEquals("2万1",
				JNSConverter.createJapaneseNumeralString(BigInteger.valueOf(20001)));
		assertEquals("9999万9999",
				JNSConverter.createJapaneseNumeralString(num100000000.subtract(BigInteger.valueOf(1))));
		assertEquals("1億",
				JNSConverter.createJapaneseNumeralString(num100000000));
		assertEquals("1億1",
				JNSConverter.createJapaneseNumeralString(num100000000.add(BigInteger.valueOf(1))));
	}

	/**
	 * confirm bigger numbers are accepted.
	 */
	@Test
	public void test2() {
		BigInteger ten = BigInteger.valueOf(10);
		for (int i = 0; i <= 68; i += 4) {
			BigInteger v = ten.pow(i);
			String s = JNSConverter.createJapaneseNumeralString(v);
			assertNotNull(s);
			System.out.println(s + ":" + v.toString());
		}
	}

	@Test
	public void test3() {
		BigInteger ten = BigInteger.valueOf(10);
		{
			BigInteger v = ten.pow(69);
			String s = JNSConverter.createJapaneseNumeralString(v);
			assertNotNull(s);
			System.out.println(s);
		}
		{
			BigInteger v = ten.pow(70);
			String s = JNSConverter.createJapaneseNumeralString(v);
			assertNotNull(s);
			System.out.println(s);
		}
		{
			BigInteger v = ten.pow(71);
			String s = JNSConverter.createJapaneseNumeralString(v);
			assertNotNull(s);
			System.out.println(s);
		}
		{
			BigInteger v = ten.pow(72);
			String s = JNSConverter.createJapaneseNumeralString(v);
			assertNotNull(s);
			System.out.println(s);
		}
	}

	@Test
	public void test4() {
		for (int i = 1; i < 256; i++) {
			BigInteger v = new BigInteger(i, new Random());
			String s = JNSConverter.createJapaneseNumeralString(v);
			assertNotNull(s);
			System.out.println(s);
		}
	}
	
	@Test
	public void test5(){
		try{
			JNSConverter.createJapaneseNumeralString(BigInteger.valueOf(-1));
			fail();
		}catch(IllegalArgumentException e) {
			//OK
		}
	}
	
}
