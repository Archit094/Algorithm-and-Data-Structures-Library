public static int[] buildCentroidTree(int[][] g) {
		int n = g.length;
		int[] ctpar = new int[n];
		Arrays.fill(ctpar, -1);
		buildCentroidTree(g, 0, new boolean[n], new int[n], new int[n], new int[n], ctpar);
		return ctpar;
	}
private static int buildCentroidTree(int[][] g, int root, boolean[] sed, int[] par, int[] ord, int[] des, int[] ctpar)
	{
		// parent and level-order
		ord[0] = root;
		par[root] = -1;
		int r = 1;
		for(int p = 0;p < r;p++) {
			int cur = ord[p];
			for(int nex : g[cur]){
				if(par[cur] != nex && !sed[nex]){
					ord[r++] = nex;
					par[nex] = cur;
				}
			}
		}
		// if(r == 1)return;
		
		// DP and find a separator
		int sep = -1; // always exists
		outer:
		for(int i = r-1;i >= 0;i--){
			int cur = ord[i];
			des[cur] = 1;
			for(int e : g[cur]){
				if(par[cur] != e && !sed[e])des[cur] += des[e];
			}
			if(r-des[cur] <= r/2){
				for(int e : g[cur]){
					if(par[cur] != e && !sed[e] && des[e] >= r/2+1)continue outer;
				}
				sep = cur;
				break;
			}
		}
		
		sed[sep] = true;
		for(int e : g[sep]){
			if(!sed[e])ctpar[buildCentroidTree(g, e, sed, par, ord, des, ctpar)] = sep;
		}
		return sep;
	}
	
	public static int[][] parents3(int[][] g, int root) {
		int n = g.length;
		int[] par = new int[n];
		Arrays.fill(par, -1);

		int[] depth = new int[n];
		depth[0] = 0;

		int[] q = new int[n];
		q[0] = root;
		for (int p = 0, r = 1; p < r; p++) {
			int cur = q[p];
			for (int nex : g[cur]) {
				if (par[cur] != nex) {
					q[r++] = nex;
					par[nex] = cur;
					depth[nex] = depth[cur] + 1;
				}
			}
		}
		return new int[][] { par, q, depth };
	}