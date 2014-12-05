package com.tgy.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class CharsetInspect {

	public static void test(String str) {

		List<String> list = new ArrayList<>();

		list.add("gbk");
		list.add("gb2312");
		list.add("utf-8");
		list.add("iso-8859-1");
		try {
			for (String charset1 : list) {
				System.out.println(" - -" + charset1 + " - -"
						+ new String(str.getBytes(), charset1));
			}

			for (String charset1 : list) {

				for (String charset2 : list) {

					System.out.println(charset1 + " - " + charset2 + "-"
							+ new String(str.getBytes(charset1), charset2));

				}

			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

}
