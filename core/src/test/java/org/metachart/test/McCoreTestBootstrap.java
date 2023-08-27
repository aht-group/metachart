package org.metachart.test;

import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.exlp.exception.ExlpConfigurationException;
import net.sf.exlp.util.config.ConfigLoader;
import net.sf.exlp.util.io.ExlpCentralConfigPointer;
import net.sf.exlp.util.io.LoggerInit;
import net.sf.exlp.util.xml.JaxbUtil;

public class McCoreTestBootstrap
{
	final static Logger logger = LoggerFactory.getLogger(McCoreTestBootstrap.class);
	public final static String xmlConfig = "mc-core.test/config/mc.xml";

	private static Configuration config;
	
	public static Configuration init()
	{
		return init(xmlConfig);
	}
	
	public static Configuration init(String configFile)
	{		
		LoggerInit loggerInit = new LoggerInit("log4j.xml");
		loggerInit.addAltPath("mc-core.test/config");
		loggerInit.init();
		//JaxbUtil.setNsPrefixMapper(new McNsPrefixMapper());
		
		try
		{
			ExlpCentralConfigPointer ccp = ExlpCentralConfigPointer.instance("metachart").jaxb(JaxbUtil.instance());
			ConfigLoader.add(ccp.toFile("mc"));
		}
		catch (ExlpConfigurationException e) {logger.debug("No additional "+ExlpCentralConfigPointer.class.getSimpleName()+" because "+e.getMessage());}
		
		ConfigLoader.add(configFile);
		config = ConfigLoader.init();
		logger.debug("Config and Logger initialized");
		return config;
	}
}