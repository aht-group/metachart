package org.metachart.xml.chart;

import org.metachart.test.AbstractMcXmlTest;

public abstract class AbstractXmlChartTest <T extends Object> extends AbstractMcXmlTest<T>
{
	public AbstractXmlChartTest(Class<T> cXml)
	{
		super(cXml,"chart");
	}
}