package org.metachart.xml.chart;

import java.io.File;

import org.metachart.test.AbstractMcXmlTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractXmlAccessTest extends AbstractMcXmlTest
{
	final static Logger logger = LoggerFactory.getLogger(AbstractXmlAccessTest.class);
	
	protected static final String rootDir = "src/test/resources/data/xml/chart";
	protected static File fXml;
}