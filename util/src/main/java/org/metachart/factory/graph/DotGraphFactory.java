package org.metachart.factory.graph;

import org.metachart.xml.graph.Graph;

import net.sf.exlp.util.io.txt.ExlpTxtWriter;

public class DotGraphFactory {
	private ExlpTxtWriter txtWriter;
	private Double ratio,ranksep,nodesep,pad;

	private String mode,model,overlap,splines;
	private String formatedLabel;


	public DotGraphFactory(ExlpTxtWriter txtWriter) {
		this.txtWriter = txtWriter;
		//to-do init config
		this.ratio = 0.7;
		this.ranksep = 0.5;
		this.nodesep = 0.5;
		this.pad = 0.3;
		this.splines = "ortho";
		this.overlap = "prism";
		this.mode = "ipsep";
		this.model = "subset";
		this.formatedLabel = " label =<\n <U><FONT FACE=\"Times New Roman\" POINT-SIZE=\"40\">  %s </FONT></U>\n >\n     labelloc=\"b\"";
	}

	public void beginDotDiagraph(String label, Graph graph) {
		txtWriter.add("digraph "+graph.getCode()+" { ");
		txtWriter.add("");
		if(mode!=null){txtWriter.add("  mode="+mode+";");}
		if(model!=null){txtWriter.add("  model="+model+";");}
		//if(overlap!=null){txtWriter.add("  overlap="+overlap+";");}
		txtWriter.add("  overlap=false;");
		txtWriter.add("  remincross=true;");
		if(nodesep!=null){txtWriter.add("  nodesep="+nodesep+";");}
		if(pad!=null){txtWriter.add("  pad="+pad+";");}
		if(splines!=null){txtWriter.add("  splines="+splines+";");}

		if(ratio!=null){txtWriter.add("  ratio="+ratio+";");}
		if(ranksep!=null){txtWriter.add("  ranksep="+ranksep+";");}
		if(overlap!=null){txtWriter.add("  overlap="+overlap+";");}
		txtWriter.add("  equally=true;");

		if(label!=null) {
			txtWriter.add("");
			txtWriter.add(String.format(formatedLabel, label));
		}

		txtWriter.add("");
	}



	public void endDotDiagraph() {
		txtWriter.add("}");

	}
}
