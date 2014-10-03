import java.io.*;
import java.util.*;

/**
 * Created by daria on 03.10.14.
 */
public class bipartite {
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
    int[] color;
    ArrayList<Integer> comp;
    int count = 0;
    int[] ans;
    boolean ok;

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
        color = new int[n];
        Arrays.fill(color, 0);
        ok = true;
        for (int i = 0; i < n; i++) {
            if (color[i] == 0) {
                color[i] = 1;
                dfs(i);
            }
        }
        out.print(ok ? "YES" : "NO");
    }

    void dfs(int u) {
        for(int v : graph[u]) {
            if (color[v] == 0) {
                color[v] = 3 - color[u];
                dfs(v);
            }
            else if (color[v] == color[u]) {
                ok = false;
            }
        }

    }

    public void run() {
        try {
            in = new FastScanner(new File("bipartite.in"));
            out = new PrintWriter("bipartite.out");

            solve();

            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new bipartite().run();
    }
}
