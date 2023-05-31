import java.util.*;

public class Search<V> {
    public List<Vertex<V>> breadthFirstSearch(WeightedGraph<V> graph, Vertex<V> start, Vertex<V> end) {
        Map<Vertex<V>, Vertex<V>> parentMap = new HashMap<>();
        Queue<Vertex<V>> queue = new LinkedList<>();
        Set<Vertex<V>> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();

            if (current == end) {
                return constructPath(parentMap, start, end);
            }

            List<Edge<V>> edges = graph.getEdges(current);
            for (Edge<V> edge : edges) {
                Vertex<V> neighbor = edge.getDestination();
                if (!visited.contains(neighbor)) {
                    parentMap.put(neighbor, current);
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }

        return null; // No path found
    }

    public List<Vertex<V>> dijkstraSearch(WeightedGraph<V> graph, Vertex<V> start, Vertex<V> end) {
        Map<Vertex<V>, Double> distanceMap = new HashMap<>();
        Map<Vertex<V>, Vertex<V>> parentMap = new HashMap<>();
        PriorityQueue<Vertex<V>> queue = new PriorityQueue<>(Comparator.comparingDouble(distanceMap::get));
        Set<Vertex<V>> visited = new HashSet<>();

        distanceMap.put(start, 0.0);
        queue.add(start);

        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();

            if (current == end) {
                return constructPath(parentMap, start, end);
            }

            visited.add(current);

            List<Edge<V>> edges = graph.getEdges(current);
            for (Edge<V> edge : edges) {
                Vertex<V> neighbor = edge.getDestination();
                double newDistance = distanceMap.get(current) + edge.getWeight();

                if (!visited.contains(neighbor) && (!distanceMap.containsKey(neighbor) || newDistance < distanceMap.get(neighbor))) {
                    distanceMap.put(neighbor, newDistance);
                    parentMap.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        return null; // No path found
    }

    private List<Vertex<V>> constructPath(Map<Vertex<V>, Vertex<V>> parentMap, Vertex<V> start, Vertex<V> end) {
        List<Vertex<V>> path = new ArrayList<>();
        Vertex<V> current = end;

        while (current != start) {
            path.add(0, current);
            current = parentMap.get(current);
        }

        path.add(0, start);
        return path;
    }
}
