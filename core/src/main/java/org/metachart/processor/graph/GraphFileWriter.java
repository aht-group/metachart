package org.metachart.processor.graph;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.exlp.shell.spawn.Spawn;

public class GraphFileWriter
{
	final static Logger logger = LoggerFactory.getLogger(GraphFileWriter.class);

	private String type;
	private String executeablePath; public String getExecuteablePath() {return executeablePath;} public void setExecuteablePath(String executeablePath) {this.executeablePath = executeablePath;}

	public GraphFileWriter(String type)
	{
		this.type=type;
		executeablePath = "/opt/homebrew/bin/";
		//assuming dot for windows its directly installed in path folder
		if (Files.notExists(Paths.get(executeablePath))) {
			executeablePath ="";
		}
	}

	public void svg(File src, File dst) throws IOException, ClassNotFoundException
	{
		StringBuffer sb = new StringBuffer();
		sb.append(executeablePath).append(type);
		sb.append(" -Tsvg");
		sb.append(" ").append(src.getAbsolutePath());
		sb.append(" -o ");
		sb.append(dst.getAbsolutePath());

		logger.info(sb.toString());

		Spawn spawn = new Spawn(sb.toString());
//		spawn.debug();
		spawn.run();
	}

	public void pdf(File src, File dst) throws IOException
	{
		StringBuffer sb = new StringBuffer();
		sb.append(executeablePath).append(type);
		sb.append(" -Tpdf");
		sb.append(" ").append(src.getAbsolutePath());
		sb.append(" -o ");
		sb.append(dst.getAbsolutePath());

		logger.info(sb.toString());

		Spawn spawn = new Spawn(sb.toString());
//		spawn.debug();
		spawn.run();
	}
}