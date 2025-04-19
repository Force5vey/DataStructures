package umgc.models;

import java.util.*;

public class Graph<E>
{
    public static class Edge
    {
        public final int u, v;

        public Edge(int u, int v)
        {
            this.u = u;
            this.v = v;
        }
    }

    private final int vertexCount;
    private final List<List<Integer>> adjList;

    public Graph(List<Edge> edges, int vertexCount)
    {
        this.vertexCount = vertexCount;
        adjList = new ArrayList<>(vertexCount);
        for (int i = 0; i < vertexCount; i++)
        {
            adjList.add(new ArrayList<>());
        }
        for (Edge e : edges)
        {
            adjList.get(e.u).add(e.v);
        }
    }

    public class SearchTree
    {
        private final int root;
        private final int[] parent;
        private final List<Integer> searchOrder;

        public SearchTree(int root, int[] parent, List<Integer> searchOrder)
        {
            this.root = root;
            this.parent = parent;
            this.searchOrder = searchOrder;
        }

        public List<Integer> getPath(int index)
        {
            List<Integer> path = new ArrayList<>();
            while (index != -1)
            {
                path.add(index);
                index = parent[index];
            }
            return path;
        }

        public List<Integer> getSearchOrder()
        {
            return searchOrder;
        }

    }

    public SearchTree bfs(int root)
    {
        boolean[] visited = new boolean[vertexCount];
        int[] parent = new int[vertexCount];
        Arrays.fill(parent, -1);
        List<Integer> order = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        visited[root] = true;
        queue.offer(root);

        while (!queue.isEmpty())
        {
            int u = queue.poll();
            order.add(u);
            for (int v : adjList.get(u))
            {
                if (!visited[v])
                {
                    visited[v] = true;
                    parent[v] = u;
                    queue.offer(v);
                }
            }
        }
        return new SearchTree(root, parent, order);
    }

    public SearchTree dfs(int root)
    {
        boolean[] visited = new boolean[vertexCount];
        int[] parent = new int[vertexCount];
        Arrays.fill(parent, -1);
        List<Integer> order = new ArrayList<>();
        dfsHelper(root, visited, parent, order);
        return new SearchTree(root, parent, order);
    }

    private void dfsHelper(int u, boolean[] visited, int[] parent, List<Integer> order)
    {
        visited[u] = true;
        order.add(u);
        for (int v : adjList.get(u))
        {
            if (!visited[v])
            {
                parent[v] = u;
                dfsHelper(v, visited, parent, order);
            }
        }
    }
}
