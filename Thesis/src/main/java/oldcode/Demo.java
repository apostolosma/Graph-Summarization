package oldcode;

import java.util.LinkedList;
import java.io.File;

//import org.json.*;
import graph.*;
import neighborhoods.*;
import semantic.*;

public class Demo{
	public static void main(String[] args){
		Reader r = new Reader();
		
		final File folder = new File("D:\\Graph-Summarization\\Graph-Summarization\\Thesis\\src\\main\\resources\\input\\test");  //todo:replace the source path

		LinkedList<File> files = new LinkedList<File>();
		Main.listFilesForFolder(folder,files);
		try{
			int graphs_num = 0;
			for(File f: files) {
				graphs_num +=1;
				System.out.println("Processing: " + f.getName() );
				SimpleGraph G = r.read(f);
				G.setAdjacency();
				System.out.println("Nodes:"+G.getVertices().size()+" Edges:"+G.getEdges().size());
				IVertex[] ts = Main.setTopologicalIds(G);
				System.out.println("Topological sorting finished");
				Heuristics_v0 h = new Heuristics_v0(G,ts);
			
				LinkedList<Channel> decomposition = h.Heuristic3();
				System.out.println("Decomposition finished");
				//Main.printDecomposition(decomposition);
				Hierarchical pbf = new Hierarchical(G,decomposition);
				pbf.setCordinates();
				System.out.println("Hierarchical Drawing finished");
				
				String dir = "D:\\Graph-Summarization\\Graph-Summarization\\Thesis\\src\\main\\resources\\output\\"+f.getName()+"\\";   //todo:replace the destination path
				File results = new File(dir);
				results.mkdir();
				String fileNameWithOutExt = f.getName().replaceFirst("[.][^.]+$", "");

				// Separates each graph's output
				String fname1 = ""+fileNameWithOutExt+"_semantic.gml";
				String fname2 = ""+fileNameWithOutExt+"_neigh.gml";
				String fname3 = ""+fileNameWithOutExt+".gml";

				// This prints the original PBF
				Hierarchical.print_gml(pbf.getLG(),fname3,dir);
				long start = System.currentTimeMillis();

				//Topology Semantic
				TopologySemantic tsem = new TopologySemantic(pbf.getLG(), pbf.getG(), decomposition, pbf.getX_dist(), pbf.getY_dist());
				tsem.findSemantics(dir);
				// Prints the Semantic Summaries Graph of pbf
				Hierarchical.print_gml(pbf.getLG(),fname1,dir);

				//Topology Neighborhood
				long end = System.currentTimeMillis();
				System.out.println("PBF Finished: " + (end-start) + " ms");
				long start2 = System.currentTimeMillis();
				TopologyNeighborhoods tn = new TopologyNeighborhoods(pbf.getLG(), decomposition, pbf.getX_dist(), pbf.getY_dist());
				tn.findTopolNeigh();

				// Prints the Topology Neighborhood graph
				TopologyNeighborhoods.print_gml(tn.getGraphS(),fname2,dir);
//
//				// Prints the statistics/comparisons of PBF and Topology Neighborhood
				tn.print_statistics(fname2,dir);
				long end2 = System.currentTimeMillis();
				System.out.println("GML file finished " + fname3 + "\nNeighborhood Finished: " + (end2-start2) + " ms" );
			}

			
			
		}catch(Exception e) {
			System.out.print(e);
		}
	}
}