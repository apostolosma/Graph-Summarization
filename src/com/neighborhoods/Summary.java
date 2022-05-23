
import graph.IVertex;

import java.awt.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

public class Summary {
    private HashSet<LNode> nodes;
    private HashSet<Summary> targets;
    private HashSet<Summary> sources;
    private int inChannel;
    private String label;
    private int id;
    private Point p; //coordinates
    public Random rand;
    public final String template = "\"\"";

    public double w = 40.0000000000;
    public double h = 40.0000000000;
    public String fill = "\"#808080\"";

    public void setFill(String fill) {
        this.fill = fill;
    }

    public final String fillbg = "\"#000000\"";
    public final String outline = "\"#000000\"";
    public final String pattern = "\"Solid\"";
    public final String stipple = "\"Solid\"";
    public final double lineWidth = 1.0000000000;
    public String type = "\"circle\"";

    public Summary(int id,String label) {
        this.nodes = new HashSet<>();
        this.id = id;
        this.label = label;
        this.rand = new Random();
        this.targets = new HashSet<>();
        this.sources = new HashSet<>();
    }

    public HashSet<Summary> getTargets() {
        return targets;
    }

    public double getx(){return p.getx();}
    public double gety(){return p.gety();}

    public void add(LNode node) {
        if (this.nodes.contains(node) == false) {
            nodes.add(node);
            if(this.label == "S")
                this.label = this.label + "" + node.getlabel();
            else
                this.label = this.label + "," + node.getlabel();
        }
    }
    public void addTarget(Summary s) {
        this.targets.add(s);
    }

    public void addSource(Summary s) {
        this.sources.add(s);
    }

    public int getInChannel() {
        return inChannel;
    }

    public void setInChannel(int inChannel) {
        this.inChannel = inChannel;
    }

    public HashSet<LNode> getNodes() {
        return nodes;
    }

    public void setNodes(HashSet<LNode> nodes) {
        this.nodes = nodes;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void calculatePosition() {
        for(LNode v : this.nodes) {
            if(this.p == null) {
                p = new Point(v.getx(), v.gety());
            }
            if(v.hasCrossEdge() && (v.gety() > this.p.gety())) {
                this.p.sety(v.gety());
            }
        }
    }

    public String randomColorGenerator() {

        int rand_num = rand.nextInt(0xffffff + 1);
        String colorCode = String.format("#%06x", rand_num);

        return "\"" + colorCode + "\"";
    }

    public String printSummary() {
        StringBuilder s = new StringBuilder();
        s.append(this.id + " (" + this.getx() + "," + this.gety()+") :\t");
        for(LNode n : this.nodes) {
            s.append(n.getlabel() + ",\t");
        }
        s.append("\n");
        return s.toString();
    }

    public String toString() {
        if(this.nodes.size() > 1) {
            this.w=80.0000000000;
            this.h=80.0000000000;
        }
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
