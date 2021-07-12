package tz.go.tcra.hrms.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FileHelper {
	public static void copyFileUsingApacheCommonsIO(File source, File destination) throws IOException {
		// create source file if not exist
		if (!source.exists()) {
			source.mkdir();
		}
		// create destination directory if not exist
		if (!destination.exists()) {
			destination.mkdir();
		}
		FileUtils.copyFile(source, destination);
	}

	public static void copyFileExcel(File source, File destination) {
		// create source file if not exist
		if (!source.exists()) {
			source.mkdir();
		}
		// create destination directory if not exist
		if (!destination.exists() && !destination.isDirectory()) {
			try {
				destination.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// create input stream
		FileInputStream sourceFIS = null;
		try {
			sourceFIS = new FileInputStream(source);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		// load excel template
		Workbook wb = null;
		try {
			wb = WorkbookFactory.create(sourceFIS);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// create output stream
		FileOutputStream destinationFOS = null;
		try {
			destinationFOS = new FileOutputStream(destination);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			wb.write(destinationFOS);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		// write template data to newly created excel file
		try {
			destinationFOS.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
