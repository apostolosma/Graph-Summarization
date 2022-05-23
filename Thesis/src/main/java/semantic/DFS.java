package semantic;

import graph.IVertex;
import oldcode.*;

import java.util.HashSet;
import java.util.Deque;
import java.util.LinkedList;

public class DFS {
    private HashSet<IVertex> visited = new HashSet<>();
    private LGraph LG;
    private IVertex targetVertex;
    private LNode targetNode;

    public DFS( LGraph LG, IVertex targetVertex)
    {
        this.LG = LG;
        this.targetVertex = targetVertex;
        this.targetNode = LG.getnode((int)targetVertex.getId());
    }

    // This one will visit the last child first
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
