package org.metachart.processor.export;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BackgroundExportProcessor 
{	
	final static Logger logger = LoggerFactory.getLogger(BackgroundExportProcessor.class);

	private HttpClient client;

	private static String resources = "\"resources\": {\"files\": \"highcharts.js,modules/exporting.js,modules/export-data.js\"}}";
	
	public BackgroundExportProcessor() 
	{
		client = HttpClientBuilder.create().build();
	}
	
	public byte[] exportSVG(String url, String json, String type) throws ClientProtocolException, IOException
	{
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Content-Type", "application/json");
		httpPost.setHeader("accept", "application/json");
		String postString = "{\"infile\": " + json + ", \"type\": \""+type+"\", "+resources;
		logger.info(postString);
		StringEntity stringEntity = new StringEntity(postString);
		httpPost.setEntity(stringEntity);
		HttpResponse httpRespnse = client.execute(httpPost);
//		return EntityUtils.toString(httpRespnse.getEntity(),"UTF-8");
		return EntityUtils.toByteArray(httpRespnse.getEntity());
	}
}