package com.urt.util.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class GenerateZip {
	private static final Log log = LogFactory.getLog(GenerateZip.class);
	List<String> fileList = new ArrayList<String>();

	private String OUTPUT_ZIP_FILE = null;
    private String SOURCE_FOLDER = null;
    
 
    public String getOUTPUT_ZIP_FILE() {
		return OUTPUT_ZIP_FILE;
	}
	public void setOUTPUT_ZIP_FILE(String oUTPUT_ZIP_FILE) {
		OUTPUT_ZIP_FILE = oUTPUT_ZIP_FILE;
	}
	public void generatePath(String sourceFile,String zipFile){
    	OUTPUT_ZIP_FILE=zipFile;
    	SOURCE_FOLDER=sourceFile;
    }
	/**
	 * Zip it
	 * 
	 * @param zipFile  output ZIP file location	 
	 * @throws FileNotFoundException 
	 */
	public void zipIt(String zipFile) throws FileNotFoundException  {
		byte[] buffer = new byte[1024];
		
		FileInputStream in = null;
		FileOutputStream fos = new FileOutputStream(zipFile); 
		ZipOutputStream zos = new ZipOutputStream(fos);
		try  {
						
			for (String file : fileList) {
				ZipEntry ze = new ZipEntry(file);
				zos.putNextEntry(ze);
				in = new FileInputStream(SOURCE_FOLDER+ File.separator + file);
				int len;
				while ((len = in.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}
				in.close();				
			}
			zos.closeEntry();
			// remember close it
			//zos.close();
			log.debug("Done");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			IOUtils.closeQuietly(in);
		}
			
	}

	/**
	 * Traverse a directory and get all files, and add the file into fileList
	 * 
	 * @param node file or directory
	 */
	public void generateFileList(File node) {
		// add file only
		if (node.isFile()) {
			fileList.add(generateZipEntry(node.getAbsoluteFile().toString()));
		}
		if (node.isDirectory()) {
			String[] subNote = node.list();
			for (String filename : subNote) {
				generateFileList(new File(node, filename));
			}
		}
	}

	/**
	 * Format the file path for zip	 * 
	 * @param file file path
	 * @return Formatted file path
	 */
	private String generateZipEntry(String file) {
		return file.substring(SOURCE_FOLDER.length() + 1, file.length());
	}

}
