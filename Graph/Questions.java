import java.util.ArrayList;

public class Questions {
    public static class Edge {
        int src;
        int nbr;
        int wt;

        Edge(int src, int nbr, int wt) {
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }

    public static boolean hasPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited) {
        if (src == dest)
            return true;

        visited[src] = true;

        for (Edge e : graph[src]) {
            int nbr = e.nbr;

            if (!visited[nbr]) {
                if (hasPath(graph, nbr, dest, visited))
                    return true;
            }
        }

        return false;
    }

    public static void allPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited, String psf) {
        if (src == dest) {
            System.out.println(psf + dest); // because dest is still not added
            return;
        }

        visited[src] = true;

        for (Edge e : graph[src]) {
            int nbr = e.nbr;

            if (!visited[nbr]) {
                allPath(graph, nbr, dest, visited, psf + src);
            }
        }

        visited[src] = false; // so that we can discover all paths
        // otherwise it would've marked possible path nbr as true
    }

    // Multisolver - Smallest, Longest, Ceil, Floor, Kthlargest Path
    public static void Multisolver(){
        
    }
}