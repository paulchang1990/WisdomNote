/**
 * The MIT License (MIT)
 * 
 * Copyright (c) 2015 Paul Chang
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.paul.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * 读写文件的工具类.
 * 
 * 该类将文本框中的内容添加到一个目标文件中去
 * 
 * @author Paul Chang 2015-11-8
 * 
 */
/**
 * 
 * @author Paul Chang 2015-11-8
 * 
 */
/**
 * 
 * @author Paul Chang 2015-11-8
 * 
 */
public class FileUtils {

	/**
	 * 将String类型的数据写入一个文件中.
	 * 
	 * @param data
	 *            要写入的数据
	 * @param file
	 *            要被写入数据的文件
	 * @param append
	 *            是否追加，若false则完全覆盖
	 * @return true-成功写入数据
	 */
	public static boolean writeDataToFile(String data, File file, boolean append) {
		boolean result = false;
		if (isStringEmpty(data)) {
			return result;
		}
		PrintWriter out = null;
		System.out.println(data);
		try {
			out = new PrintWriter(new FileOutputStream(file, append), true);
			out.println(data);
			result = true;

		} catch (Exception e) {
			e.printStackTrace();
			return result;
		} finally {
			if (out != null) {
				out.close();
			}
		}
		return result;
	}

	/**
	 * 默认追加数据的写入.
	 * 
	 * @param data
	 *            要写入的数据
	 * @param file
	 *            要被写入数据的文件
	 * @return true-成功写入数据
	 */
	public static boolean writeDataToFile(String data, File file) {
		return writeDataToFile(data, file, true);
	}

	/**
	 * 判断数据是否为空.
	 * 
	 * @param data
	 *            目标数据
	 * @return true-目标数据为null或去掉空格后为字符串""
	 */
	public static boolean isStringEmpty(String data) {
		return data == null || data.trim().length() == 0;
	}

}
