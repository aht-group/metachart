package org.metachart.factory.xml;

import org.metachart.xml.Axis;

public class XmlAxisFactory
{
	public static enum Orientation{domain,range0,range1};
	
	public static Axis build(Orientation orientation)
	{
		Axis xml = new Axis();
		xml.setCode(orientation.toString());
		
		return xml;
	}
}
