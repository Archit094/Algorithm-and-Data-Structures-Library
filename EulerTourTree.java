// vertex - iord[v]
// subtree - iord[v],right[iord[v]]
    public static int[] sortByPreorder(int[][] g, int root)
    {
        int n = g.length;
        int[] stack = new int[n];
        int[] ord = new int[n];
        boolean[] ved = new boolean[n];
        stack[0] = root;
        int p = 1;
        int r = 0;
        ved[root] = true;
        while(p > 0){
            int cur = stack[p-1];
            ord[r++] = cur;
            p--;
            for(int e : g[cur]){
                if(!ved[e]){
                    ved[e] = true;
                    stack[p++] = e;
                }
            }
        }
        return ord;
    }
    public static int[][] makeRights(int[][] g, int[] par, int root)
    {
        int n = g.length;
        int[] ord = sortByPreorder(g, root);
        int[] iord = new int[n];
        for(int i = 0;i < n;i++)iord[ord[i]] = i;
        int[] right = new int[n];
        for(int i = n-1;i >= 1;i--)
        {
            if(right[i] == 0)right[i] = i;
            int p = iord[par[ord[i]]];
            right[p] = Math.max(right[p], right[i]);
        }
        return new int[][]{ord, iord, right};
    }