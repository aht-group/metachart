package org.metachart.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.exlp.util.io.LoggerInit;

public class AbstractMcXmlTest extends AbstractAhtUtilsXmlTest
{
	final static Logger logger = LoggerFactory.getLogger(AbstractMcXmlTest.class);
	
	@BeforeClass
    public static void initLogger()
	{
		LoggerInit loggerInit = new LoggerInit("log4junit.xml");	
		loggerInit.addAltPath("config.metachart-xml.test");
		loggerInit.init();
    }
	
	@BeforeClass
	public static void initPrefixMapper()
	{
//		JaxbUtil.setNsPrefixMapper(new McNsPrefixMapper());
	}
	
	protected static Collection<Object[]> initFileNames(String srcDir, String fileSuffix)
	{
		Collection<Object[]> c = new ArrayList<Object[]>();
		File dirSrc = new File(srcDir);
		for(File f : dirSrc.listFiles())
		{
			if(f.getName().endsWith(fileSuffix))
			{
				Object[] o = new Object[] {f};
				c.add(o);
			}
		}
		return c;
	}
}