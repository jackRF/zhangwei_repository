package com.jack.core.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtil {
	/**
	 * �����ļ�
	 * @param fis
	 * @param fos
	 * @throws IOException
	 */
	public static void  copyFile(InputStream fis,OutputStream fos) throws IOException{
		try {
			byte[] bytes = new byte[1024];
			int i;
			while ((i = fis.read(bytes)) != -1) {
				fos.write(bytes, 0, i);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (fis != null) {
				fis.close();
			}
			if (fos != null) {
				fos.close();
			}
		}
	}
	/**
	 * �����ļ�
	 * 
	 * @param fis
	 * @param filePath
	 * @throws IOException
	 */
	public static void copyFile(InputStream fis, String filePath)
			throws IOException {
		copyFile(fis,new FileOutputStream(new File(filePath)));
	}
	/**
	 * �����ļ�
	 * @param srcFile
	 * @param destFile
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void copyFile(File srcFile,File destFile) throws FileNotFoundException, IOException{
		copyFile(new FileInputStream(srcFile),new FileOutputStream(destFile));
	}
	/**
	 * �����ļ�
	 * @param srcFilePath
	 * @param destFilePath
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void copyFile(String srcFilePath,String destFilePath) throws FileNotFoundException, IOException{
		copyFile(new File(srcFilePath),new File(destFilePath));
	}

}
