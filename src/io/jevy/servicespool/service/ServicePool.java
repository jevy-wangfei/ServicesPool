package io.jevy.servicespool.service;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import io.jevy.servicespool.conf.*;
import io.jevy.servicespool.exceptoin.ParamNotMatchException;

public class ServicePool {

	private static final ServicePool INSTANCE = new ServicePool();
	private List<Service> serviceList;
	public String configFile = null;
	private static Logger log = Logger.getLogger(ReadService.class.getName());

	private ServicePool() {
		//System.out.println("ServicePool create instance.");
		if(configFile == null){
			serviceList = new ReadService().getServiceList();
		}else{
			serviceList = new ReadService(this.configFile).getServiceList();
		}
		
	}
	public static ServicePool getInstance(){ return INSTANCE;}
	
	public String getService(String serviceScope, String serviceName, String ...param ){
		String sql = null;
		for(Iterator<Service> t=serviceList.iterator(); t.hasNext(); ){
			Service service= (Service)t.next();
			if(serviceScope.equals(service.getSerivceScope()) && serviceName.equals(service.getServiceName())){
				//
				sql = setParams(service.getSQL(), param);
			}
		}
		return sql;
	}
	
	private String setParams(String sql, String[] param)  {
		String[] paramNameArray = this.getParamName(sql);
		int SQLParamCount = paramNameArray.length;
		int paramCount = param.length;
	System.out.println("#"+paramCount+":"+SQLParamCount);
	
		if(paramCount == SQLParamCount){
	System.out.println("##"+sql);
			sql = setParam(sql, paramNameArray,param);
			sql = sql.replace("[", " ");
			sql = sql.replace("]", " ");
	System.out.println("##"+sql);
			return sql;
		}else if(paramCount < SQLParamCount){
			String tempSql = sql.substring(0,  sql.indexOf("["));
			String[] tempSqlArray = this.getParamName(tempSql);
			if(paramCount==tempSqlArray.length){
				sql = setParam(sql, paramNameArray, param);
				
				sql = sql.substring(0,  sql.indexOf("["));
				return sql;
			}else{
				//Through Exception
				log.error("input params is less the params must needed in SQL");
				
			}
			
			
		}else if(paramCount > SQLParamCount){
			//Through Exception
			log.error("input params is more than the params most needed in SQL");
		}
		return null;
	}
	
	private String setParam(String sql, String[] paramNameArray,  String[] param){
		String s = sql;
		for(int i=0; i<param.length; i++){
			s = s.replaceFirst("=:"+paramNameArray[i], "="+param[i]);
			System.out.println("###"+paramNameArray[i]+":"+param[i]+":"+s);
		}
		return s;
	}
	
	private String[] getParamName(String sql){
		String[] splitSQL = sql.split("=:");
		String[] paramNameArray = new String[splitSQL.length-1];
		for(int i=1; i<splitSQL.length; i++){
			paramNameArray[i-1] = splitSQL[i].split(" ")[0].trim();
		}
		return paramNameArray;
	}
	
	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}

}
