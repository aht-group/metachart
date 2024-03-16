package org.metachart.test;

import org.exlp.controller.handler.io.log.LoggerBootstrap;
import org.exlp.controller.handler.system.property.ConfigLoader;
import org.exlp.interfaces.system.property.Configuration;
import org.exlp.util.io.config.ExlpCentralConfigPointer;
import org.exlp.util.io.log.LoggerInit;
import org.exlp.util.jx.JaxbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class McBootstrap
{
	final static Logger logger = LoggerFactory.getLogger(McBootstrap.class);
	
	private enum System {metachart}
	
	public final static String cfgDirTmp = "dir.tmp";
	public final static String xmlConfig = "metachart/system/property/mc.xml";

	private static Configuration config;
	
	public static Configuration init() {return init(xmlConfig);}
	
	public static Configuration init(String configFile)
	{
		LoggerBootstrap.instance("cli.xml.log4j2.xml").path("lis/system/io/log").init();
//		LoggerInit loggerBootstrap = new LoggerInit("log4j.xml");
//		loggerBootstrap.path("metachart/system/io/log");
//		loggerBootstrap.init();
		//JaxbUtil.setNsPrefixMapper(new McNsPrefixMapper());
		
		ConfigLoader configBootstrap = ConfigLoader.instance();
		configBootstrap.add(ExlpCentralConfigPointer.instance(System.metachart).jaxb(JaxbUtil.instance()).toPath("core"));
		configBootstrap.add(configFile);
		config = configBootstrap.wrap();
		
		logger.debug("Config and Logger initialized");
		return config;
	}
}