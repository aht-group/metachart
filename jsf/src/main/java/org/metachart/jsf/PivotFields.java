package org.metachart.jsf;

import java.io.IOException;
import java.util.Map;

import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ListenerFor;
import javax.faces.event.PostAddToViewEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FacesComponent(value="org.metachart.jsf.PivotFields")
@ListenerFor(systemEventClass=PostAddToViewEvent.class)
public class PivotFields extends UINamingContainer
{
final static Logger logger = LoggerFactory.getLogger(PivotFields.class);
	
	private static enum Attribute {name, col, row}

	private String  name;
	private Boolean col;
	private Boolean row;
	
	@Override
	public void processEvent(ComponentSystemEvent event) throws AbortProcessingException
	{
		if(event instanceof PostAddToViewEvent)
		{
			Map<String,Object> map = this.getAttributes();
			this.col         = (Boolean) map.get(Attribute.col.toString());
			this.row         = (Boolean) map.get(Attribute.row.toString());
			this.name        = (String)  map.get(Attribute.name.toString());
			logger.info("Added " +name);
		}
		super.processEvent(event);
	}
	
	@Override
	public void encodeAll(FacesContext ctx) throws IOException
	{
		logger.info("Data already included in Pivot Table");
	}
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	public Boolean getCol() {return col;}
	public void setCol(Boolean col) {this.col = col;}

	public Boolean getRow() {return row;}
	public void setRow(Boolean row) {this.row = row;}

	@Override
	public String getFamily() {
		return null;
	}
}