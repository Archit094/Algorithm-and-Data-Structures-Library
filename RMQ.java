	public static int[][][] buildP(int[] a)
	{
		int n = a.length;
		int b = 32-Integer.numberOfLeadingZeros(n);
		int[][] ret = new int[b][];
		int[][] pos = new int[b][];
		for(int i = 0, l = 1;i < b;i++, l*=2) {
			if(i == 0) {
				ret[i] = a;
				pos[i] = new int[n];
				for(int j = 0;j < n;j++)pos[i][j] = j;
			}else {
				ret[i] = new int[n-l+1];
				pos[i] = new int[n-l+1];
				for(int j = 0;j < n-l+1;j++) {
					if(ret[i-1][j] < ret[i-1][j+l/2]){
						ret[i][j] = ret[i-1][j];
						pos[i][j] = pos[i-1][j];
					}else{
						ret[i][j] = ret[i-1][j+l/2];
						pos[i][j] = pos[i-1][j+l/2];
					}
				}
			}
		}
		return new int[][][]{ret, pos};
	}
	
	public static int[][][] buildP(int[] a, int[] ord)
	{
		int n = a.length;
		int b = 32-Integer.numberOfLeadingZeros(n);
		int[][] ret = new int[b][];
		int[][] pos = new int[b][];
		for(int i = 0, l = 1;i < b;i++, l*=2) {
			if(i == 0) {
				ret[i] = a;
				pos[i] = ord;
			}else {
				ret[i] = new int[n-l+1];
				pos[i] = new int[n-l+1];
				for(int j = 0;j < n-l+1;j++) {
					if(ret[i-1][j] < ret[i-1][j+l/2]){
						ret[i][j] = ret[i-1][j];
						pos[i][j] = pos[i-1][j];
					}else{
						ret[i][j] = ret[i-1][j+l/2];
						pos[i][j] = pos[i-1][j+l/2];
					}
				}
			}
		}
		return new int[][][]{ret, pos};
	}
	
	public static int rmqpos(int[][][] st, int a, int b)
	{
		int t = 31-Integer.numberOfLeadingZeros(b-a);
		if(st[0][t][a] < st[0][t][b-(1<<t)]){
			return st[1][t][a];
		}else{
			return st[1][t][b-(1<<t)];
		}
	}
	
	public static int rmqval(int[][][] st, int a, int b)
	{
		int t = 31-Integer.numberOfLeadingZeros(b-a);
		if(st[0][t][a] < st[0][t][b-(1<<t)]){
			return st[0][t][a];
		}else{
			return st[0][t][b-(1<<t)];
		}
	}
