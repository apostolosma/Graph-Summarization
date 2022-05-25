package semantic;

import graph.*;
import oldcode.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class TopologySemantic {
    private LGraph LG;
    private SimpleGraph G;
    private SemanticPath[] semanticSummaries;
    private int semanticCounter;
    private LinkedList<Channel> decomposition;
    private int decompositionSize;
    private double x_dist, y_dist;
    private int hidInfoTypeCounter;

    public TopologySemantic(LGraph LG, SimpleGraph G, LinkedList<Channel> decomposition, double x_dist, double y_dist) {
        this.LG = LG;
        this.G = G;
        this.decomposition = decomposition;
        this.decompositionSize = decomposition.size();
        this.semanticSummaries = new SemanticPath[this.decompositionSize];
        for(int i = 0; i < this.decompositionSize; i++) {
            this.semanticSummaries[i] = new SemanticPath();
        }
        this.semanticCounter = 0;
        this.x_dist = x_dist;
        this.y_dist = y_dist;
        this.hidInfoTypeCounter = 0;
    }

    public void findSemantics(String directoryname) throws IOException {
        StringBuilder sb = new StringBuilder();

        defineSummaries();

        // Uncomment this if you want to create semantics.txt
        sb.append("# of summaries: " + this.semanticCounter);
        for(int i = 0; i < this.decompositionSize; i++) {
            if(this.semanticSummaries[i].size() > 0)
                sb.append( "Path["+(i+1) + "]:\n\t" + this.semanticSummaries[i].toString(LG));
        }
        FileWriter f = new FileWriter(directoryname+"semantic_summaries.txt",true);
        f.write(sb.toString());
        f.close();


        overlappingSummaries(0.8, true, directoryname);
    }

    public void defineSummaries() {
        HashMap<Double, SemanticSummary> sources;
        HashMap<Double, SemanticSummary> targets;
        for(IVertex v : G.getVertices()) {
            sources = commonSource(v);
            targets = commonTarget(v);
            for(SemanticSummary sum: sources.values()) {
                if(sum.getNodesize() > 1 && (sum.getxcoord() != LG.getnode((int)v.getId()).getx())) {
                    sum.setCommonNode(v);
                    sum.findInternals(LG, decomposition.get(getDecompositionPath(sum)), sum.getFirstNode(), sum.getLastNode());
                    sum.setId(++this.semanticCounter);
                    this.semanticSummaries[getDecompositionPath(sum)].insert(sum, LG);
                    sum.getNodes().increaseSemantics();
                }
            }
            for(SemanticSummary sum: targets.values()) {
                if(sum.getNodesize() > 1 && (sum.getxcoord() != LG.getnode((int)v.getId()).getx())) {
                    sum.setCommonNode(v);
                    sum.findInternals(LG, decomposition.get(getDecompositionPath(sum)), sum.getFirstNode(), sum.getLastNode());
                    sum.setId(++this.semanticCounter);
                    this.semanticSummaries[getDecompositionPath(sum)].insert(sum, LG);
                    sum.getNodes().increaseSemantics();
                }
            }
        }
    }

    public void overlappingSummaries(double threshold, boolean rigorousConnection, String directoryname) throws IOException {
        SemanticList list;
        LNode start;
        LinkedList<LNode> oNodes;
        StringBuilder build = new StringBuilder();
        String connectionType;

        for(int i = 0; i < this.decompositionSize; i++) {
            SemanticPath.Node current = this.semanticSummaries[i].getHead();

            while (current != null) {

                LNode lastCur = LG.getnode((int)current.s.getLastNode().getId());
                LNode commonNodeCur = LG.getnode((int)current.s.getCommonNode().getId());
                SemanticPath.Node iter = current.next;
                while(iter != null) {
                    oNodes = new LinkedList<>();
                    int overlappingNodes = 0;
                    LNode firstIt = LG.getnode((int) iter.s.getFirstNode().getId());
                    LNode lastIt = LG.getnode((int) iter.s.getLastNode().getId());
                    LNode commonNodeIt = LG.getnode((int)iter.s.getCommonNode().getId());
                    if (lastCur.gety() >= firstIt.gety()) {
                        start = LG.getnode((int)iter.s.getFirstNode().getId()); // always list is sorted

                        if(lastIt.gety() <= lastCur.gety()) {
                            list = iter.s.getNodes();
                        } else {
                            list = current.s.getNodes();
                        }
                        SemanticList.Node it = list.getTail();
                        while(it != null) {
                            if(it.data == start) {
                                oNodes.add(it.data);
                                overlappingNodes++;
                                break;
                            }

                            oNodes.add(it.data);
                            overlappingNodes++;
                            it = it.prev;
                        }

                    }
                    if(!rigorousConnection) {
                        if (((double) overlappingNodes / current.s.getNodesize()) >= threshold && current.s.getType() == iter.s.getType()) {
//                            System.out.println("Semantic Summary ID: " + current.s.getId() +
//                                    " is " + (double) overlappingNodes / current.s.getNodesize() * 100 + "% in Semantic Summary ID: " + iter.s.getId() +
//                                    " with " + overlappingNodes + " nodes"
//                            );
                            //                        System.out.println("\tCommon nodes:");
                            //                        for(LNode l: oNodes) {
                            //                            System.out.print("\t\t"+l.getlabel());
                            //                        }
                            //                        System.out.println();
//                            System.out.println("Possible Hidden Information: " + current.s.getCommonNode().getLabel() + " & " + iter.s.getCommonNode().getLabel());
                            print_connection(current,iter,commonNodeCur,commonNodeIt,directoryname);
                        }
                        if (((double) overlappingNodes / iter.s.getNodesize()) >= threshold && current.s.getType() == iter.s.getType()) {

//                            build.append("Semantic Summary ID: " + iter.s.getId() +
//                                    " is " + (double) overlappingNodes / iter.s.getNodesize() * 100 + "% in Semantic Summary ID: " + current.s.getId() +
//                                    " with " + overlappingNodes + " nodes"
//                            );
                            //                        System.out.println("\tCommon nodes:");
                            //                        for (LNode l : oNodes) {
                            //                            System.out.print("\t\t" + l.getlabel());
                            //                        }
                            //                        System.out.println();
//                            build.append("Possible Hidden Information: " + current.s.getCommonNode().getLabel() + " & " + iter.s.getCommonNode().getLabel());
                            print_connection(current,iter,commonNodeCur,commonNodeIt,directoryname);
                        }
                    } else {
                        if (((double) overlappingNodes / current.s.getNodesize()) >= threshold && ((double) overlappingNodes / iter.s.getNodesize()) >= threshold && current.s.getType() == iter.s.getType())
                        {
                            print_connection(current,iter,commonNodeCur,commonNodeIt,directoryname);

                        }
                    }

                    iter = iter.next;
                }

                current = current.next;
            }
        }


    }

    public int getDecompositionPath(SemanticSummary s) {
        return ((int) s.getxcoord())/((int) x_dist);
    }

    public void print_connection(SemanticPath.Node current,SemanticPath.Node iter,LNode commonNodeCur, LNode commonNodeIt, String directoryname) throws IOException {
        IVertex startVer, targetVer;
        String connectionType;
        StringBuilder build = new StringBuilder();

        if(commonNodeIt.gety() < commonNodeCur.gety())
        {
            startVer = iter.s.getCommonNode();
            targetVer = current.s.getCommonNode();
        }
        else
        {
            startVer = current.s.getCommonNode();
            targetVer = iter.s.getCommonNode();
        }
        DFS dfs = new DFS(LG, targetVer);
        if(startVer.getAdjacentSources().contains(targetVer))
            connectionType = "directly";
        else if(dfs.traverse(startVer,targetVer))
            connectionType = "indirectly";
        else
            connectionType = "hidden";

        if(connectionType == "hidden")
        {
            commonNodeCur.type = "\"octagon\"";
            commonNodeIt.type = "\"octagon\"";
            commonNodeCur.outlineWidth = 3;
            commonNodeIt.outlineWidth = 3;
        }

        build.append(current.s.getCommonNode().getLabel() + " & " + iter.s.getCommonNode().getLabel() +" connected "+connectionType +
                " through S"+current.s.getId()+" & S"+iter.s.getId()+", semantic summary type: " +current.s.getType()+" \n");

        FileWriter f = new FileWriter(directoryname+"hidden_connections.txt",true);
        f.write(build.toString());
        f.close();
    }

    public HashMap<Double, SemanticSummary> commonSource(IVertex v) {
        HashMap<Double, SemanticSummary> source = new HashMap<Double, SemanticSummary>();
        for(IVertex u: v.getAdjacentTargets()) { // e1 = (v,u1), e2 = (v,u2)
            Double xcoord = LG.getnode((int)u.getId()).getx();
            LNode un = LG.getnode((int)u.getId());
            if(source.get(xcoord) == null) {
                SemanticSummary s = new SemanticSummary("S", SemanticSummary.SemanticType.SOURCE);
                s.setxcoord(xcoord);
                s.addNode(u, LG);
                s.setCommonNode(v);
                s.updateBoundaries(u, LG);
                source.put(xcoord, s);
            } else {
                if(!source.get(xcoord).getNodes().contains(un)) {
                    source.get(xcoord).addNode(u, LG);
                }
                source.get(xcoord).updateBoundaries(u, LG);
            }

        }
        return source;
    }

    public HashMap<Double, SemanticSummary> commonTarget(IVertex v) {
        HashMap<Double, SemanticSummary> target = new HashMap<Double, SemanticSummary>();
//        System.out.println("--------------------------------------\nTargets");
        for(IVertex u: v.getAdjacentSources()) { // e1 = (u1,v), e2 = (u2,v)
            Double xcoord = LG.getnode((int) u.getId()).getx();
            LNode un = LG.getnode((int)u.getId());
            if (target.get(xcoord) == null) {
//                System.out.println("\tCreating new summary for xcoord: " + xcoord);
                SemanticSummary s = new SemanticSummary("S", SemanticSummary.SemanticType.TARGET);
                s.setxcoord(xcoord);
                s.addNode(u, LG);
                s.updateBoundaries(u, LG);
                target.put(xcoord, s);
            } else {
//                System.out.println("\tAdding to summary: "+target.get(xcoord).getCommonNode()+" w/ xcoord:" + xcoord + " node u:" + u.getId());
                if(!target.get(xcoord).getNodes().contains(un)) {

                    target.get(xcoord).addNode(u, LG);
                }
                target.get(xcoord).updateBoundaries(u, LG);
            }
        }
//        System.out.println("--------------------------------------");
        return target;
    }

    public static void print_gml(LGraph LG,String name,String Directory){
        try {
            StringBuilder gml = new StringBuilder();
            gml.append("graph\n[\n\tdirected\t1\n");

            for(int i=0;i<LG.getnodessize();++i){
                gml.append(LG.toString_n(i));
            }

            for(int i=0;i<LG.getedgessize();++i){
                gml.append(LG.toString_e(i));
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

}
