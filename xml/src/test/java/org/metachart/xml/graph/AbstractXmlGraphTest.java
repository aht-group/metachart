package org.metachart.xml.graph;

import org.metachart.test.AbstractMcXmlTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractXmlGraphTest <T extends Object> extends AbstractMcXmlTest<T>
{
	final static Logger logger = LoggerFactory.getLogger(AbstractXmlGraphTest.class);

	public AbstractXmlGraphTest(Class<T> cXml)
	{
		super(cXml,"graph");
	}
}