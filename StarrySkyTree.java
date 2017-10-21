	public static class StarrySkyTreePosL {
		public int M, H, N;
		public long[] st;
		public int[] st2;
		public long[] plus;
		public int[] pos;
		public long I = Long.MAX_VALUE/4; // I+plus<int
		
		public StarrySkyTreePosL(int n)
		{
			N = n;
			M = Integer.highestOneBit(Math.max(n-1, 1))<<2;
			H = M>>>1;
			st = new long[M];
			plus = new long[H];
			pos = new int[M];
			for(int i = M-1;i >= H;i--)pos[i] = i-H;
			for(int i = H-1;i >= 0;i--)propagate(i);
		}
		
		public StarrySkyTreePosL(long[] a)
		{
			N = a.length;
			M = Integer.highestOneBit(Math.max(N-1, 1))<<2;
			H = M>>>1;
			st = new long[M];
			for(int i = 0;i < N;i++){
				st[H+i] = a[i];
			}
			plus = new long[H];
			Arrays.fill(st, H+N, M, I);
			pos = new int[M];
			for(int i = M-1;i >= H;i--)pos[i] = i-H;
			for(int i = H-1;i >= 1;i--)propagate(i);
		}
		
		private void propagate(int i)
		{
			if(st[2*i] < st[2*i+1] || st[2*i] == st[2*i+1] && pos[2*i] < pos[2*i+1]){
				st[i] = st[2*i] + plus[i];
				pos[i] = pos[2*i];
			}else{
				st[i] = st[2*i+1] + plus[i];
				pos[i] = pos[2*i+1];
			}
		}
		
		public void updatePos(int x, int v)
		{
			pos[H+x] = v;
			for(int i = H+x>>>1;i >= 1;i>>>=1){
				propagate(i);
			}
		}
		
		public void add(int l, int r, long v){ if(l < r)add(l, r, v, 0, H, 1); }
		
		private void add(int l, int r, long v, int cl, int cr, int cur)
		{
			if(l <= cl && cr <= r){
				if(cur >= H){
					st[cur] += v;
				}else{
					plus[cur] += v;
					propagate(cur);
				}
			}else{
				int mid = cl+cr>>>1;
				if(cl < r && l < mid){
					add(l, r, v, cl, mid, 2*cur);
				}
				if(mid < r && l < cr){
					add(l, r, v, mid, cr, 2*cur+1);
				}
				propagate(cur);
			}
		}
		
		public long min(int l, int r){ return l >= r ? I : min(l, r, 0, H, 1);}
		
		private long min(int l, int r, int cl, int cr, int cur)
		{
			if(l <= cl && cr <= r){
				return st[cur];
			}else{
				int mid = cl+cr>>>1;
				long ret = I;
				if(cl < r && l < mid){
					ret = Math.min(ret, min(l, r, cl, mid, 2*cur));
				}
				if(mid < r && l < cr){
					ret = Math.min(ret, min(l, r, mid, cr, 2*cur+1));
				}
				return ret + plus[cur];
			}
		}
		
		public long[] minpos(int l, int r){ return l >= r ? null : minpos(l, r, 0, H, 1);}
		
		private long[] minpos(int l, int r, int cl, int cr, int cur)
		{
			if(l <= cl && cr <= r){
				return new long[]{st[cur], pos[cur]};
			}else{
				int mid = cl+cr>>>1;
				long[] L = null, R = null;
				if(cl < r && l < mid){
					L = minpos(l, r, cl, mid, 2*cur);
				}
				if(mid < r && l < cr){
					R = minpos(l, r, mid, cr, 2*cur+1);
				}
				if(L == null && R == null)return null;
				if(R == null){
					L[0] += plus[cur];
					return L;
				}
				if(L == null){
					R[0] += plus[cur];
					return R;
				}
				if(L[0] < R[0] || L[0] == R[0] && L[1] < R[1]){
					L[0] += plus[cur];
					return L;
				}else{
					R[0] += plus[cur];
					return R;
				}
			}
		}
		
		public void fall(int i)
		{
			if(i < H){
				if(2*i < H){
					plus[2*i] += plus[i];
					plus[2*i+1] += plus[i];
				}
				st[2*i] += plus[i];
				st[2*i+1] += plus[i];
				plus[i] = 0;
			}
		}
		
		public int firstle(int l, long v) {
			if(l >= H)return -1;
			int cur = H+l;
			for(int i = 1, j = Integer.numberOfTrailingZeros(H)-1;i <= cur;j--){
				fall(i);
				i = i*2 + (cur>>>j&1);
			}
			while(true){
				fall(cur);
				if(st[cur] <= v){
					if(cur < H){
						cur = 2*cur;
					}else{
						return cur-H;
					}
				}else{
					cur++;
					if((cur&cur-1) == 0)return -1;
					cur = cur>>>Integer.numberOfTrailingZeros(cur);
				}
			}
		}
		
		public int lastle(int l, long v) {
			if(l < 0)return -1;
			int cur = H+l;
			for(int i = 1, j = Integer.numberOfTrailingZeros(H)-1;i <= cur;j--){
				fall(i);
				i = i*2 + (cur>>>j&1);
			}
			while(true){
				fall(cur);
				if(st[cur] <= v){
					if(cur < H){
						cur = 2*cur+1;
					}else{
						return cur-H;
					}
				}else{
					if((cur&cur-1) == 0)return -1;
					cur = cur>>>Integer.numberOfTrailingZeros(cur);
					cur--;
				}
			}
		}		
		public long[] toArray() { return toArray(1, 0, H, new long[H]); }
		
		private long[] toArray(int cur, int l, int r, long[] ret)
		{
			if(r-l == 1){
				ret[cur-H] = st[cur];
			}else{
				toArray(2*cur, l, l+r>>>1, ret);
				toArray(2*cur+1, l+r>>>1, r, ret);
				for(int i = l;i < r;i++)ret[i] += plus[cur];
			}
			return ret;
		}
	}
