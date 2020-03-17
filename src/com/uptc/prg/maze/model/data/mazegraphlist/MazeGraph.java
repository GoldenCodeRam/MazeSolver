package com.uptc.prg.maze.model.data.mazegraphlist;

import com.uptc.prg.maze.model.data.graphlist.EdgeType;
import com.uptc.prg.maze.model.data.graphlist.list.LinkedList;

import java.awt.*;

public class MazeGraph extends LinkedList<MazeVertex> {

    public MazeGraph(MazeVertex startMazeVertex) {
        super(startMazeVertex);
    }

    public MazeGraph() {
    }

    /*====================================== PUBLIC METHODS ======================================*/

    public final MazeVertex getAnyUncheckedVertex() {
        Node<MazeVertex> uncheckedVertexNode = this.getHead();
        while (uncheckedVertexNode != null) {
            if (!uncheckedVertexNode.getData().isChecked()) {
                return uncheckedVertexNode.getData();
            }
            uncheckedVertexNode = uncheckedVertexNode.getNext();
        }
        return null;
    }

    public final MazeVertex addVertex(MazeVertex mazeVertex) {
        if (this.searchMazeVertex(mazeVertex) == null) {
            this.addToList(mazeVertex);
            return mazeVertex;
        } else {
            return this.searchMazeVertex(mazeVertex);
        }
    }

    public final void addPath(MazePath path) {
        MazeVertex startMazeVertex = this.addVertex(new MazeVertex(path.getStartVertexPosition()));
        MazeVertex endMazeVertex = this.addVertex(new MazeVertex(path.getEndVertexPosition()));
        if (path.getEdgeType() == EdgeType.DIRECTED) {
            this.addDirectedPaths(startMazeVertex, endMazeVertex, path.getEdgeWeight());
        } else {
            this.addUndirectedPath(startMazeVertex, endMazeVertex, path.getEdgeWeight());
        }
    }

    public final void addDirectedPaths(MazeVertex startMazeVertex, MazeVertex endMazeVertex, int pathWeight) {
        startMazeVertex.addEdge(new MazeEdge(endMazeVertex, pathWeight, EdgeType.DIRECTED));
    }

    public final void addUndirectedPath(MazeVertex startMazeVertex, MazeVertex endMazeVertex, int pathWeight) {
        startMazeVertex.addEdge(new MazeEdge(endMazeVertex, pathWeight, EdgeType.UNDIRECTED));
        endMazeVertex.addEdge(new MazeEdge(startMazeVertex, pathWeight, EdgeType.DIRECTED));
    }

    /**
     * Try to find on the graph the parameter {@link MazeVertex}, if is found the {@link MazeVertex} from
     * the graph is returned, else, returns {@literal null}.
     *
     * @param mazeVertex The {@link MazeVertex} to search on the graph.
     * @return The found {@link MazeVertex} from the graph, {@literal null} otherwise.
     */
    public final MazeVertex searchMazeVertex(MazeVertex mazeVertex) {
        Node<MazeVertex> foundNode = this.getHead();
        while (foundNode != null) {
            if (foundNode.getData().getVertexPosition().equals(mazeVertex.getVertexPosition())) {
                return foundNode.getData();
            } else {
                foundNode = foundNode.getNext();
            }
        }
        return null;
    }

    public final MazeVertex searchApproximateMazeVertex(MazeVertex vertexToSearch, int threshold) {
        Node<MazeVertex> foundNode = this.getHead();
        while (foundNode != null && vertexToSearch.getVertexPosition() != null) {
            if (this.isOnPositionWithThreshold(
                    foundNode.getData().getVertexPosition(),
                    vertexToSearch.getVertexPosition(),
                    threshold)) {
                return foundNode.getData();
            } else {
                foundNode = foundNode.getNext();
            }
        }
        return null;
    }

    public final boolean hasVertex(MazeVertex mazeVertex) {
        return this.searchMazeVertex(mazeVertex) != null;
    }

    public final void uncheckAllVertices() {
        MazeVertex currentVertex;
        Node<MazeVertex> currentNodeVertex = this.getHead();
        while (currentNodeVertex != null) {
            currentVertex = currentNodeVertex.getData();
            currentVertex.uncheckVertex();
            currentNodeVertex = currentNodeVertex.getNext();
        }
    }

    public final void checkAllVertices() {
        MazeVertex currentVertex;
        Node<MazeVertex> currentNodeVertex = this.getHead();
        while (currentNodeVertex != null) {
            currentVertex = currentNodeVertex.getData();
            currentVertex.checkVertex();
            currentNodeVertex = currentNodeVertex.getNext();
        }
    }

    public final void resetVerticesDistances() {
        MazeVertex currentVertex;
        Node<MazeVertex> currentNodeVertex = this.getHead();
        while (currentNodeVertex != null) {
            currentVertex = currentNodeVertex.getData();
            currentVertex.setVertexDistance(Integer.MAX_VALUE);
            currentNodeVertex = currentNodeVertex.getNext();
        }
    }

    public final int size() {
        int vertexCount = 0;
        Node<MazeVertex> currentNodeVertex = this.getHead();
        while (currentNodeVertex != null) {
            vertexCount++;
            currentNodeVertex = currentNodeVertex.getNext();
        }
        return vertexCount;
    }

    /*====================================== PRIVATE METHODS =====================================*/

    private boolean isOnPositionWithThreshold(Point pointOnGraph, Point searchingPosition, int threshold) {
        if (searchingPosition.x > pointOnGraph.x - threshold && searchingPosition.x < pointOnGraph.x + threshold) {
            return searchingPosition.y > pointOnGraph.y - threshold && searchingPosition.y < pointOnGraph.y + threshold;
        }
        return false;
    }
}
