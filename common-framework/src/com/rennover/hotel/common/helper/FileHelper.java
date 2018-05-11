/*
 * Copyright (c) 2013 Rennover Technologies, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Rennover Technologies, Inc.
 */

/*
 * 
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.rennover.hotel.common.helper;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

import com.rennover.hotel.common.exception.TechnicalException;
import com.rennover.hotel.common.exception.UserShowableException;

public class FileHelper
{
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");

	public static final String FILE_SEPARATOR = System.getProperty("file.separator");

	public static final String NSI_ZIP_SUFFIX = ".nsizip";

	public static byte[] read(String filePath)
	{
		try
		{
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			byte[] data = new byte[(int) file.length()];
			fis.read(data);
			return data;
		} catch (FileNotFoundException e)
		{
			throw new TechnicalException("Read file error", e);
		} catch (IOException e)
		{
			throw new TechnicalException("Read file error", e);
		}
	}

	public static void write(StringBuffer str, String filePath) throws IOException
	{
		File file = new File(filePath);
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
		bos.write(str.toString().getBytes());
		bos.close();
	}

	public static byte[] readAndZip(String filePath)
	{
		try
		{
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			DeflaterOutputStream zipos = new DeflaterOutputStream(bos);
			StreamHelper.write(fis, zipos);
			fis.close();
			zipos.close();
			return bos.toByteArray();
		} catch (FileNotFoundException e)
		{
			throw new TechnicalException("Read file error", e);
		} catch (IOException e)
		{
			throw new TechnicalException("Read file error", e);
		}
	}

	public static void save(File file, byte[] data)
	{
		try
		{
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(data);
			fos.flush();
			fos.close();
		} catch (IOException exception)
		{
			throw new UserShowableException("Erreur lors de la sauvegarde du fichier " + file.getName());
		}
	}

	public static void unzipAndSave(File file, byte[] data)
	{
		try
		{
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			InflaterInputStream zipis = new InflaterInputStream(new ByteArrayInputStream(data));
			StreamHelper.write(zipis, fos);
			fos.flush();
			fos.close();
			zipis.close();
		} catch (IOException exception)
		{
			throw new UserShowableException("Erreur lors de la sauvegarde du fichier " + file.getName());
		}
	}

	/**
	 * Zips the file specified as sourceFilePath, zips it in nsi format and
	 * saved the nsizipped file as targetFilePath. If the name of source file is
	 * not suffixed with NSI_ZIP_SUFFIX, NSI_ZIP_SUFFIX is added as suffix The
	 * zipped file can be unzipped by only FileHelper.unzipFile method
	 */
	public static File zipFile(String sourceFilePath, String targetFilePath)
	{
		if (targetFilePath != null && !targetFilePath.endsWith(NSI_ZIP_SUFFIX))
		{
			targetFilePath = targetFilePath + NSI_ZIP_SUFFIX;
		}
		File targetZipFile = new File(targetFilePath);
		return zipFile(sourceFilePath, targetZipFile);
	}

	/**
	 * 
	 * The name of the targetZipFile MUST be suffixed with
	 * FileHelper.NSI_ZIP_SUFFIX
	 */
	private static File zipFile(String sourceFilePath, File targetZipFile)
	{
		save(targetZipFile, readAndZip(sourceFilePath));
		return targetZipFile;
	}

	/**
	 * Zips the source file and saves the file in the systems temp folder. Name
	 * of zip file is the default nsi temp zip file name.
	 * 
	 * @param sourceFilePath
	 * @return
	 */
	public static File zipFile(String sourceFilePath)
	{
		return zipFile(sourceFilePath, TempFileHelper.createTempNSIZipFile().getAbsolutePath());
	}

	/*
	 * UNzips the file at targetFilePath- and saves it as sourceFilePath This
	 * method unzips the file zipped only by the FileHelper.zip method
	 */
	public static File unzipFile(String sourceFilePath, String targetFilePath)
	{
		if (sourceFilePath != null && !sourceFilePath.endsWith(NSI_ZIP_SUFFIX))
		{
			throw new UserShowableException("Source file " + sourceFilePath
			        + " to be unzipped is in unknown nsizip file format");
		}
		File unzippedTargetFile = new File(targetFilePath);
		unzipAndSave(unzippedTargetFile, read(sourceFilePath));
		return unzippedTargetFile;
	}

	/*
	 * 
	 */
	public static File unzipFile(String sourceFilePath)
	{
		return unzipFile(sourceFilePath, TempFileHelper.createTempFile().getAbsolutePath());
	}

	/*
	 * returns the array of strings containing lines in the file
	 */
	public static String[] getStringArray(File aFile, String lineSeperator)
	{

		byte[] bytes = read(aFile.getAbsolutePath());
		String str = new String(bytes);
		return StringHelper.split(str, lineSeperator);

	}

	public static void moveFile(File sourceFile, File targetFile)
	{
		sourceFile.renameTo(targetFile);
	}

	public static void copyFile(File sourceFile, File targetFile) throws FileNotFoundException, IOException
	{
		FileInputStream fr = null;
		BufferedOutputStream bos = null;
		try
		{
			bos = new BufferedOutputStream(new FileOutputStream(targetFile));
			fr = new FileInputStream(sourceFile);
			StreamHelper.write(fr, bos);
			bos.flush();
		} finally
		{
			if (fr != null)
			{
				fr.close();
			}

			if (bos != null)
			{
				bos.close();
			}
		}

	}

	public static void main(String args[])
	{
		System.out.println(unzipFile(zipFile("c:/winzip.log").getAbsolutePath()).getAbsolutePath());
		// Commmented code removed

	}
}
