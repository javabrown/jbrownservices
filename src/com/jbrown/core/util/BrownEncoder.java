package com.jbrown.core.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

public class BrownEncoder {
	final String DEFAULT_ENCODING = "UTF-8";

	public String encode(String text) {
		try {
			String rez = Base64.encodeBase64String(text
					.getBytes(DEFAULT_ENCODING));
			return rez;
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	public String decode(String text) {

		try {
			return new String(Base64.decodeBase64(text), DEFAULT_ENCODING);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static void main(String[] args) {
		String txt = "RAJA KHAN";
		BrownEncoder dis = new BrownEncoder();
		String encoded = dis.encode(txt);

		System.out.println(encoded);

		System.out.println(dis.decode(encoded));
	}

}