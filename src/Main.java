import java.util.List;

public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>();
        Vertex<String> a = new Vertex<>("A");
        Vertex<String> b = new Vertex<>("B");
        Vertex<String> c = new Vertex<>("C");
        Vertex<String> d = new Vertex<>("D");
        Vertex<String> e = new Vertex<>("E");
        Vertex<String> f = new Vertex<>("F");

        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);
        graph.addVertex(e);
        graph.addVertex(f);

        graph.addEdge(a, b, 2);
        graph.addEdge(a, c, 4);
        graph.addEdge(b, d, 3);
        graph.addEdge(c, d, 1);
        graph.addEdge(c, e, 2);
        graph.addEdge(d, e, 3);
        graph.addEdge(d, f, 5);
        graph.addEdge(e, f, 1);

        Search<String> search = new Search<>();
        List<Vertex<String>> bfsPath = search.breadthFirstSearch(graph, a, f);
        List<Vertex<String>> dijkstraPath = search.dijkstraSearch(graph, a, f);

        System.out.println("BFS Path: " + bfsPath); // [A, C, E, F]
        System.out.println("Dijkstra Path: " + dijkstraPath); // [A, C, E, F]
    }
}
