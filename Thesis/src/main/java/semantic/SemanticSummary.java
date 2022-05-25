package semantic;

import graph.Channel;
import graph.IVertex;
import oldcode.*;

import java.util.Random;

public class SemanticSummary {
    enum SemanticType {
        SOURCE,
        TARGET;
    }

    private SemanticList nodes;
    private String label;
    private int id;
    private SemanticType type;
    private String color;
    private IVertex commonNode;
    private IVertex firstNode, lastNode;
    private double x_coord;


    public SemanticSummary(String label, SemanticType type) {
        this.label = label;
        this.type = type;
        this.nodes = new SemanticList();
        this.color = randomColorGenerator();
    }

    public void setxcoord(double x_coord) {
        this.x_coord = x_coord;
    }


    public double getxcoord() {
        return x_coord;
    }

    public IVertex getFirstNode() {
        return firstNode;
    }

    public void setFirstNode(IVertex firstNode) {
        this.firstNode = firstNode;
    }

    public IVertex getLastNode() {
        return lastNode;
    }

    public void setLastNode(IVertex lastNode) {
        this.lastNode = lastNode;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public IVertex getCommonNode() {
        return commonNode;
    }

    public void setCommonNode(IVertex commonNode) {
        this.commonNode = commonNode;
    }

    public String randomColorGenerator() {
        Random rnd = new Random();
        int r = rnd.nextInt(128) + 128; // 128 ... 255
        int g = rnd.nextInt(128) + 128; // 128 ... 255
        int b = rnd.nextInt(128) + 128; // 128 ... 255

        String colorCode =  String.format("#%02x%02x%02x", r, g, b);
        return "\"" + colorCode + "\"";
    }

    public SemanticList getNodes() {
        return this.nodes;
    }

    public int getNodesize() {return nodes.size();}

    public void setNodes(SemanticList nodes) {
        this.nodes = nodes;
    }

    public String getLabel() {
        return label;
    }

    public void addNode(IVertex node, LGraph LG) {
//        if(!this.nodes.contains(node))
            this.nodes.insert(node, LG);
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

    public SemanticType getType() {
        return type;
    }

    public void setType(SemanticType type) {
        this.type = type;
    }

    public void findInternals(LGraph LG, Channel decomposition, IVertex firstNode, IVertex lastNode) {
        LNode firstLNode = LG.getnode((int)firstNode.getId());
        LNode lastLNode = LG.getnode((int)lastNode.getId());
        for(IVertex potential_vert : decomposition.getVertices())
        {
            LNode potential_lnode = LG.getnode((int)potential_vert.getId());

            if((potential_lnode.gety() > firstLNode.gety()) && (potential_lnode.gety() < lastLNode.gety()) && !this.nodes.contains(potential_lnode)) //!TODO: INVERSE UPSIDE-DOWN
                this.nodes.insert(potential_vert, LG);
        }

        return;
    }


    public void updateBoundaries(IVertex ver,LGraph LG) {
        LNode newNode = LG.getnode((int)ver.getId());
        if(this.firstNode == null && this.lastNode == null) {
            this.firstNode = ver;
            this.lastNode = ver;
            return;
        }

        LNode lfirst = LG.getnode((int)this.firstNode.getId());
        LNode llast = LG.getnode((int)this.lastNode.getId());
        if(newNode.gety() < lfirst.gety()) { //!TODO: INVERSE UPSIDE-DOWN
            this.firstNode = ver;
        }
        if(newNode.gety() > llast.gety()) { //!TODO: INVERSE UPSIDE-DOWN
            this.lastNode = ver;
        }

        return;
    }

    public String toString_inverse(LGraph LG) {
        StringBuilder s = new StringBuilder();

        s.append("Semantic Summary ID: "+this.getId()+"\n w/ ");
        s.append("Common");
        if(this.type == SemanticType.SOURCE) s.append(" Source Node: " + LG.getnode((int)this.commonNode.getId()).getlabel() +"\n");
        else s.append(" Target Node: " + LG.getnode((int)this.commonNode.getId()).getlabel() +"\n");
        s.append(this.nodes.toString_inverse());
        s.append("\nColor:\t" + this.color);
        s.append("\nType:\t" + this.type + "\n\n");

        return s.toString();
    }

    public String toString(LGraph LG) {
        StringBuilder s = new StringBuilder();

        s.append("Semantic Summary ID: "+this.getId()+"\n w/ ");
        s.append("Common");
        if(this.type == SemanticType.SOURCE) s.append(" Source Node: " + LG.getnode((int)this.commonNode.getId()).getlabel() +"\n");
        else s.append(" Target Node: " + LG.getnode((int)this.commonNode.getId()).getlabel() +"\n");
        s.append("Semantic List Tail: " + this.nodes.getTail().data.getlabel() + "\n"+this.nodes.toString());
        s.append("\nColor:\t" + this.color);
        s.append("\nType:\t" + this.type + "\n\n");

        return s.toString();
    }
}
