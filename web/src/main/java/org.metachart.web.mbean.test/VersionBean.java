package org.metachart.web.mbean.util;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import net.sf.ahtutils.prototype.web.mbean.PrototypeMenuBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named @ViewScoped
public class MenuBean implements Serializable
{
	final static Logger logger = LoggerFactory.getLogger(MenuBean.class);
	private static final long serialVersionUID = 1L;
	
}