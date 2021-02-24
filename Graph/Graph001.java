import java.util.*;

public class Graph001{
    public static class Edge{
        int src, nbr, wt;

        public Edge(){
            this(0,0,0);
        }

        public Edge(int src, int nbr, int wt){
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }
    public static void main(String args[]){
        ArrayList<Edge>[] graph = new ArrayList[6];
        for (int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0,1,10));
        graph[0].add(new Edge(0,3,10));
        graph[0].add(new Edge(0,2,5));

        graph[1].add(new Edge(1,0,10));
        graph[1].add(new Edge(1,2,10));

        graph[2].add(new Edge(2,1,10));
        graph[2].add(new Edge(2,0,5));
        
        graph[3].add(new Edge(3,0,10));
        graph[3].add(new Edge(3,5,8));

        graph[4].add(new Edge(4,5,3));
        graph[4].add(new Edge(4,6,5));

        graph[5].add(new Edge(5,1,10));
        graph[5].add(new Edge(5,3,10));
        graph[5].add(new Edge(5,2,5));
        
          

        for (int i = 0; i < graph.length; i++){
            System.out.print(i+": ");
            ArrayList<Edge> list = graph[i];
            for (Edge e : list){
                System.out.print(e.src+"-"+e.nbr+"@"+e.wt+"->");
            }
            System.out.println();
        }

    }
}