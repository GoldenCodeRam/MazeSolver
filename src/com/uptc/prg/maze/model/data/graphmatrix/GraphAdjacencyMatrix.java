package com.uptc.prg.maze.model.data.graphmatrix;

public class GraphAdjacencyMatrix<V, E> {
    private E mEmptyValue;
    private V[] mVertices;
    private E[][] mEdges;

    public GraphAdjacencyMatrix(V[] vertex, E[][] edges, E emptyValue) {
        this.mEmptyValue = emptyValue;
        this.mVertices = vertex;
        this.mEdges = edges;
    }

    public int size() {
        return this.mVertices.length;
    }

    public V getVertex(int index) {
        return this.mVertices[index];
    }

    public E getEdge(int column, int row) {
        return this.mEdges[column][row];
    }

    public E getEmptyValue() {
        return this.mEmptyValue;
    }

    public boolean isDirectedGraph() {
        int edgesSize = this.mEdges.length;
        for (int i = 0; i < edgesSize; i++) {
            for (int e = 0; e < edgesSize; e++) {
                if (!this.mEdges[i][e].equals(this.mEdges[e][i])) {
                    return true;
                }
            }
        }
        return false;
    }

    public int calculateAdjacency(V vertex) {
        int index = this.searchPosition(vertex);
        int count = 0;
        for (E[] mEdge : this.mEdges) {
            if (!mEdge[index].equals(0)) {
                count++;
            }
        }
        return count;
    }

    /**
     * TODO: Do this. Thot.
     *
     * @param vertex
     * @return
     */
    public String[] calculateIncidence(V vertex) {
        return null;
    }

    /*====================================== PRIVATE METHODS =====================================*/

    private int searchPosition(V vertex) {
        for (int i = 0; i < this.mVertices.length; i++) {
            if (vertex == this.mVertices[i]) {
                return i;
            }
        }
        return -1;
    }

    public final int getShortPathBetween(V vertex1, V vertex2) {
        String[] paths = this.generatePossiblePaths();
        String vertex1Position = String.valueOf(this.searchPosition(vertex1));
        String vertex2Position = String.valueOf(this.searchPosition(vertex2));
        int complexity = 0;
        System.out.println(vertex1Position);
        System.out.println(vertex2Position);
        for (String path : paths) {
            if (path.startsWith(vertex1Position) && path.endsWith(vertex2Position)) {
                complexity++;
            }
        }
        return complexity;
    }

    public final String[] generatePossiblePaths() {
        int matrixSize = (int) (Math.pow(10, this.mEdges.length) - 1);
        String[] possiblePaths = new String[matrixSize * matrixSize];
        int possiblePathsIndex = 0;
        int pathNumber;
        for (int i = 0; i < matrixSize; i++) {
            for (int e = 0; e < matrixSize; e++) {
                pathNumber = i;
                possiblePaths[possiblePathsIndex] = pathNumber + "" + e;
                possiblePathsIndex++;
            }
        }
        return possiblePaths;
    }
}
