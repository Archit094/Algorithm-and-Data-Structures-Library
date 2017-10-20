	static boolean nextPermutation(int[] src) {
		int i;
		for (i = src.length - 2; i >= 0 && src[i] > src[i + 1]; i--)
			;
		if (i == -1)
			return false;
		int j;
		for (j = i + 1; j < src.length && src[i] < src[j]; j++)
			;
		int d = src[i];
		src[i] = src[j - 1];
		src[j - 1] = d;
		for (int p = i + 1, q = src.length - 1; p < q; p++, q--) {
			d = src[p];
			src[p] = src[q];
			src[q] = d;
		}
		return true;
	}
