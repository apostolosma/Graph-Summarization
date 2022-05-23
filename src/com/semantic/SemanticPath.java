import graph.IVertex;

public class SemanticPath {
    private Node head;
    private int lsize;

    public SemanticPath() {
        head = null;
        lsize = 0;
    }

    static class Node {
        SemanticSummary s;
        Node next;
        Node(SemanticSummary s) {
            this.s = s;
            this.next = null;
        }
    }

    public Node getHead() {
        return this.head;
    }

    public void insert(SemanticSummary s, LGraph LG) {
        Node newNode = new Node(s);
        Node current = head;
        Node previous = null;

        LNode newfn = LG.getnode((int) newNode.s.getFirstNode().getId());
        while(current != null) {
            LNode fn = LG.getnode((int) current.s.getFirstNode().getId());
            if(fn.gety() > newfn.gety()) {
                newNode.next = current;
                if(previous == null) {
                    head = newNode;
                } else {
                    previous.next = newNode;
                }
                lsize++;
                return;
            }
            previous = current;
            current = current.next;
        }

        if(previous == null) {
            head = newNode;
        } else if (current == null) {
            previous.next = newNode;
        }

        lsize++;
    }

    public int size() {
        return this.lsize;
    }

    public String toString_inverse(LGraph LG) {
        Node current = head;
        StringBuilder s = new StringBuilder();
        s.append("# of summaries in path: " + this.lsize + "\n");
        while(current != null) {
            /*
            if(current.next == null)
                s.append(current.s.getId());
            else
                s.append(current.s.getId() + " -> ");
             */
            s.append(current.s.toString_inverse(LG));

            current = current.next;
        }

        return s.toString();
    }

    public String toString(LGraph LG) {
        Node current = head;
        StringBuilder s = new StringBuilder();
        s.append("# of summaries in path: " + this.lsize + "\n");
        while(current != null) {
            /*
            if(current.next == null)
                s.append(current.s.getId());
            else
                s.append(current.s.getId() + " -> ");
             */
            s.append(current.s.toString(LG));

            current = current.next;
        }

        return s.toString();
    }
}
