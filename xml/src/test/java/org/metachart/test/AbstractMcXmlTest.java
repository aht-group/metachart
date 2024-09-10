package org.metachart.test;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.exlp.controller.handler.io.log.LoggerBootstrap;
import org.exlp.test.AbstractXmlTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractMcXmlTest <T extends Object> extends AbstractXmlTest<T>
{
	final static Logger logger = LoggerFactory.getLogger(AbstractMcXmlTest.class);
	
	public AbstractMcXmlTest(Class<T> cXml, Path pSuffix)
	{
		super(cXml,Paths.get("geojsf","system","io","jaxb"),pSuffix);
	}
	
//	@BeforeClass
    public static void initLogger()
	{
		LoggerBootstrap.instance().path("metachart/system/io/log").init();
    }
	
//	@BeforeClass
	public static void initPrefixMapper()
	{
//		JaxbUtil.setNsPrefixMapper(new GeoJsfNsPrefixMapper());
	}
}