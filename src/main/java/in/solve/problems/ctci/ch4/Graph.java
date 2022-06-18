package in.solve.problems.ctci.ch4;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import in.solve.problems.ctci.ch3.LQueue;

import java.util.List;
import java.util.Map;

public class Graph<T> {

    private GraphNode<T> startingPoint;

    public Graph(T value) {
        this.startingPoint = new GraphNode<>(value);
    }

    public GraphNode<T> getStartingPoint() {
        return startingPoint;
    }

    /*
    Using stack with recursion
     */
    public List<T> dfs() {
        Map<GraphNode<T>, Boolean> visited = Maps.newHashMap();
        List<T> result = Lists.newArrayList();
        visit(result, visited, startingPoint);
        return result;
    }

    private void visit(List<T> result, Map<GraphNode<T>, Boolean> visited, GraphNode<T> currentNode) {
        if (!visited.containsKey(currentNode)) {
            result.add(currentNode.getValue());
            visited.put(currentNode, true);
            for (GraphNode<T> neighbor : currentNode.getNeighbors()) {
                visit(result, visited, neighbor);
            }
        }
    }


    public List<T> bfs() {
        LQueue<GraphNode<T>> queue = new LQueue<>();
        Map<GraphNode<T>, Boolean> visited = Maps.newHashMap();
        List<T> result = Lists.newArrayList();
        queue.add(startingPoint);
        while (!queue.isEmpty()) {
            GraphNode<T> item = queue.remove();
            if (!visited.containsKey(item)) {
                result.add(item.getValue());
                visited.put(item, true);
            }
            for (GraphNode<T> neighbor : item.getNeighbors()) {
                if (!visited.containsKey(neighbor)) {
                    queue.add(neighbor);
                }
            }
        }
        return result;
    }

}
