import java.io.*;
import java.util.*;

public class points {
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
            return (int) st.nval;
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
    TreeSet<Integer> ans;
    int timer;
    int[] timeIn, fup;

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
        Arrays.fill(visited, false);
        ans = new TreeSet<Integer>();
        timer = 1;
        fup = new int[n];
        timeIn = new int[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i])
                dfs(i, -1);
        }
        out.println(ans.size());
        for (int k : ans) {
            out.println(k + 1);
        }
    }

    void dfs(int u, int p) {
        visited[u] = true;
        timeIn[u] = fup[u] = timer++;
        int children = 0;
        for(int v : graph[u]) {
            if (v == p)
                continue;
            if (visited[v]) {
                fup[u] = Math.min(fup[u], timeIn[v]);
            }
            else {
                children++;
                dfs(v, u);
                fup[u] = Math.min(fup[u], fup[v]);
                if (fup[v] >= timeIn[u] && p != -1)
                    ans.add(u);
            }
        }
        if (p == -1 && children > 1)
            ans.add(u);
    }

    public void run() {
        try {
            in = new FastScanner(new File("points.in"));
            out = new PrintWriter("points.out");

            solve();

            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new points().run();
    }
}