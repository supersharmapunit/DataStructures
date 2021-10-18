import java.util.ArrayList;
import java.util.Scanner;

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

        Edge(int src, int nbr) {
            this.src = src;
            this.nbr = nbr;
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
    public static void Multisolver() {

    }

    // get connected components
    public static void gcc(ArrayList<Edge>[] graph, boolean[] visited, int src, ArrayList<Integer> comp) {
        visited[src] = true;
        comp.add(src);
        for (Edge e : graph[src]) {
            int nbr = e.nbr;
            if (visited[nbr] == false) {
                gcc(graph, visited, nbr, comp);
            }
        }
    }

    // perfect Friends
    public static void perfectFriends() {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        ArrayList<Edge>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            graph[a].add(new Edge(a, b));
            graph[b].add(new Edge(b, a));
        }
        sc.close();
        ArrayList<ArrayList<Integer>> comps = new ArrayList<>();
        boolean[] visited = new boolean[n];

        for (int i = 0; i < visited.length; i++) {
            if (visited[i] == false) {
                ArrayList<Integer> comp = new ArrayList<>();
                gcc(graph, visited, i, comp);
                comps.add(comp);
            }
        }

        int ans = 0;
        for (int i = 0; i < comps.size() - 1; i++) {
            for (int j = i + 1; j < comps.size(); j++) {
                ans += comps.get(i).size() * comps.get(j).size();
            }
        }

        System.out.println(ans);
    }

    // hamiltonian Path(.) or cycle(*)
    public static void hamiltonian(ArrayList<Edge>[] graph, int src, int osrc, boolean[] visited, String psf,
            int vvsf) {
        // paf - path so far, vvsf - vertex visited so far, osrc - original src

        // logic&&base case
        if (vvsf == graph.length - 1) {
            System.out.print(psf);

            boolean isHC = false;
            for (Edge e : graph[osrc]) {
                int nbr = e.nbr;
                if (nbr == src) {
                    isHC = true;
                    break;
                }
            }
            if (isHC) {
                System.out.println("*");
            } else {
                System.out.println(".");
            }

            return;
        }

        // traversal
        visited[src] = true;
        for (Edge e : graph[src]) {
            int nbr = e.nbr;

            if (visited[nbr] == false) {
                hamiltonian(graph, nbr, osrc, visited, psf + nbr, vvsf + 1);
            }
        }
        visited[src] = false;
    }

    static class Pair{
        int vtx;
        String psf;

        Pair(int vtx, String psf){
            this.vtx = vtx;
            this.psf = psf;
        }
    }

    public static void bfs(ArrayList<Edge>[] graph,int src, boolean[] visited, Queue<Pair> q){
        q.add(new Pair(src, src+""));

        while(q.size() != 0){
            Pair rp = q.remove();

            if(!visited[rp.vtx]){
                visited[rp.vtx] = true;

                System.out.println(rp.vtx + "@"+rp.psf);

                for(Edge e : graph[rp.vtx]){
                    int nbr = e.nbr;

                    if(visited[nbr] == false){
                        q.add(new Pair(nbr, rp.psf+nbr));
                    }
                }
            }
        }
    }
}