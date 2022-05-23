package neighborhoods;// Java program to find neighborhood overlaps

import graph.*;
import oldcode.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class TopologyNeighborhoods {
    private LinkedList<Channel> decomposition;
    private LinkedList<SummaryChannel> paths;
    private HashMap<Integer, Summary> summaries;

    private LGraph graph0;
    private LGraph graphS;
    private double x_dist;
    private double y_dist;

    public TopologyNeighborhoods(LGraph graph0, LinkedList<Channel> decomposition, double x_dist, double y_dist) {
        this.decomposition = decomposition;
        this.paths = new LinkedList<>();
        this.graph0 = graph0;
        this.summaries = new HashMap<>();
//        this.graphS.setedges(graph0.getEdges());
        this.x_dist = x_dist;
        this.y_dist = y_dist;
    }

    public void findTopolNeigh() {
        System.out.println(this.x_dist + " " + this.y_dist);
        defineSummaries();

        this.graphS = new LGraph(summaries);
        for(Summary s: graphS.getSummaries().values()) {
            s.calculatePosition();
        }

        fixEdges();

//        for(Summary s:graphS.getSummaries().values()) {
//            System.out.println(s.printSummary());
//        }
    }

    public void print_statistics(String file, String directory) throws IOException {
        float node_reduction_perc = ((float)(this.graph0.getnodessize() - this.graphS.getSumSize())) / this.graph0.getnodessize();
        float edge_reduction_perc = ((float)(this.graph0.getedgessize() - this.graphS.getedgessize())) / this.graph0.getedgessize();
        StringBuilder stats = new StringBuilder();
        stats.append(file + ":\n");
        stats.append("\tNodes before: " + this.graph0.getnodessize() + " -> Nodes after: " + this.graphS.getSumSize()+"\n");
        stats.append("\tReduction %: " + 100*node_reduction_perc + " %\n");
        stats.append("\tEdges before: " + this.graph0.getedgessize() + " -> Edges after: " + this.graphS.getedgessize()+"\n");
        stats.append("\tReduction %: " + 100*edge_reduction_perc + " %\n");

        FileWriter wr = new FileWriter(directory+"statistics.txt",true);
        wr.write(stats.toString());
        wr.close();
    }

    public void clearBends() {
        for(LEdge e: graphS.getEdges()) {
            LNode source = e.getsource();
            LNode target = e.gettarget();
            if(source.getx() != target.getx())
                e.getbends().clear();
        }
    }

    public void fixEdges(){
        clearBends();

        for(LEdge e:graph0.getEdges()){
            LNode source = e.getsource();
            LNode target = e.gettarget();
            Summary sourceSum = source.getTopSummary();
            Summary targetSum = target.getTopSummary();
            int internals = findInternals(sourceSum, targetSum);
            if(sourceSum.getTargets().contains(targetSum)) {
               continue;
            }
            sourceSum.addTarget(targetSum);
            targetSum.addSource(sourceSum);
            LEdge newedge = new LEdge(sourceSum, targetSum);

            if(sourceSum.getx() != targetSum.getx()) {
                if (sourceSum.getx() < targetSum.getx()) { // right edge
                    newedge.addBend(sourceSum.getx() + (x_dist)/2, targetSum.gety() - y_dist/10);
                } else { // left edge
                    newedge.addBend(sourceSum.getx() - (x_dist)/2, targetSum.gety() - y_dist/10);
                }
            }
            else if( targetSum.gety() - sourceSum.gety() > y_dist && internals > 0) {
                newedge.addBend((sourceSum.getx() - x_dist/10) -internals*50, (sourceSum.gety() + y_dist/10) + internals*50);
                newedge.addBend((targetSum.getx() - x_dist/10) -internals*50, (targetSum.gety() - y_dist/10) - internals*50);
            }
            graphS.addedge(newedge);

//			LG.addedge(e);
        }
    }

    public int findInternals(Summary source, Summary target) {
        int numberOfInternals=0;
        SummaryChannel sc = this.paths.get(source.getInChannel());
        for (Summary s: sc.getSummaries()) {
            if(s.gety() > source.gety() && s.gety() < target.gety()) {
                numberOfInternals++;
            }
        }
        return numberOfInternals;
    }

    public void defineSummaries() {
        for(int c = 0; c< decomposition.size(); c++) {
            LinkedList<IVertex> lane = decomposition.get(c).getVertices();
            SummaryChannel sc = new SummaryChannel(c);
            this.paths.add(sc);
            for (IVertex ver : lane) {
                LNode v = graph0.getnode((int)ver.getId());
                for(IVertex uver: lane) {
                    LNode u = graph0.getnode((int)uver.getId());
                    if(((u.gety() - v.gety() <= y_dist) && (u.gety() - v.gety() > 0.0)) || ((v.gety() - u.gety() <= y_dist) && (v.gety() - u.gety() > 0.0))) {
                        if(v.getTopSummary() != null) {
                            v.getTopSummary().add(u);
                            u.setTopSummary(v.getTopSummary());
                        }
                        else if(u.getTopSummary() != null) {
                            u.getTopSummary().add(v);
                            v.setTopSummary(u.getTopSummary());
                        }
                    }
                    else {
                        if(v.getTopSummary() == null) {
                            Summary newsum = new Summary(this.summaries.size()+1, "S");
                            newsum.add(v);
                            v.setTopSummary(newsum);
                            sc.addSummary(newsum);
                            newsum.setInChannel(sc.getId());

                            summaries.put(newsum.getId(), newsum);
                        }
                    }
                }
            }

        }
    }

    public static void print_gml(LGraph LG){
        try {
            StringBuilder gml = new StringBuilder();
            gml.append("graph\n[\n\tdirected\t1\n");

            gml.append(LG.toString_s());
            for(int i=0;i<LG.getedgessize();++i){
                gml.append(LG.toString_es(i));
            }

            gml.append("]\n");

            //System.out.println(gml);
            FileWriter myWriter = new FileWriter("GMLoutput.gml");
            myWriter.write(gml.toString());
            myWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public static void print_gml(LGraph LG,String name,String Directory){
        try {

            StringBuilder gml = new StringBuilder();
            gml.append("graph\n[\n\tdirected\t1\n");

            gml.append(LG.toString_s());
            for(int i=0;i<LG.getedgessize();++i){
                gml.append(LG.toString_es(i));
            }

            gml.append("]\n");
//			System.out.println(name);
            FileWriter myWriter = new FileWriter(Directory+name);//"GMLoutput.gml");
            myWriter.write(gml.toString());
            myWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    /*
    public void defineSummaries() {
        for(LNode u: graph0.getnodes()) { // e = (u,v)
//            System.out.println("Looking for path of " + u.getlabel() + " " + u.getx() + " " + u.gety());
            Channel path = u.getInChannel();
            assert(path != null);
            for(IVertex ver: path.getVertices()) {
                LNode v = graph0.getnode((int)ver.getId());
                if(v != u && (u.getx() == v.getx())) {
                    if(((u.gety() - v.gety() <= y_dist) && (u.gety() - v.gety() > 0.0))) {
                        System.out.println(u.getlabel() + " " + v.getlabel());
                        if(u.getTopSummary() != null) {
                            u.getTopSummary().add(v);
                            v.setTopSummary(u.getTopSummary());
                            v.getTopSummary().setFill(v.getTopSummary().randomColorGenerator());
                        }
                        else if(v.getTopSummary() != null) {
                            v.getTopSummary().add(u);
                            u.setTopSummary(v.getTopSummary());
                            u.getTopSummary().setFill(u.getTopSummary().randomColorGenerator());
                        }
                    } else {
                        if(v.getTopSummary() == null) {
                            System.out.println("Creating new summary for " + v.getlabel());
                            Summary newsum = new Summary(graphS.getSumSize() + 1, "S");
                            newsum.add(v);
                            v.setTopSummary(newsum);
                            graphS.addSum(newsum);
                        }
                    }
                }

            }
        }
    }
    */
    public LinkedList<Channel> getDecomposition() {
        return decomposition;
    }

    public void setDecomposition(LinkedList<Channel> decomposition) {
        this.decomposition = decomposition;
    }


    public LGraph getGraphS() {
        return graphS;
    }

}
