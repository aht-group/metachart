package org.metachart.test;

import net.sf.exlp.util.io.LoggerInit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class McXmlTestBootstrap
{
	final static Logger logger = LoggerFactory.getLogger(McXmlTestBootstrap.class);
		
	public static void init()
	{
		LoggerInit loggerInit = new LoggerInit("log4j.xml");	
		loggerInit.path("config.metachart-xml.test");
		loggerInit.init();
//		JaxbUtil.setNsPrefixMapper(new McNsPrefixMapper());
	}
}