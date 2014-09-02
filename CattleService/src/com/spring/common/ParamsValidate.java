package com.spring.common;

import java.util.Map;
import java.util.Set;

public class ParamsValidate {
	
	private Map<String, String> requestMap; 
	private Set<String> 		requiredKeys; 
	
	private String errorMsg;
	private boolean validateParams; 

	public ParamsValidate(Map<String, String> requestMap, Set<String> requiredKeys) {
		this.requestMap = requestMap;
		this.requiredKeys = requiredKeys;
		
		System.out.println("execute ParamsValidate create!!");
		
		//request key가 유효하지 않은경우 msg생성
		if(!checkValidateOfKey()){
			this.errorMsg =  getMsg("keys");
		}
		//reqeust key가 유효한 경우 value체크
		else{
			if(!checkValidateOfValue()){
				this.errorMsg = getMsg("values");
			}else{
				validateParams = true; 
			}
		}
	}
	
	/**
	 * invalidateKey에 세팅된 키에서 유효키값들을 하나씩 지움. 
	 * invalidateKey에 남아있는 key값이 유효하지 않은 키로 체크됨.  
	 **/
	private boolean checkValidateOfKey(){
		
		Set<String> requestKeys = requestMap.keySet();
		Set<String> compSet	= requiredKeys; 
		Object[] array = requiredKeys.toArray();
		
		for(int i=0; i<array.length; i++){
			String key = array[i].toString(); 
			System.out.println("[checked Key] >> " + key);
			if(requestKeys.contains(key)){
				compSet.remove(key); 
				System.out.println("[removed Key] >> " + key);
			}
		}
		
		System.out.println("[remain keys] >> " + requiredKeys.size());
		return (compSet.size() <=0 ); 
	}
	
	private boolean checkValidateOfValue(){
		
		
		Set<String> compSet	= requiredKeys;
		Object[] array = requiredKeys.toArray();
		for(int i=0; i<array.length; i++){
			String key = array[i].toString(); 
			String compVal = requestMap.get(key).toString(); 
			System.out.println("[checked value] >> " + compVal);
			
			if(compVal != null && !compVal.equals("")){
				compSet.remove(key); 
				System.out.println("[removed valueOfKey] >> " + key);
			}
		}
		
		return (compSet.size() <= 0); 
	}
	
	public String getErrorMsg(){
		return errorMsg;
	}
	
	
	private String getMsg(String checkType){
		StringBuffer sb = new StringBuffer();
		Object[] array = requiredKeys.toArray();
		
		for(int i=0; i<array.length; i++){
			sb.append(array[i].toString());
			if(i != array.length - 1){
				sb.append(", ");
			}
		}
		
		if(checkType == "keys"){
			sb.append(" Prameter Required!"); 
		}else{
			sb.append(" Value is Empty!"); 
		}

		return sb.toString(); 
	}
	
	public boolean isValidateParams(){
		return validateParams; 
	}
	
}
