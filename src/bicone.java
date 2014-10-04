import java.io.*;
import java.util.*;

/**
 * Created by daria on 04.10.14.
 */
public class bicone {
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
    int[] colors, ret, enter;
    int maxColor;
    boolean[] visited;
    ArrayList<Integer> ans;
    Stack<Integer> stack;
    int timer;

    public void solve() throws IOException {
        int n = in.nextInt(), m = in.nextInt();
        graph = new ArrayList[n];
        colors = new int[n];
        visited = new boolean[n];
        ret = new int[n];
        enter = new int[n];
        ans = new ArrayList<Integer>();
        stack = new Stack<Integer>();

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1, b = in.nextInt() - 1;
            graph[a].add(b);
            graph[b].add(a);
        }
        maxColor = 0;
        Arrays.fill(colors, 0);
        timer = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i])
                dfs(i, -1);
        }
        for (int i = 0; i < n; i++) {
            if (colors[i] == 0) {
                maxColor++;
                paint(i, maxColor);
            }
        }
        out.println(maxColor);
        for (int k : colors) {
            out.print(k + " ");
        }
    }

    void dfs(int v, int p) {
        visited[v] = true;
        enter[v] = ret[v] = timer++;
        for (int u : graph[v]) {
            if (u == p) {
                continue;
            }
            if (visited[u]) {
                ret[v] = Math.min(ret[v], enter[u]);
            }
            else {
                dfs(u, v);
                ret[v] = Math.min(ret[v], ret[u]);
            }
        }
    }

    void paint(int v, int color) {
        colors[v] = color;
        for (int u : graph[v]) {
            if (colors[u] == 0) {
                if (ret[u] == enter[u]) {
                    maxColor++;
                    paint(u, maxColor);
                }
                else {
                    paint (u, color);
                }
            }
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("bicone.in"));
            out = new PrintWriter("bicone.out");

            solve();

            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new bicone().run();
    }
}
