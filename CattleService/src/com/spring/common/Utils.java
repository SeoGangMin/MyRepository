package com.spring.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Utils {
	/*
	  * Comment  : 1. 입력받은 값 만큼의 날자를 더하기, 빼기 연산을 처리한다.
	  *            2. plus : 입력받은 수 만큼 더한 날자를 반환.
	  *            3. minus : 입력받은 수 만큼 뺀 날자를 반환.
	  * @version : 1.0
	  * @tags    : @param value
	  * @tags    : @return
	  * @date    : 2009. 8. 17.
	  */
	 public static String setOperationDate(String mode, String inputDate, int value){
	  SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
	  Date parseDate = null;
		
	  try {
			parseDate = fmt.parse(inputDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	  Calendar cal = new GregorianCalendar();
	  cal.setTime(parseDate);

	  if(mode.equals("plus")){
	   cal.add(cal.DATE,value); //현재날짜에 value 값을 더한다. 
	  }else if(mode.equals("minus")){
	   cal.add(cal.DATE,-value); //현재날짜에 value 값을 뺀다. 
	  }

	  Date date = cal.getTime(); //연산된 날자를 생성. 
	  String setDate = fmt.format(date);

	  return setDate;
	 }
	 
	 public static String customFormatDate(String inputDate, String formatter){
		 SimpleDateFormat fmt = new SimpleDateFormat(formatter);
		 Date parseDate = null; 
		 try {
			 inputDate = inputDate.replaceAll("-", "");
			parseDate = (new SimpleDateFormat("yyyyMMdd")).parse(inputDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return fmt.format(parseDate); 
	 }
}
