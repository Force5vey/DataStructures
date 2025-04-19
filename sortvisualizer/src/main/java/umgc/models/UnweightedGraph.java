package umgc.models;

import java.util.List;

public class UnweightedGraph<E> extends Graph<E>
{
    public UnweightedGraph(List<Edge> edges, int vertexCount)
    {
        super(edges, vertexCount);
    }
}
