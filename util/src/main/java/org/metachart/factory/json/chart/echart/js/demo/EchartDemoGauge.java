package org.metachart.factory.json.chart.echart.js.demo;

import java.io.IOException;
import java.util.Arrays;

import org.metachart.factory.json.chart.echart.JsonEchartFactory;
import org.metachart.factory.json.chart.echart.data.JsonDataFactory;
import org.metachart.factory.json.chart.echart.data.JsonDatasFactory;
import org.metachart.factory.json.chart.echart.ui.JsonOptionFactory;
import org.metachart.factory.txt.chart.TxtDataFactory;
import org.metachart.interfaces.data.EchartGaugeDataProvider;
import org.metachart.model.json.chart.echart.JsonOption;
import org.metachart.model.json.chart.echart.axis.JsonAxisLabel;
import org.metachart.model.json.chart.echart.axis.JsonAxisLine;
import org.metachart.model.json.chart.echart.axis.JsonAxisTick;
import org.metachart.model.json.chart.echart.color.Color;
import org.metachart.model.json.chart.echart.color.ColorStop;
import org.metachart.model.json.chart.echart.data.JsonData;
import org.metachart.model.json.chart.echart.data.JsonDatas;
import org.metachart.model.json.chart.echart.data.JsonSeries;
import org.metachart.model.json.chart.echart.label.JsonDetail;
import org.metachart.model.json.chart.echart.line.JsonLineStyle;
import org.metachart.model.json.chart.echart.line.JsonPointer;
import org.metachart.model.json.chart.echart.line.JsonSplitLine;
import org.metachart.model.json.chart.echart.style.JsonItemStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchartDemoGauge implements EchartGaugeDataProvider
{
	final static Logger logger = LoggerFactory.getLogger(EchartDemoGauge.class);
		
	public static void demo(JsonEchartFactory jfEchart) throws IOException
	{
		jfEchart.letData("A");
		jfEchart.dataValues(EchartDemoGauge.toData("A"));
		jfEchart.option(JsonOptionFactory.toMagicDatas(EchartDemoGauge.toOption()));
	}
	
	public static JsonOption toOption()
	{
		JsonOptionFactory jfOption = JsonOptionFactory.instance().gauge();

		JsonSeries seriesA = new JsonSeries();
		seriesA.setType(JsonEchartFactory.Type.gauge.toString());
		seriesA.setData(TxtDataFactory.dataId("A"));
		
		JsonLineStyle style = new JsonLineStyle();
		style.setWidth(30);
		style.setColor(Color.of("#67e0e3"));
		
		style.setColor(Color.of(Arrays.asList(ColorStop.of(0.3, "#67e0e3"),ColorStop.of(0.7, "#37a2da"),ColorStop.of(1.0, "#fd666d"))));
		
		JsonAxisLine al = new JsonAxisLine();
		al.setLineStyle(style);
	
		JsonAxisTick tick = new JsonAxisTick();
		tick.setDistance(-30);
		tick.setLength(8);
		
		JsonLineStyle styleTick = new JsonLineStyle();
		styleTick.setWidth(4);
		styleTick.setColor(Color.of("#fff"));
		tick.setLineStyle(styleTick);
		
		seriesA.setAxisLine(al);
		seriesA.setAxisTick(tick);
		
		JsonAxisLabel axisLabel = new JsonAxisLabel();
		axisLabel.setColor("inherit");
		axisLabel.setDistance(40);
		axisLabel.setFontSize(20);
		seriesA.setAxisLabel(axisLabel);
		
		JsonSplitLine splitLine = new JsonSplitLine();
		splitLine.setDistance(-30);
		splitLine.setLength(30);
		
		JsonLineStyle styleSplit = new JsonLineStyle();
		styleSplit.setWidth(4);
		styleSplit.setColor(Color.of("#fff"));
		splitLine.setLineStyle(styleSplit);
		seriesA.setSplitLine(splitLine);
		
		JsonPointer pointer = new JsonPointer();
		JsonItemStyle styleItem = new JsonItemStyle();
		styleItem.setColor("auto");
		pointer.setItemStyle(styleItem);
		seriesA.setPointer(pointer);
		
		JsonDetail detail = new JsonDetail();
		detail.setValueAnimation(true);
		detail.setColor("inherit");
		detail.setFormatter("{value} K");
		seriesA.setDetail(detail);
		
		jfOption.series(seriesA);

		return jfOption.assemble();
	}
	
	public static JsonDatas toDatas()
	{
		JsonDatasFactory jf = JsonDatasFactory.instance();
		jf.add(EchartDemoGauge.toData("A"));
		return jf.assemble();
	}
	
	public static JsonData toData(String seriesId)
	{
		JsonDataFactory jf = JsonDataFactory.instance().id(seriesId).type(JsonDataFactory.Type.value);
		
		jf.value(75);

		return jf.assemble();
	}
}