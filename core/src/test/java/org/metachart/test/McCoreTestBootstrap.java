package org.metachart.test;

import org.apache.commons.configuration.Configuration;
import org.metachart.xml.McNsPrefixMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.exlp.util.config.ConfigLoader;
import net.sf.exlp.util.io.LoggerInit;
import net.sf.exlp.util.xml.JaxbUtil;

public class McCoreTestBootstrap
{
	final static Logger logger = LoggerFactory.getLogger(McCoreTestBootstrap.class);
	
	public static Configuration init()
	{		
		LoggerInit loggerInit = new LoggerInit("log4j.xml");
		loggerInit.addAltPath("mc-core.test/config/log4j.xml");
		loggerInit.init();
		JaxbUtil.setNsPrefixMapper(new McNsPrefixMapper());
			
		ConfigLoader.add("mc-core.test/config/mc.xml");
		Configuration config = ConfigLoader.init();					
		logger.debug("Config and Logger initialized");
		return config;
	}
}