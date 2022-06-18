package in.solve.problems.ctci.ch4;

import com.google.common.collect.Lists;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GraphTest {

    /*
    A--B--C--D
    |   /  \
    E--F   G--H
     */
    @Test
    public void testGraphDFSAndBFSTraversal() {
        Graph<Character> graph = new Graph<>('A');
        GraphNode<Character> a = graph.getStartingPoint();
        GraphNode<Character> b = new GraphNode<>('B');
        GraphNode<Character> c = new GraphNode<>('C');
        GraphNode<Character> d = new GraphNode<>('D');
        GraphNode<Character> e = new GraphNode<>('E');
        GraphNode<Character> f = new GraphNode<>('F');
        GraphNode<Character> g = new GraphNode<>('G');
        GraphNode<Character> h = new GraphNode<>('H');

        a.addNeighbors(b, e);
        b.addNeighbors(c);
        c.addNeighbors(d, g);
        e.addNeighbors(f);
        f.addNeighbors(c);
        g.addNeighbors(h);

        List<Character> dfsTraversal = graph.dfs();
        assertThat(dfsTraversal, Matchers.is(Lists.newArrayList('A', 'B', 'C', 'D', 'G', 'H', 'F', 'E')));

        List<Character> bfsTraversal = graph.bfs();
        assertThat(bfsTraversal, Matchers.is(Lists.newArrayList('A', 'B', 'E', 'C', 'F', 'D', 'G', 'H')));
    }
}