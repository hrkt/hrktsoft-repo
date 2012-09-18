package com.hrktsoft.jns;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;

/**
 * A converter from BigInteger to string representation of the value in Japanese
 * Numaral System.
 * 
 * @see http://ja.wikipedia.org/wiki/命数法
 * 
 * @author hrkt
 * 
 * 
 * 
 * 
 ----------------------------------------------------------------------------
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
public class JNSConverter {
	@SuppressWarnings("unused")
	private static String Meisuu_10_0 = "一";
	private static String Meisuu_10_4 = "万";
	private static String Meisuu_10_8 = "億";
	private static String Meisuu_10_12 = "兆";
	private static String Meisuu_10_16 = "京";
	private static String Meisuu_10_20 = "垓";
	private static String Meisuu_10_24 = "𥝱";
	private static String Meisuu_10_28 = "穣";
	private static String Meisuu_10_32 = "溝";
	private static String Meisuu_10_36 = "澗";
	private static String Meisuu_10_40 = "正";
	private static String Meisuu_10_44 = "載";
	private static String Meisuu_10_48 = "極";
	private static String Meisuu_10_52 = "恒河沙";
	private static String Meisuu_10_56 = "阿僧祇";
	private static String Meisuu_10_60 = "那由他";
	private static String Meisuu_10_64 = "不可思議";
	private static String Meisuu_10_68 = "無量大数";

	private static String[] meisuu = new String[] { "", Meisuu_10_4,
			Meisuu_10_8, Meisuu_10_12, Meisuu_10_16, Meisuu_10_20,
			Meisuu_10_24, Meisuu_10_28, Meisuu_10_32, Meisuu_10_36,
			Meisuu_10_40, Meisuu_10_44, Meisuu_10_48, Meisuu_10_52,
			Meisuu_10_56, Meisuu_10_60, Meisuu_10_64, Meisuu_10_68, };

	/**
	 * Returns string expression of BigInteger in Japanese numeration system.
	 * 
	 * <ul>
	 * <li>ex)given "12345", returns "1万2345"</li>
	 * <li>ex)given "123456789", returns "1億2345万6789"</li>
	 * <li>ex)given "100000000000000000000000000000000000000000000000000000000000000000000", returns "1無量大数"</li>
	 * </ul>
	 * @param bi
	 *            value to be parsed.
	 * @return a number in Japanese numeration system format
	 * @throws IllegalArgumentException-when negative value is given
	 */
	public static String createJapaneseNumeralString(BigInteger bi) {
		if(null == bi){
			throw new IllegalArgumentException("number must be specified.");
		}
		if (-1 == bi.signum()) {
			throw new IllegalArgumentException(
					"number must be zero or positive number.given:"
							+ bi.toString());
		}
		BigInteger unit = BigInteger.valueOf(10000);
		BigInteger buf = bi;
		int numDigits = 0;
		// parse number and push part of it to queue.
		Queue<BigInteger> queue = new LinkedList<BigInteger>();
		while (-1 != buf.compareTo(unit) && numDigits <= 64) {
			BigInteger mod = buf.mod(unit);
			queue.add(mod);
			buf = (buf.subtract(mod)).divide(unit);
			numDigits += 4;
		}
		queue.add(buf);// rest

		// build string
		StringBuilder sb = new StringBuilder();
		int queueSize = queue.size();
		for (int i = 0; i < queueSize; i++) {
			BigInteger mod = queue.poll();
			if (mod.longValue() != 0) {
				sb.insert(0, mod + meisuu[i]);
			}
		}

		// for exceptional case, return 0 manually.
		if (0 != sb.length()) {
			return sb.toString();
		} else {
			return "0";
		}
	}
}
