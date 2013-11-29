package org.metachart.web.mbean.test;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.metachart.factory.xml.XmlDataFactory;
import org.metachart.xml.DataSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named @ViewScoped
public class DatasetBean implements Serializable
{
	final static Logger logger = LoggerFactory.getLogger(DatasetBean.class);
	private static final long serialVersionUID = 1L;

    private DataSet ds1,ds2;

    public DataSet getDsCategories(){return dsCategories;}

    private DataSet dsCategories;

    @PostConstruct
    public void init()
    {
        ds1 = new DataSet();ds1.setLabel("PNBA");
        ds2 = new DataSet();ds2.setLabel("RBT");

        dsCategories = new DataSet();
        dsCategories.getData().add(XmlDataFactory.build("Accipitriformes"));
        ds1.getData().add(XmlDataFactory.build(4));
        ds2.getData().add(XmlDataFactory.build(50));

        dsCategories.getData().add(XmlDataFactory.build("Anseriformes"));
        ds1.getData().add(XmlDataFactory.build(0));
        ds2.getData().add(XmlDataFactory.build(45518));

        dsCategories.getData().add(XmlDataFactory.build("Charadriiformes"));
        ds1.getData().add(XmlDataFactory.build(192315));
        ds2.getData().add(XmlDataFactory.build(14629));

        dsCategories.getData().add(XmlDataFactory.build("Ciconiiformes"));
        ds1.getData().add(XmlDataFactory.build(0));
        ds2.getData().add(XmlDataFactory.build(137));

        dsCategories.getData().add(XmlDataFactory.build("Columbiformes"));
        ds1.getData().add(XmlDataFactory.build(0));
        ds2.getData().add(XmlDataFactory.build(5));

        dsCategories.getData().add(XmlDataFactory.build("Coraciiformes"));
        ds1.getData().add(XmlDataFactory.build(0));
        ds2.getData().add(XmlDataFactory.build(18));

        dsCategories.getData().add(XmlDataFactory.build("Cuculiformes"));
        ds1.getData().add(XmlDataFactory.build(0));
        ds2.getData().add(XmlDataFactory.build(1));

        dsCategories.getData().add(XmlDataFactory.build("Falconiformes"));
        ds1.getData().add(XmlDataFactory.build(0));
        ds2.getData().add(XmlDataFactory.build(3));

        dsCategories.getData().add(XmlDataFactory.build("Galliformes"));
        ds1.getData().add(XmlDataFactory.build(0));
        ds2.getData().add(XmlDataFactory.build(1));

        dsCategories.getData().add(XmlDataFactory.build("Gruiformes"));
        ds1.getData().add(XmlDataFactory.build(0));
        ds2.getData().add(XmlDataFactory.build(136));

        dsCategories.getData().add(XmlDataFactory.build("Passeriformes"));
        ds1.getData().add(XmlDataFactory.build(0));
        ds2.getData().add(XmlDataFactory.build(174));

        dsCategories.getData().add(XmlDataFactory.build("Pelecaniformes"));
        ds1.getData().add(XmlDataFactory.build(3108));
        ds2.getData().add(XmlDataFactory.build(11691));

        dsCategories.getData().add(XmlDataFactory.build("Phoenicopteriformes"));
        ds1.getData().add(XmlDataFactory.build(3410));
        ds2.getData().add(XmlDataFactory.build(12884));

        dsCategories.getData().add(XmlDataFactory.build("Podicipediformes"));
        ds1.getData().add(XmlDataFactory.build(0));
        ds2.getData().add(XmlDataFactory.build(24));

        dsCategories.getData().add(XmlDataFactory.build("Suliformes"));
        ds1.getData().add(XmlDataFactory.build(3546));
        ds2.getData().add(XmlDataFactory.build(9215));

        dsCategories.getData().add(XmlDataFactory.build("Upupiformes"));
        ds1.getData().add(XmlDataFactory.build(0));
        ds2.getData().add(XmlDataFactory.build(0));
    }

    public DataSet getDs1(){return ds1;}
    public DataSet getDs2(){return ds2;}
}