package com.sixdee.util;



import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;



@Configuration
public class AppCache {

	
	public static Environment environment;
	public static Map<String,String> cacheMap;

	public static Environment getEnvironment() {
		return environment;
	}

	public static void setEnvironment(Environment environment) {
		AppCache.environment = environment;
	}

	public static Map<String, String> getCacheMap() {
		return cacheMap;
	}

	public static void setCacheMap(Map<String, String> cacheMap) {
		AppCache.cacheMap = cacheMap;
	}
	
	
}
