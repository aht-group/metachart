package org.metachart.web.mbean.test;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named @ViewScoped
public class VersionBean implements Serializable
{
	final static Logger logger = LoggerFactory.getLogger(VersionBean.class);
	private static final long serialVersionUID = 1L;

    private Date date;

    @PostConstruct
    public void init()
    {
        date = new Date();
    }

    public Date getDate(){return date;}
}