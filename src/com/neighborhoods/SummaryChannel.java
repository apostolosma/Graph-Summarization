import graph.IVertex;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class SummaryChannel {
    private int id;
//    private LinkedList<Summary> summaries = new LinkedList<>();
    private Collection<Summary> summaries;

    public SummaryChannel(int id) {
        this.id = id;
        this.summaries = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public Collection<Summary> getSummaries() {
        return this.summaries;
    }

    public void addSummary(Summary s) {
        this.summaries.add(s);
    }
}
