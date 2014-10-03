import java.io.*;
import java.util.*;

/**
 * Created by daria on 03.10.14.
 */
public class bridges {
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

    class Pair {
        int first;
        int second;
        Pair() {
            first = 0;
            second = 0;
        }
        Pair(int a, int b) {
            first = a;
            second = b;
        }
    }

    FastScanner in;
    PrintWriter out;
    ArrayList<Pair>[] graph;
    boolean[] visited;
    ArrayList<Integer> ans;
    int timer;
    int[] timeIn, fup;

    public void solve() throws IOException {
        int n = in.nextInt(), m = in.nextInt();
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Pair>();
        }
        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1, b = in.nextInt() - 1;
            graph[a].add(new Pair(b, i + 1));
            graph[b].add(new Pair(a, i + 1));
        }
        visited = new boolean[n];
        ans = new ArrayList<Integer>();
        timer = 0;
        fup = new int[n];
        timeIn = new int[n];
        Arrays.fill(visited, false);
        for (int i = 0; i < n; i++) {
            if (!visited[i])
                dfs(i, -1);
        }
        out.println(ans.size());
        Collections.sort(ans);
        for (int k : ans) {
            out.print(k + " ");
        }
    }

    void dfs(int u, int p) {
        visited[u] = true;
        timeIn[u] = fup[u] = timer++;
        for(Pair v : graph[u]) {
            if (v.first == p)
                continue;
            if (visited[v.first]) {
                fup[u] = Math.min(fup[u], timeIn[v.first]);
            }
            else {
                dfs(v.first, u);
                fup[u] = Math.min(fup[u], fup[v.first]);
                if (fup[v.first] > timeIn[u])
                    ans.add(v.second);
            }
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("bridges.in"));
            out = new PrintWriter("bridges.out");

            solve();

            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new bridges().run();
    }
}
