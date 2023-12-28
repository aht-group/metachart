package org.metachart.factory.xml.graph;

import org.metachart.model.xml.graph.Dot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlDotFactory
{
	final static Logger logger = LoggerFactory.getLogger(XmlDotFactory.class);
	
	public static Dot build(){return new Dot();}
	
	public static Dot build(String text)
	{
		Dot dot = build();
		dot.setValue(text);
		return dot;
	}
}