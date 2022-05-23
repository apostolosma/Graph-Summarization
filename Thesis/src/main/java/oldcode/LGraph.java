package oldcode;

import semantic.*;
import neighborhoods.*;

import java.util.HashMap;
import java.util.LinkedList;

public class LGraph{
	private LinkedList<LEdge> edges;
	private LNode[] nodes;
	private HashMap<Integer, Summary> summaries;

	private HashMap<Integer, SemanticSummary> semanticSummaries;

	public LGraph(int vertices){
		this.edges = new LinkedList<LEdge>();
		this.nodes = new LNode[vertices];
		this.summaries = new HashMap<>();
		this.semanticSummaries = new HashMap<>();
	}

	public LGraph(HashMap<Integer, Summary> summaries) {
		this.edges = new LinkedList<>();
		this.summaries = summaries;
		this.semanticSummaries = new HashMap<>();
	}
	public void addnode(LNode n){
		this.nodes[n.getId()] = n;
	}

	public void addnode_c(int id,double x,double y,String label){
		LNode ln = new LNode(id,x,y,label);
		this.nodes[id] = ln;

	}

	public HashMap<Integer, SemanticSummary> getSemanticSummaries() {
		return semanticSummaries;
	}

	public void setSemanticSummaries(HashMap<Integer, SemanticSummary> semanticSummaries) {
		this.semanticSummaries = semanticSummaries;
	}

	public LNode getnode(int id){return nodes[id];}
	public LNode[] getnodes(){return nodes;}
	public HashMap<Integer,Summary> getSummaries() {return summaries;}
	public void addedge(LEdge e){
		this.edges.add(e);
	}
	public void setedges(LinkedList<LEdge> edges){this.edges = edges;}
	public void addSum(Summary s) {this.summaries.put(s.getId(),s);}
	public int getnodessize(){return this.nodes.length;}
	public int getedgessize(){return this.edges.size();}
	public int getSumSize(){return this.summaries.size();}
	public LinkedList<LEdge> getEdges(){return this.edges;}
	public String toString_n(int i){
		String node=this.nodes[i].toString();
		return node;
	}

	public String toString_e(int i){
		String e=this.edges.get(i).toString();
		return e;
	}

	public String toString_s() {
		StringBuilder s = new StringBuilder();
		for(Summary sum: this.summaries.values()) {
			s.append(sum.toString());
//			s.append(">\n");
		}

		return s.toString();
	}

	public String toString_es(int i) {
		String e=this.edges.get(i).toStringSum();
		return e;
	}
}