package com.hrktsoft.jns;

import java.io.Console;
import java.math.BigInteger;

/**
 * A simple console tool for Japanese Numeration System converter.
 * 
 * <pre>
 * How to run:
 * $ java -jar target/JapaneseNumerationSystemConverter-0.0.1-SNAPSHOT.jar 
 * </pre>
 * 
 * 
 * @author hrkt
 *
 *----------------------------------------------------------------------------
 <pre>
   Copyright 2012 Hiroki Ito

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
	</pre>
   ----------------------------------------------------------------------------

 */
public class JNSMain {
	public static void main(String[] args) {
		System.out.println("[A Japanese Numerical System Converter]");
		System.out.println("");
		System.out.println("usage:");
		System.out.println("- type in zero or positive value and press enter.");
		System.out.println("- press ctrl+c to quit.");
		System.out.println("");
		System.out.println("- This tool cannot run on Eclipse console.");
		System.out.println("- if you have encoding problems on MacOSX + Java, try put these two lines in your .bash_profile)");
		System.out.println("alias javac='javac -J-Dfile.encoding=UTF-8'");
		System.out.println("alias java='java -Dfile.encoding=UTF-8'");
		System.out.println("");
		
		Console console = System.console();
		while(true) {
			String line = console.readLine("number?");
			try{
				BigInteger bi = new BigInteger(line);
				String jns = JNSConverter.createJapaneseNumeralString(bi);
				System.out.println(String.format(bi.toString() + "=>" + jns));
			} catch (IllegalArgumentException e) {
				System.out.println("error.");
			}
			System.out.println("");
		}
	}
}
