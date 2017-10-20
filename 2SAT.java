	public static class TwoSatisfiability {
		public int[] from, to;
		public int p;
		public int n;
		
		public TwoSatisfiability(int n) {
			this.n = n;
			from = new int[2];
			to = new int[2];
			p = 0;
		}
		
		private void addEdge(int x, int y)
		{
			if(p == from.length){
				from = Arrays.copyOf(from, p*3/2);
				to = Arrays.copyOf(to, p*3/2);
			}
			
			from[p] = x;
			to[p] = y;
			p++;
		}
		
		public boolean isSatisfied()
		{
			int[][] g = packD(2*n, from, to, p);
			int[] clus = decomposeToSCC(g);
			for(int i = 0;i < n;i++){
				if(clus[i] == clus[i+n])return false;
			}
			return true;
		}
		
		public int[] restore()
		{
			int[][] g = packD(2*n, from, to, p);
			int[] clus = decomposeToSCC(g);
			int[] ret = new int[n];
			for(int i = 0;i < n;i++){
				if(clus[i] == clus[i+n])return null; // unsatisfied
				ret[i] = clus[i] > clus[i+n] ? -1 : 1;
			}
			return ret;
		}
		
		// x or y
		// ~x->y, ~y->x
		public void or(int x, int y)
		{
			addEdge(x+n, y);
			addEdge(y+n, x);
		}
		
		// x = y
		// ~x -> ~y, ~y -> ~x
		// x -> y, y -> x
		public void eq(int x, int y)
		{
			addEdge(x, y);
			addEdge(y, x);
			addEdge(x+n, y+n);
			addEdge(y+n, x+n);
		}
		
		// x xor y
		// ~x -> y, y -> ~x
		// x -> ~y, ~y -> x
		public void xor(int x, int y)
		{
			addEdge(x+n, y);
			addEdge(y, x+n);
			addEdge(x, y+n);
			addEdge(y+n, x);
		}
		
		// always true
		public void t(int x)
		{
			addEdge(x+n, x);
		}
		
		// always false
		public void f(int x)
		{
			addEdge(x, x+n);
		}

		////// inner utility
		
		public static int[][] packD(int n, int[] from, int[] to, int sup)
		{
			int[][] g = new int[n][];
			int[] p = new int[n];
			for(int i = 0;i < sup;i++)p[from[i]]++;
			for(int i = 0;i < n;i++)g[i] = new int[p[i]];
			for(int i = 0;i < sup;i++){
				g[from[i]][--p[from[i]]] = to[i];
			}
			return g;
		}
		
		public static int[] decomposeToSCC(int[][] g)
		{
			int n = g.length;
			int[] stack = new int[n+1];
			int[] ind = new int[n+1];
			int[] ord = new int[n];
			Arrays.fill(ord, -1);
			int[] low = new int[n];
			Arrays.fill(low, -1);
			int sp = 0;
			int id = 0; // preorder
			int[] clus = new int[n];
			int cid = 0;
			int[] cstack = new int[n+1];
			int csp = 0;
			boolean[] incstack = new boolean[n];
			for(int i = 0;i < n;i++){
				if(ord[i] == -1){
					ind[sp] = 0;
					cstack[csp++] = i;
					stack[sp++] = i;
					incstack[i] = true;
					while(sp > 0){
						int cur = stack[sp-1];
						if(ind[sp-1] == 0){
							ord[cur] = low[cur] = id++;
						}
						if(ind[sp-1] < g[cur].length){
							int nex = g[cur][ind[sp-1]];
							if(ord[nex] == -1){
								ind[sp-1]++;
								ind[sp] = 0;
								incstack[nex] = true;
								cstack[csp++] = nex;
								stack[sp++] = nex;
							}else{
								// shortcut
//								U.tr(cur, nex, incstack[nex], low[nex], stack);
								if(incstack[nex])low[cur] = Math.min(low[cur], low[nex]);
								ind[sp-1]++;
							}
						}else{
							if(ord[cur] == low[cur]){
								while(csp > 0){
									incstack[cstack[csp-1]] = false;
									clus[cstack[--csp]] = cid;
									if(cstack[csp] == cur)break;
								}
								cid++;
							}
							if(--sp >= 1)low[stack[sp-1]] = Math.min(low[stack[sp-1]], low[stack[sp]]);
						}
					}
				}
			}
			return clus;
		}
	}
