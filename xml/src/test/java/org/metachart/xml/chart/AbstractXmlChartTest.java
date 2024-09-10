package org.metachart.xml.chart;

import java.nio.file.Paths;

import org.metachart.test.AbstractMcXmlTest;

public abstract class AbstractXmlChartTest <T extends Object> extends AbstractMcXmlTest<T>
{
	public AbstractXmlChartTest(Class<T> cXml)
	{
		super(cXml,Paths.get("chart"));
	}
}