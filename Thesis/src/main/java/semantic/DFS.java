package semantic;

import graph.IVertex;
import oldcode.*;

import java.util.*;

public class DFS {
    private HashSet<IVertex> visited = new HashSet<>();
    private HashSet<LNode> visitedNodes = new HashSet<>();
    private LGraph LG;
    private IVertex targetVertex;
    private LNode targetNode;

    public DFS( LGraph LG, IVertex targetVertex)
    {
        this.LG = LG;
        this.targetVertex = targetVertex;
        this.targetNode = LG.getnode((int)targetVertex.getId());
    }

    public void handleQueue(PriorityQueue queue, IVertex vertex, IVertex targetLimit)
    {
        QueueComparator comp = new QueueComparator(LG);
        for(IVertex target: vertex.getAdjacentTargets())
        {
            if(comp.compare(target,targetLimit) == 1 && !queue.contains(target)) // if node exists before target
                queue.add(target);
        }
    }

    /**
     * Better version of DFS
     * @param startVertex
     * @param targetVertex
     * @return
     */
    public boolean traverse2(IVertex startVertex, IVertex targetVertex)
    {
        PriorityQueue queue = new PriorityQueue(this.LG.getnodessize(), new QueueComparator(LG));
        visited.clear();

        handleQueue(queue, startVertex, targetVertex);
        while(!queue.isEmpty())
        {
            IVertex current = (IVertex) queue.poll();
            if (!visited.contains(current)) {
                visited.add(current);

                // Indirectly connected vertices
                if(current == targetVertex)
                    return true;

                handleQueue(queue, current, targetVertex);
            }
        }
        return false;
    }

    // Using a stack
    public boolean traverse(IVertex startVertex, IVertex targetVertex) {
        Deque<IVertex> stack = new LinkedList<>();
        stack.push(startVertex);

        while (!stack.isEmpty()) {
            IVertex current = stack.pop();
            LNode currentNode = this.LG.getnode((int)current.getId());
            if (!visited.contains(current)) {
                visited.add(current);

                // Indirectly connected vertices
                if(current == targetVertex)
                    return true;

                current.getAdjacentTargets().forEach(stack::push);
            }
        }

        return false;
    }
}
