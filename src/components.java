import java.io.*;
import java.lang.Integer;
import java.util.*;

public class components {
    class FastScanner {
        StreamTokenizer st;

        FastScanner() {
            st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        }

        FastScanner(File f) {
            try {
                st = new StreamTokenizer(new BufferedReader(new FileReader(f)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        int nextInt() throws IOException {
            st.nextToken();
            return (int)st.nval;
        }

        String nextString() throws IOException {
            st.nextToken();
            return st.sval;
        }
    }
    FastScanner in;
    PrintWriter out;
    ArrayList<Integer>[] graph;
    boolean[] visited;
    ArrayList<Integer> comp;
    int count = 0;
    int[] ans;

    public void solve() throws IOException {
        int n = in.nextInt(), m = in.nextInt();
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1, b = in.nextInt() - 1;
            graph[a].add(b);
            graph[b].add(a);
        }
        visited = new boolean[n];
        ans = new int[n];
        Arrays.fill(visited, false);
        comp = new ArrayList<Integer>();
        findComponents();
        out.println(count);
        for (int i = 0; i < n; i++) {
            out.print(ans[i] + " ");
        }

    }

    void dfs(int u) {
        visited[u] = true;
        comp.add(u);
        for(int v : graph[u]) {
            if (!visited[v])
                dfs(v);
        }
    }

    void findComponents() {
        Arrays.fill(visited, false);
        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                comp.clear();
                count++;
                dfs(i);
                for (int k : comp) {
                    ans[k] = count;
                }
            }
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("components.in"));
            out = new PrintWriter("components.out");

            solve();

            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new components().run();
    }
}