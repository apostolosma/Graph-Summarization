package semantic;

import graph.IVertex;
import oldcode.*;

public class SemanticList {
    private Node head, tail;
    private int lsize;

    public SemanticList() {
        head = null;
        lsize = 0;
    }

    static class Node {
        LNode data;
        Node next;
        Node prev;
        Node(LNode n) {
            this.data = n;
            this.prev = null;
            this.next = null;
        }
    }

    public Node getHead() {
        return this.head;
    }

    public Node getTail() {
        return this.tail;
    }

    public void insert(IVertex v, LGraph LG) {
        LNode n = LG.getnode((int)v.getId());
        Node newNode = new Node(n);
        Node current = head;
        Node previous = null;

        if(this.lsize == 0) {
            head = newNode;
            tail = newNode;
            lsize++;
            return;
        }

        while(current != null) {
            if(newNode.data.gety() < current.data.gety()) {
                newNode.next = current;
                if(previous == null) {
                    head = newNode;
                    newNode.prev = null;
                    current.prev = newNode;
                }else {
                    previous.next = newNode;
                    newNode.prev = previous;
                    current.prev = newNode;
                }

                this.lsize++;
                return;
            }

            previous = current;
            current = current.next;
        }

        previous.next = newNode;
        newNode.prev = previous;
        tail = newNode;
        this.lsize++;
        return;
    }
    /*
    public void insert(IVertex v, LGraph LG) {
        LNode n = LG.getnode((int)v.getId());
        Node newNode = new Node(n);
        Node current = head;
        Node previous = null;

        if(lsize == 0) {
            head = tail = newNode;
            lsize++;
            return;
        }

        while(current != null) {
            if(n.gety() < current.data.gety()) {
                newNode.next = current;
                if(previous != null) {
                    previous.next = newNode;
                    newNode.prev = previous;
                }
                else {
                    head = newNode;
                }
                lsize++;
                return;
            }

            previous = current;
            current = current.next;
        }

        previous.next = newNode;
        newNode.prev = previous;
        tail = newNode;

        lsize++;
    }
     */

    public int size() {
        return this.lsize;
    }

    public boolean contains(LNode n) {
        Node current = head;

        while(current != null) {
            if(current.data == n)
                return true;

            current = current.next;
        }

        return false;
    }

    public void increaseSemantics() {
        Node current = head;

        while(current != null) {
            current.data.increaseSemantics();

            current = current.next;
        }
    }

    public String toString_inverse() {
        Node current = tail;
        StringBuilder s = new StringBuilder();
        s.append("# of nodes: " + this.lsize + "\n");
        while(current != null) {
            s.append("\t" + current.data.getlabel());

            current = current.prev;
        }

        return s.toString();
    }

    public java.lang.String toString() {
        Node current = head;
        StringBuilder s = new StringBuilder();
        s.append("# of nodes: " + this.lsize + "\n");
        while(current != null) {
            s.append("," + current.data.getlabel() );

            current = current.next;
        }

        return s.toString();
    }
}
