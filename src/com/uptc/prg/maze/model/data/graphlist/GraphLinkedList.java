package com.uptc.prg.maze.model.data.graphlist;

import com.uptc.prg.maze.model.data.graphlist.list.LinkedList;

/**
 * @param <V> Data type of the vertex.
 * @param <E> Data type of the edges.
 */
public class GraphLinkedList<V, E extends Comparable<E>> extends LinkedList<Vertex<V, E>> {

    public GraphLinkedList(Vertex<V, E> startVertex) {
        this.addVertex(startVertex);
    }

    public GraphLinkedList() {
    }

    /*====================================== PUBLIC METHODS ======================================*/

    public final Vertex<V, E> addVertex(Vertex<V, E> vertex) {
        if (this.searchVertex(vertex) == null) {
            this.addToList(vertex);
            return vertex;
        } else {
            return this.searchVertex(vertex);
        }
    }

    public final void addPath(Path<? extends V, ? extends E> path) {
        Vertex<V, E> startVertex = this.addVertex(new Vertex<>(path.getStartVertexData()));
        Vertex<V, E> endVertex = this.addVertex(new Vertex<>(path.getEndVertexData()));
        if (path.getEdgeType() == EdgeType.DIRECTED) {
            this.addDirectedPaths(startVertex, endVertex, path.getEdgeData());
        } else {
            this.addUndirectedPath(startVertex, endVertex, path.getEdgeData());
        }
    }

    public final void addDirectedPaths(Vertex<V, E> startVertex, Vertex<V, E> endVertex, E pathData) {
        startVertex.addEdge(new Edge<>(endVertex, pathData, EdgeType.DIRECTED));
        endVertex.addEdge(new Edge<>(startVertex, pathData, EdgeType.DIRECTED));
    }

    public final void addUndirectedPath(Vertex<V, E> startVertex, Vertex<V, E> endVertex, E pathData) {
        startVertex.addEdge(new Edge<>(endVertex, pathData, EdgeType.UNDIRECTED));
    }

    /**
     * Try to find on the graph the parameter {@link Vertex}, if is found the {@link Vertex} from
     * the graph is returned, else, returns {@literal null}.
     *
     * @param vertex The {@link Vertex} to search on the graph.
     * @return The found {@link Vertex} from the graph, {@literal null} otherwise.
     */
    public final Vertex<V, E> searchVertex(Vertex<V, E> vertex) {
        Node<Vertex<V, E>> foundNode = this.getHead();
        while (foundNode != null) {
            if (foundNode.getData().getVertexData() == vertex.getVertexData()) {
                return foundNode.getData();
            } else {
                foundNode = foundNode.getNext();
            }
        }
        return null;
    }

    public final int calculateAdjacency(Vertex<V, E> vertex) {
        int adjacencyAmount = 0;

        Vertex<V, E> auxiliaryVertex = this.searchVertex(vertex);
        if (auxiliaryVertex != null) {
            Node<Edge<V, E>> auxiliaryEdge = auxiliaryVertex.getHead();
            while (auxiliaryEdge != null) {
                if (auxiliaryEdge.getData().getTargetVertex() != null) {
                    adjacencyAmount++;
                }
                auxiliaryEdge = auxiliaryEdge.getNext();
            }
            return adjacencyAmount;
        } else {
            return 0;
        }
    }

    public final String[] calculateIncidence(Vertex<V, E> vertex) {
        int adjacencyAmount = this.calculateAdjacency(vertex);
        String[] incidenceEdges = new String[adjacencyAmount];

        Vertex<V, E> auxiliaryVertex = this.searchVertex(vertex);
        if (auxiliaryVertex != null) {
            Node<Edge<V, E>> auxiliaryEdge = auxiliaryVertex.getHead();
            for (int i = 0; i < adjacencyAmount; i++) {
                if (auxiliaryEdge.getData().getTargetVertex() != null) {
                    incidenceEdges[i] = String.format(
                            "%s -> %s%s",
                            vertex.getVertexData(),
                            vertex.getVertexData(),
                            auxiliaryEdge.getData().getTargetVertex().getVertexData()
                    );
                }
                auxiliaryEdge = auxiliaryEdge.getNext();
            }
            return incidenceEdges;
        } else {
            return null;
        }
    }

    public final boolean hasVertex(Vertex<V, E> vertex) {
        return this.searchVertex(vertex) != null;
    }

    /*====================================== PRIVATE METHODS =====================================*/

    private Edge<V, E> searchShortestEdge(Vertex<V, E> startVertex, Vertex<V, E> endVertex) {
        return null;
    }
}
