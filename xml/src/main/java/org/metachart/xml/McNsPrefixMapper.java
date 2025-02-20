package org.metachart.xml;

public class McNsPrefixMapper  // 2025-02-19 PrefixMapper deactivated
								// extends NamespacePrefixMapper implements NsPrefixMapperInterface
{
    public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix)
    {
    	if("http://www.openfuxml.org".equals(namespaceUri) ){return "ofx";}
    	if("http://www.openfuxml.org/editorial".equals(namespaceUri) ){return "ed";}
    	if("http://www.openfuxml.org/wiki".equals(namespaceUri) ){return "wiki";}
        if("http://www.openfuxml.org/chart".equals(namespaceUri) ){return "chart";}
        if("http://www.openfuxml.org/table".equals(namespaceUri) ){return "table";}
        if("http://www.openfuxml.org/layout".equals(namespaceUri) ){return "layout";}
        if("http://www.openfuxml.org/list".equals(namespaceUri) ){return "l";}
        if("http://www.openfuxml.org/text".equals(namespaceUri) ){return "text";}
        if("http://www.openfuxml.org/media".equals(namespaceUri) ){return "media";}
        
        if("http://www.openfuxml.org/jsf".equals(namespaceUri) ){return "jsf";}
        
        if("http://exlp.sf.net/io".equals(namespaceUri) ){return "io";}
  
        return suggestion;
    }

    public String[] getPreDeclaredNamespaceUris()
    {
    	String[] result = new String[3];
    	result[2] = "http://www.openfuxml.org/chart";
    	result = new String[0];
        return result;
    }
}