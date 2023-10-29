package org.metachart.test;

import org.exlp.controller.handler.system.property.ConfigLoader;
import org.exlp.interfaces.system.property.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.exlp.util.io.ExlpCentralConfigPointer;
import net.sf.exlp.util.io.LoggerInit;
import net.sf.exlp.util.xml.JaxbUtil;

public class McBootstrap
{
	final static Logger logger = LoggerFactory.getLogger(McBootstrap.class);
	
	private enum System {metachart}
	
	public final static String cfgDirTmp = "dir.tmp";
	public final static String xmlConfig = "metachart/core/system/property/mc.xml";

	private static Configuration config;
	
	public static Configuration init() {return init(xmlConfig);}
	
	public static Configuration init(String configFile)
	{		
		LoggerInit loggerBootstrap = new LoggerInit("log4j.xml");
		loggerBootstrap.path("metachart/core/system/io/log");
		loggerBootstrap.init();
		//JaxbUtil.setNsPrefixMapper(new McNsPrefixMapper());
		
		ConfigLoader configBootstrap = ConfigLoader.instance();
		configBootstrap.add(ExlpCentralConfigPointer.instance(System.metachart).jaxb(JaxbUtil.instance()).toPath("core"));
		configBootstrap.add(configFile);
		config = configBootstrap.wrap();
		
		logger.debug("Config and Logger initialized");
		return config;
	}
}