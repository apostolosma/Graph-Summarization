package neighborhoods;

import java.util.Collection;
import java.util.HashSet;

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
