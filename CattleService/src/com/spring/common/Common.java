package com.spring.common;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Common {
	public static String getHeaderInfo(HttpServletRequest req){
		JSONObject json = null;
		try {
			json = new JSONObject();  
			 Enumeration names = req.getHeaderNames(); 
			 while(names.hasMoreElements()){
				 String name = (String)names.nextElement();
				 json.put(name, req.getHeader(name));
			 }
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return json.toString();
	}
	
	public static void checkStringEncoding(String str){
		String word = str;
		try {
			System.out.println("utf-8(1) : " + new String(word.getBytes("utf-8"), "euc-kr"));
			System.out.println("utf-8(2) : " + new String(word.getBytes("utf-8"), "ksc5601"));
			System.out.println("utf-8(3) : " + new String(word.getBytes("utf-8"), "x-windows-949"));
			System.out.println("utf-8(4) : " + new String(word.getBytes("utf-8"), "iso-8859-1"));
			 
			System.out.println("iso-8859-1(1) : " + new String(word.getBytes("iso-8859-1"), "euc-kr"));
			System.out.println("iso-8859-1(2) : " + new String(word.getBytes("iso-8859-1"), "ksc5601"));
			System.out.println("iso-8859-1(3) : " + new String(word.getBytes("iso-8859-1"), "x-windows-949"));
			System.out.println("iso-8859-1(4) : " + new String(word.getBytes("iso-8859-1"), "utf-8"));
			 
			System.out.println("euc-kr(1) : " + new String(word.getBytes("euc-kr"), "ksc5601"));
			System.out.println("euc-kr(2) : " + new String(word.getBytes("euc-kr"), "utf-8"));
			System.out.println("euc-kr(3) : " + new String(word.getBytes("euc-kr"), "x-windows-949"));
			System.out.println("euc-kr(4) : " + new String(word.getBytes("euc-kr"), "iso-8859-1"));
			 
			System.out.println("ksc5601(1) : " + new String(word.getBytes("ksc5601"), "euc-kr"));
			System.out.println("ksc5601(2) : " + new String(word.getBytes("ksc5601"), "utf-8"));
			System.out.println("ksc5601(3) : " + new String(word.getBytes("ksc5601"), "x-windows-949"));
			System.out.println("ksc5601(4) : " + new String(word.getBytes("ksc5601"), "iso-8859-1"));
			 
			System.out.println("x-windows-949(1) : " + new String(word.getBytes("x-windows-949"), "euc-kr"));
			System.out.println("x-windows-949(2) : " + new String(word.getBytes("x-windows-949"), "utf-8"));
			System.out.println("x-windows-949(3) : " + new String(word.getBytes("x-windows-949"), "ksc5601"));
			System.out.println("x-windows-949(4) : " + new String(word.getBytes("x-windows-949"), "iso-8859-1"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
