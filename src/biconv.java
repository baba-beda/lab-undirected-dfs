import java.io.*;
import java.util.*;

/**
 * Created by daria on 04.10.14.
 */
public class biconv {
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
        Pair (int a, int b) {
            first = a;
            second = b;
        }
    }

    FastScanner in;
    PrintWriter out;
    ArrayList<Pair>[] graph;
    int[] colors, fup, timeIn;
    int maxColor;
    boolean[] visited;
    Stack<Integer> stack;
    int timer;

    public void solve() throws IOException {
        int n = in.nextInt(), m = in.nextInt();
        graph = new ArrayList[n];
        colors = new int[m];
        visited = new boolean[n];
        fup = new int[n];
        timeIn = new int[n];
        stack = new Stack<Integer>();

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Pair>();
        }

        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1, b = in.nextInt() - 1;
            graph[a].add(new Pair (b, i));
            graph[b].add(new Pair(a, i));
        }
        maxColor = 0;
        Arrays.fill(colors, 0);
        timer = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, -1);
            }
        }
        out.println(maxColor);
        for (int k : colors) {
            out.print(k + " ");
        }

    }

    void dfs(int u, int p) {
        visited[u] = true;
        fup[u] = timeIn[u] = timer++;
        for (Pair v : graph[u]) {
            if (v.first == p)
                continue;
            if (!visited[v.first]) {
                stack.push(v.second);
                dfs(v.first, u);
                if (fup[v.first] >= timeIn[u]) {
                    maxColor++;
                    while (stack.peek() != v.second) {
                        colors[stack.pop()] = maxColor;
                    }
                    colors[stack.pop()] = maxColor;
                }
                fup[u] = Math.min(fup[u], fup[v.first]);
            }
            else {
                if (timeIn[v.first] < timeIn[u])
                    stack.push(v.second);
                if (fup[u] > timeIn[v.first])
                    fup[u] = timeIn[v.first];
            }
        }
    }



    public void run() {
        try {
            in = new FastScanner(new File("biconv.in"));
            out = new PrintWriter("biconv.out");

            solve();

            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new biconv().run();
    }
}
