package com.pandocloud.android.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @described : 流转字符串工具
 * 
 * @corporation : 上海合家科技（www.hooca.com.cn）
 * 
 * @copyright : 版权所有，侵权必究
 * 
 * @author ：gileYang
 * 
 * @date ：2016-3-4
 * 
 * @version : 0.18.01s
 * 
 * @last modified time : 2016-3-4　
 */
public class StringUtil {

	public static String getStringFromInputStream(InputStream a_is) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			br = new BufferedReader(new InputStreamReader(a_is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
		}
		return sb.toString();
	}

}
