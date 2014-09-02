package com.spring.common;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Common {
	public static final String getHeaderInfo(HttpServletRequest req){
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
}
