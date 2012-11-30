package com.jbrown.core.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlWriter {
	
	public static void main(String[] xx){
		System.out.println("*** main Begin *** ");
	  File file = new File("c:\\tmp\\test.js");
	  saveUrlToFile(file, "http://xx/images/");
	  System.out.println("*** main ended. *** ");
	}//http://aamaadmiparty.org/fancybox/jquery.mousewheel-3.0.4.pack.js
	
	public static void saveUrlToFile(File saveFile, String location) {
		URL url;
		try {
			url = new URL(location);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));
			BufferedWriter out = new BufferedWriter(new FileWriter(saveFile));
			char[] cbuf = new char[255];
			while ((in.read(cbuf)) != -1) {
				out.write(cbuf);
			}
			in.close();
			out.close();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
