package oldcode;

import graph.Channel;
import neighborhoods.*;
import semantic.*;

import java.util.HashSet;

public class LNode{
	private int id;
	private Point p;
	private String label;

	/* Tolis added Channel */
    private final String[] colorPalette = {"\"#32CD32\"","\"#E12120\"","\"#743E0C\"", // Green, Red, Brown
                                            "\"#EFD30B\"","\"#F18405\"","\"#1e90ff\"", // Yellow, Orange, Blue
                                            "\"#800080\""}; // Purple
	private Channel inChannel;
	private Summary topSumm;
	private int numOfSemantics;
	private HashSet<SemanticSummary> semanticSummaries;


	public void addSemanticSummary(SemanticSummary s) {
		semanticSummaries.add(s);
	}

	public HashSet<SemanticSummary> getSemanticSummaries() {
		return semanticSummaries;
	}

	public void setSemanticSummaries(HashSet<SemanticSummary> semanticSummaries) {
		this.semanticSummaries = semanticSummaries;
	}

	public int getNumOfSemantics() {
		return numOfSemantics;
	}

	public void increaseSemantics() {
		this.numOfSemantics ++;
	}

	public Summary getTopSummary() {
		return topSumm;
	}

	public void setTopSummary(Summary topSumm) {
		this.topSumm = topSumm;
	}

	public boolean crossEdge = false;
	/* */
	public final String template = "\"\"";

	public final double w = 40.0000000000;
	public final double h = 40.0000000000;
	public String fill = "\"#808080\"";
	public final String fillbg = "\"#000000\"";
	public String outline = "\"#000000\"";
	public final String pattern = "\"Solid\"";
	public final String stipple = "\"Solid\"";
	public final double lineWidth = 1.0000000000;
	public final String type = "\"Ellipse\"";
	public Channel getInChannel() {
		return inChannel;
	}

	public void setInChannel(Channel inChannel) {
		this.inChannel = inChannel;
	}

	public int getId(){return id;}
	public double getx(){return p.getx();}
	public double gety(){return p.gety();}
	public void setx(double x){ p.setx(x);}
	public void sety(double y){ p.sety(y);}
	public void setId(int id){this.id=id;}
	public void setLabel(int label){this.label=(""+label);}
	public boolean hasCrossEdge(){return this.crossEdge;}

	public void updateColor() {
	    if(this.numOfSemantics == 0)
	        return;
		else if((this.numOfSemantics/2) >= 7) {
		    this.fill = colorPalette[6];
        }
        else {
            this.fill = colorPalette[(this.numOfSemantics/2)];
        }
	}

	public LNode(int id,double x,double y,String label){
		this.id = id;
		this.p = new Point(x,y);
		this.label = label;
		this.semanticSummaries = new HashSet<>();
		this.numOfSemantics = 0;
	}
	public String getlabel(){return label;}

	public String toString(){
		this.updateColor();
		String node="";
		node+="\tnode\n\t[\n";
		node+=("\t\tid\t"+this.getId()+"\n");
		node+=("\t\ttemplate\t"+this.template+"\n");
		node+=("\t\tlabel\t\""+this.label+"\"\n");
		
		node+=("\t\tgraphics\n\t\t[\n");
		node+=("\t\t\tx\t"+this.getx()/2+"\n");
		node+=("\t\t\ty\t"+this.gety()/2+"\n");
		node+=("\t\t\tw\t"+this.w+"\n");
		node+=("\t\t\th\t"+this.h+"\n");
		node+=("\t\t\tfill\t"+this.fill+"\n");
		node+=("\t\t\toutline\t"+this.outline+"\n");
		node+=("\t\t\tpattern\t"+this.pattern+"\n");
		node+=("\t\t\tstipple\t"+this.stipple+"\n");
		node+=("\t\t\tlineWidth\t"+this.lineWidth+"\n");
		node+=("\t\t\ttype\t"+this.type+"\n");
		node+=("\t\t]\n");
		node+=("\t]\n");
		
		
		return node;
	}


}