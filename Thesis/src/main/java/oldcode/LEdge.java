package oldcode;

import java.util.LinkedList;

import neighborhoods.*;

public class LEdge{
	private LNode source;
	private LNode target;
	private Summary sourceSum;
	private Summary targetSum;
	
	final String type = "line";
	final String arrow = "last";
	final String stipple = "Solid";
	final String lineWidth = "1.0000000000";
	final String fill = "#000000";
	private LinkedList<Point> bends; 

	public LEdge(Summary source, Summary target) {
		this.sourceSum = source;
		this.targetSum = target;
		this.bends = new LinkedList<>();
	}

	public LEdge(LNode source,LNode target){
		this.source=source;
		this.target=target;
		bends = new LinkedList<>();
	}
	public void addBend(double x, double y){
		Point p = new Point(x,y);
		this.bends.add(p);
	}
	public LinkedList<Point> getbends(){return this.bends;}
	public LNode getsource(){return source;}
	public LNode gettarget(){return target;}
	public int getsourceId(){return source.getId();}
	public int gettargetId(){return target.getId();}
	public int getsourceSumId(){return sourceSum.getId();}
	public int gettargetSumId(){return targetSum.getId();}

	public String toStringSum(){
		if(sourceSum == targetSum)
				return "";
		String edge="\tedge\n\t[\n";
		edge+="\t\tsource\t"+this.sourceSum.getId()+"\n";
		edge+="\t\ttarget\t"+this.targetSum.getId()+"\n";
		edge+="\t\tgraphics\n\t\t[\n";
		edge+="\t\t\ttype\t\""+this.type+"\"\n";
		edge+="\t\t\ttype\t\""+this.type+"\"\n";
		edge+="\t\t\tarrow\t\""+this.arrow+"\"\n";
		edge+="\t\t\tstipple\t\""+this.stipple+"\"\n";
		edge+="\t\t\tlineWidth\t"+this.lineWidth+"\n";

		if(bends.size()!=0){
			edge+="\t\t\tLine [\n";
			edge+="\t\t\t\tpoint [ x "+sourceSum.getx()/2+" y "+sourceSum.gety()/2+ " ]\n";
			for(Point p:bends){
				edge+="\t\t\t\tpoint [ x "+p.getx()/2+" y "+p.gety()/2+ " ]\n";
			}
			edge+="\t\t\t\tpoint [ x "+targetSum.getx()/2+" y "+targetSum.gety()/2+ " ]\n";
			edge+="\t\t\t]\n";
		}


		edge+="\t\t\tfill\t\""+this.fill+"\"\n";
		edge+="\t\t]\n";
		edge+="\t]\n";

		return edge;
	}
	public String toString(){
		String edge="\tedge\n\t[\n";
		edge+="\t\tsource\t"+this.source.getId()+"\n";
		edge+="\t\ttarget\t"+this.target.getId()+"\n";
		edge+="\t\tgraphics\n\t\t[\n";
		edge+="\t\t\ttype\t\""+this.type+"\"\n";
		edge+="\t\t\ttype\t\""+this.type+"\"\n";
		edge+="\t\t\tarrow\t\""+this.arrow+"\"\n";
		edge+="\t\t\tstipple\t\""+this.stipple+"\"\n";
		edge+="\t\t\tlineWidth\t"+this.lineWidth+"\n";
		
		if(bends.size()!=0){
			edge+="\t\t\tLine [\n";
			edge+="\t\t\t\tpoint [ x "+source.getx()/2+" y "+source.gety()/2+ " ]\n";
			for(Point p:bends){
				edge+="\t\t\t\tpoint [ x "+p.getx()/2+" y "+p.gety()/2+ " ]\n";
			}
			edge+="\t\t\t\tpoint [ x "+target.getx()/2+" y "+target.gety()/2+ " ]\n";
			edge+="\t\t\t]\n";
		}
		
		
		edge+="\t\t\tfill\t\""+this.fill+"\"\n";
		edge+="\t\t]\n";
		edge+="\t]\n";
		
		return edge;
	}
}