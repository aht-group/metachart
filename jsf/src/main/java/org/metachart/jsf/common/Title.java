package org.metachart.jsf.common;

import java.io.IOException;
import java.util.Objects;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;

import org.exlp.util.jx.ComponentAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FacesComponent(value="org.metachart.jsf.common.Title")
public class Title extends UIComponentBase
{
	final static Logger logger = LoggerFactory.getLogger(Title.class);
	
	private static enum Attribute {text}

    private String text; public void setText(String text) {this.text = text;} public String getText() {return text;}

	@Override public String getFamily() { return "javax.faces.NamingContainer";}
    
    @Override public void encodeAll(FacesContext ctx) throws IOException
	{
    	ComponentAttribute.debugOnInfo(true);
    	if(Objects.isNull(text)) {text = ComponentAttribute.toString(ctx,this,Attribute.text,text);}
    	ComponentAttribute.debugOnInfo(false);
    	logger.info(text);
	}
}