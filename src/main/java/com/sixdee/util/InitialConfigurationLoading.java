package com.sixdee.util;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import java.util.Enumeration;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Properties;

@Component
@EnableConfigurationProperties
@PropertySource("classpath:application.properties")
public class InitialConfigurationLoading {
	private static final Logger logger = LogManager.getLogger(InitialConfigurationLoading.class);

	public static final String SYSTEM_PROPERTIES_FILE = "System.properties";
	@Autowired
	public Environment environment;

	@PostConstruct
	public void loadCache() throws Exception {

		try {
			// propertyFileLoading();
			loadProperties();

		} catch (Exception e) {
			logger.info("Exception in initial loading ", e);
			throw new Exception("Application failed to start");
		}

	}

	@PostConstruct

	public void propertyFileLoading() {
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>      " + environment.getProperty("DB_TYPE"));
		environment.getProperty("RE_CALL_TIMEOUT_CONFIG");
		AppCache.setEnvironment(environment);
	}

	public static void loadProperties() {
		InputStream in = null;
		Properties properties = null;
		Map<String, String> map = Collections.synchronizedMap(new HashMap<String, String>());
		Enumeration enumeration = null;
		List<String> accountCategoryList = null;
		try {
			in = InitialConfigurationLoading.class.getClassLoader().getResourceAsStream(SYSTEM_PROPERTIES_FILE);
			properties = new Properties();
			properties.load(in);

			enumeration = properties.keys();

			while (enumeration.hasMoreElements()) {
				String key = (String) enumeration.nextElement();

				if (properties.getProperty(key) != null) {
					logger.info("key = " + key + " &  value = " + (String) properties.getProperty(key));
					map.put(key.trim(), (String) properties.getProperty(key).trim());
				} else {
					logger.info("key = " + key + " & value = NOT FOUND");
					logger.info("Exit ");
					throw new Exception();
				}

			}
			AppCache.setCacheMap(map);

		} catch (Exception e) {
			logger.info("Error occured while loading system property", e);
			e.printStackTrace();
			System.exit(1);
		} finally {
			try {
				if (in != null) {
					in.close();
					in = null;
				}
			} catch (Exception e) {
			}
			properties = null;
			map = null;
			enumeration = null;
			accountCategoryList = null;

		}
	}// loadProperties

}
