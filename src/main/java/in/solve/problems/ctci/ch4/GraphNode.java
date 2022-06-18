package in.solve.problems.ctci.ch4;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Objects;

public class GraphNode<T> {
    private T value;
    private List<GraphNode<T>> neighbors = Lists.newArrayList();

    public GraphNode(T value) {
        this.value = value;
    }

    public void addNeighbors(GraphNode<T> ...neighbors) {
        for (GraphNode<T> neighbor : neighbors) {
            if (!this.neighbors.contains(neighbor)) {
                this.neighbors.add(neighbor);
                neighbor.addNeighbors(this);
            }
        }
    }

    public T getValue() {
        return value;
    }

    public List<GraphNode<T>> getNeighbors() {
        return neighbors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphNode<?> graphNode = (GraphNode<?>) o;
        return Objects.equals(value, graphNode.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "GraphNode{" +
                "value=" + value +
                '}';
    }
}
