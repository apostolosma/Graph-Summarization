import graph.IVertex;

import java.util.HashSet;
import java.util.Deque;
import java.util.LinkedList;

public class DFS {
    private static HashSet<IVertex> visited = new HashSet<>();

    // This one will visit the last child first
    public static boolean traverse(IVertex startVertex, IVertex targetVertex) {
        Deque<IVertex> stack = new LinkedList<>();
        stack.push(startVertex);
        while (!stack.isEmpty()) {
            IVertex current = stack.pop();
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
